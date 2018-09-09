package joker.run.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import device.frid.Device;
import joker.kit.base.ActivityJoker;
import joker.run.data.HOST;

import static joker.kit.arithmetic.TimeUitl.FormatMiss;

public class TimeReckonActivity extends ActivityJoker implements Chronometer.OnChronometerTickListener, View.OnClickListener {

    private Chronometer ReckonTime;
    private Button ReckonStop, ReckonOperation;
    private TextView ReckonTimeType, ReckonDistance;

    private int current = 0;//计算时间 秒
    public static final int TIME_RECKON = 9527;

    private Device device;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_time_reckon);
        init();
        scanFrid();//设备扫描
    }

    public void init() {
        device = new Device(this);

        ReckonTime = findViewById(R.id.ReckonTime);
        ReckonStop = findViewById(R.id.ReckonStop);
        ReckonOperation = findViewById(R.id.ReckonOperation);

        ReckonTimeType = findViewById(R.id.ReckonTimeType);
        ReckonDistance = findViewById(R.id.ReckonDistance);

        ReckonStop.setOnClickListener(this);
        ReckonOperation.setOnClickListener(this);
        ReckonTime.setOnChronometerTickListener(this);

        ReckonTimeType.setText(getResources().getStringArray(R.array.runs)[HOST.RUN_TYPE]);
        ReckonDistance.setText(HOST.RUN_DIS + " " + getResources().getString(R.string.M));

        ReckonTime.start();

    }


    @Override
    public void onChronometerTick(Chronometer chronometer) {
        current++;
        System.out.println(current + "");
        chronometer.setText(FormatMiss(current));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ReckonStop://  停止计时 \ 查看结果
                ReckonTime.stop();
                ReckonOperation.setBackgroundColor(getResources().getColor(R.color.dimgray));
                ReckonOperation.setEnabled(false);

                if (ReckonStop.getText().toString().equals(getResources().getString(R.string.StopTime))) {//停止

                    /* 停止所有*/
                    ReckonStop.setText(R.string.checkResult);

                    stopScan();//停止设备

                } else {//查看

                    /* 关闭，传参数..*/
                    setResult(TIME_RECKON);

                    finish();

                }

                break;

            case R.id.ReckonOperation://  暂停计时 \ 继续计时


                if (ReckonOperation.getText().toString().equals(getResources().getString(R.string.suspended))) {//暂停
                    ReckonTime.stop();

                    /*rfid 暂停*/

                    stopScan();//停止设备
                    ReckonOperation.setText(R.string.Continue);
                } else {//继续
                    ReckonTime.start();

                    /*rfid 继续*/
                    scanFrid();//设备扫描
                    ReckonOperation.setText(R.string.suspended);
                }
                break;


        }
    }


    /**
     * 真实扫描
     */
    private void scanFrid() {
        device.startSearch(new Device.LoopEpc() {
            @Override
            public void ReturnEpc(String epc) {
                super.ReturnEpc(epc);
                Log.e("EPC扫描结果", epc);

                device.biBi(true);


            }
        }, false);//是否去除重复
    }

    /**
     * 真实停止
     */
    private void stopScan() {
        //真实停止扫描
        device.biBi(false);
        device.stopSearch();
    }
}
