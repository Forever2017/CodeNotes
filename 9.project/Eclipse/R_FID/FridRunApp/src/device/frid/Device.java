package device.frid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import device.frid.source.ShellUtils;
import device.frid.source.Sound;
import uhf.api.CommandType;
import uhf.api.MultiLableCallBack;
import uhf.api.Multi_query_epc;
import uhf.api.Power;
import uhf.api.Query_epc;
import uhf.api.ShareData;
import uhf.api.Ware;

/**
 * 扫描设备的相关操作
 */
public class Device implements MultiLableCallBack {
    private Ware mWare;
    private UHFClient info;
    private LoopEpc le;
    private Power mPower;
    private Sound mSound;
    private List<String> tempPool;
    private boolean isRepetition;//是否去除重复标签

    public Device(Context context) {
        mWare = new Ware(CommandType.GET_FIRMWARE_VERSION, 0, 0, 0);
        info = UHFClient.getInstance();
        mPower = new Power();
        mSound = new Sound(context);
        tempPool = new ArrayList<String>();
    }

    /**
     * Fr_id 设备连接
     *
     * @return //MainState.setText("设备获取成功");
     * //MainState.setText("连接失败，请重启设备");
     */
    public boolean connection() {
        //打开GPIO
        Boolean ret = ShellUtils.checkRootPermission();
        ShellUtils.execCommand(Parameter.open_1, ret);
        ShellUtils.execCommand(Parameter.open_2, ret);
        ShellUtils.execCommand(Parameter.open_3, ret);
        ShellUtils.execCommand(Parameter.open_4, ret);

        int count = 0;
        while (true) {
            if (info != null) {
                Boolean rett = UHFClient.mUHF.command(CommandType.GET_FIRMWARE_VERSION, mWare);
                if (rett) {
                    //Log.e("TAG", "Ver."+mWare.major_version+"."+mWare.minor_version+"."+mWare.revision_version);
                    if (mWare.major_version == 1
                            && (mWare.minor_version == 0 || mWare.minor_version == 1
                            || mWare.minor_version == 2 || mWare.minor_version == 3
                    )
                            && (mWare.revision_version == 0 || mWare.revision_version == 1
                            || mWare.revision_version == 2 || mWare.revision_version == 3 ||
                            mWare.revision_version == 4 || mWare.revision_version == 5 ||
                            mWare.revision_version == 6 || mWare.revision_version == 7 ||
                            mWare.revision_version == 8 || mWare.revision_version == 9)
                            ) {
                        //Log.e("设备信息", "Ver."+mWare.major_version+"."+mWare.minor_version+"."+mWare.revision_version);
                        return true;
                    }
                }
            }
            count++;
            if (count > 5) {
                return false;
            }
        }
    }


    /**
     * 单次扫描 s
     *
     * @return
     */
    public String SignSearch() {
        String msg = null;
        Query_epc mQuery_epc = new Query_epc();
        if (info != null) {

            Boolean ret = UHFClient.mUHF.command(
                    CommandType.SINGLE_QUERY_TAGS_EPC, mQuery_epc);
            if (ret) {

                String str_tmp = ShareData.CharToString(mQuery_epc.epc.epc,
                        mQuery_epc.epc.epc.length);
                str_tmp = str_tmp.replace(" ", "");
				/*String str_rssi = ""
						+ rssi_calculate((char) mQuery_epc.rssi_msb,
								(char) mQuery_epc.rssi_lsb);
				EPC e = mQuery_epc.epc;
				int rssi_msb = mQuery_epc.rssi_msb;//最高信号强度
				int rssi_lsb = mQuery_epc.rssi_lsb;//最低信号强度
				 */

                msg = str_tmp;
            } else {
                msg = null;
            }
        }
        return msg;
    }

    /**
     * 开始循环扫描
     * LoopEpc le 接收扫描到的数据
     */
    public void startSearch(LoopEpc le, boolean isRepetition) {
        this.isRepetition = isRepetition;
        this.le = le;
        tempPool.clear();
        // MULTI_QUERY_TAGS_EPC
        Multi_query_epc mMulti_query_epc = new Multi_query_epc();
        mMulti_query_epc.query_total = 0;

        UHFClient info = UHFClient.getInstance();
        if (info != null) {
            UHFClient.mUHF.setCallBack(this);
            UHFClient.mUHF.command(CommandType.MULTI_QUERY_TAGS_EPC,
                    mMulti_query_epc);
            //isStart = true;
        }
    }

    /**
     * 关闭循环扫描
     */
    public void stopSearch() {
        UHFClient info = UHFClient.getInstance();
        if (info != null) {
            Boolean ret = UHFClient.mUHF.command(
                    CommandType.STOP_MULTI_QUERY_TAGS_EPC, null);
            if (ret) {
                //mSound.callAlarm(true, 100);
                //setTitle("Stop Ok");
                //isStart = false;
            } else {
                //mSound.callAlarm(false, 100);
                //setTitle("Stop Fail");
            }
        }
    }

    /**
     * 接口循环返回扫描到的数据
     */
    @Override
    public void method(char[] data) {
        if (data.length <= 0) return;
        // 把EPC拷贝出来显示
        char msb = data[0];
        char lsb = data[1];
        int pc = (msb & 0x00ff) << 8 | (lsb & 0x00ff);
        pc = (pc & 0xf800) >> 11;
        char[] tmp = new char[pc * 2];
        System.arraycopy(data, 2, tmp, 0, tmp.length);
        String str_tmp = ShareData.CharToString(tmp, tmp.length);
        str_tmp = str_tmp.replace(" ", "");
        //final String str_rssi = ""+Parameter.rssi_calculate(data[2 + pc * 2], data[2 + pc * 2 + 1]);
        /** str_tmp   ID*/
        /** str_rssi  强度 */

        /**
         * 重复扫描的频率太快，去除重复，一次循环扫描，每次只返回未重复的epc
         * */
        if (isRepetition) {
            if (!tempPool.contains(str_tmp)) {
                tempPool.add(str_tmp);
                le.ReturnEpc(str_tmp);
            }
        } else
            le.ReturnEpc(str_tmp);


    }


    /**
     * 设置 功率
     * int_power_read_temp  功率值 （最高不可超过 30 dBm）
     *
     * @return
     */
    public boolean setPower(int int_power_read_temp) {
        if (info == null) return false;

        mPower.com_type = CommandType.SET_POWER;
        mPower.loop = 0;
        mPower.read = int_power_read_temp;
        mPower.write = int_power_read_temp;

        Boolean ret = UHFClient.mUHF.command(CommandType.SET_POWER, mPower);

        if (ret) {

            //	FridApplication.insertIdentity("FRID_POWER", int_power_read_temp);

            return true;
        }

        return false;
    }

    /**
     * 获取 功率
     *
     * @return
     */
    public int getPower() {
        if (info != null) return 0;

        Boolean ret = UHFClient.mUHF.command(CommandType.GET_POWER, mPower);

        if (ret) {
            return mPower.read;
        }

        return 0;
    }

    /**
     * 发出Bi的声音....
     */
    public void biBi(boolean b) {
        mSound.callAlarm(b, 100);
    }

    /**
     * 回调方法
     */
    public static abstract class LoopEpc {
        /**
         * 返回epc
         */
        public void ReturnEpc(String epc) {
        }
    }
}
