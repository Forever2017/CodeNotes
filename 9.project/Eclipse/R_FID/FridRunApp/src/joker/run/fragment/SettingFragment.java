package joker.run.fragment;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import device.frid.Device;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import joker.run.adapter.SettAdapter;
import joker.run.base.FragmentJoker;
import joker.run.base.ThreadEpcTest;
import joker.run.base.ThreadEpcTest.EpcResult;
import joker.run.data.ASHttp;
import joker.run.data.ASHttp.AsyncHttp;
import joker.run.data.ApplicationRunning;
import joker.run.data.EpcHttpJson;
import joker.run.data.GsonState;
import joker.run.data.HOST;
import joker.run.sqliteorm.Epc;
import joker.run.sqliteorm.EpcDao;
import joker.run.sqliteorm.RunRecord;
import joker.run.sqliteorm.RunRecordDao;
import joker.run.ui.R;

@SuppressLint("ValidFragment")
public class SettingFragment extends FragmentJoker implements AdapterView.OnItemSelectedListener,OnClickListener,OnCheckedChangeListener{

	public SettingFragment(int Layout) {  super(Layout);  }

	private Spinner SpinnerJoker;
	private Button DownloadEpc,UploadEpc,ScanEpc;
	private Switch aSwitch;

	private ListView SettingList;
	private List<Epc> list;
	private SettAdapter sAdapter;

	private Device device;
	private EpcDao eDao;
	@Override
	public void init() {
		super.init();

		SpinnerJoker = (Spinner) findViewById(R.id.SpinnerJoker);
		DownloadEpc = (Button) findViewById(R.id.DownloadEpc);
		UploadEpc = (Button) findViewById(R.id.UploadEpc);
		ScanEpc = (Button) findViewById(R.id.ScanEpc);
		aSwitch = (Switch) findViewById(R.id.s_v);
		SettingList = (ListView) findViewById(R.id.SettingList);

		SpinnerJoker.setOnItemSelectedListener(this);
		DownloadEpc.setOnClickListener(this);
		UploadEpc.setOnClickListener(this);
		ScanEpc.setOnClickListener(this);
		aSwitch.setOnCheckedChangeListener(this);

		HOST.PEO_IS = ApplicationRunning.getSharedInt("PEO_IS");
		aSwitch.setChecked(HOST.PEO_IS == 0?false:true);

		list = new ArrayList<Epc>();
		sAdapter = new SettAdapter(getActivity(), list);
		SettingList.setAdapter(sAdapter);
		device = new Device(getActivity());
		eDao = new EpcDao(getActivity());
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

		/* try {

            Field field =       AdapterView.class.getDeclaredField("mOldSelectedPosition");
            field.setAccessible(true);  //设置mOldSelectedPosition可访问
            field.setInt(SpinnerJoker, AdapterView.INVALID_POSITION); //设置mOldSelectedPosition的值

        } catch (Exception e) {
            e.printStackTrace();
        }
        //中文 or 英语
        String[] languages = getResources().getStringArray(R.array.languages);
        Toast("你点击的是:"+languages[i]);*/

	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.DownloadEpc:
			ASHttp.downloadrfidinfo(getActivity(),new AsyncHttp() {
				public void onResult(boolean b, String msg) {

					if(b){
						/**这里需要做逻辑操作*/
						EpcHttpJson gc = new Gson().fromJson(msg, EpcHttpJson.class);

						if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
							if(new EpcDao(getActivity()).addList(gc.getList()) > 0)	{
								Toast("下载成功.");
							}else{
								Toast("下载失败！");
							}
						}
					}
				};
			} );

			break;

		case R.id.UploadEpc:
			List<RunRecord> rrList = new RunRecordDao(getActivity()).queryList();
			if(rrList != null && rrList.size() != 0 ){
				ASHttp.uploadtcresult(getActivity(),new ASHttp().getResultJson(rrList),new AsyncHttp() {
					public void onResult(boolean b, String msg) {
						/**这里需要做逻辑操作*/
						if(b){
							GsonState gs = new Gson().fromJson(msg, GsonState.class);
							if(gs.getResponseCode().equals("0000"))/**获取数据成功*/
							{
								Toast("上传成功.");
								new RunRecordDao(getActivity()).delAll();
								//	finish();
								ResultFragment.update();
							}	
							else
								Toast("上传失败. msg:"+msg);
						}else
							Toast("服务器异常. msg:"+msg);
					};
				} );
			}else Toast("没有可上传数据.");
			break;
		case R.id.ScanEpc:
			if(ScanEpc.getText().toString().equals("检索EPC")){
				ScanEpc.setText("停止检索");
				list.clear();
				sAdapter.notifyDataSetChanged();

				device.startSearch(new Device.LoopEpc() {
					@Override
					public void ReturnEpc(String epc) {
						super.ReturnEpc(epc);

						device.biBi(true);
						updateList(epc);
					}
				}, true);
				/*tet = new ThreadEpcTest(new EpcResult() {
					@Override
					public void onResult(String epc) {
						// TODO Auto-generated method stub
						super.onResult(epc);
						device.biBi(true);
						updateList(epc);
					}
				});*/

			}else{
				ScanEpc.setText("检索EPC");
				//真实停止扫描
				device.biBi(false);
				device.stopSearch();

				//	tet.stop();
			}
			break;
		default:
			break;
		}
	}
	//	ThreadEpcTest tet;//测试
	private void updateList(String epc) {
		Epc eTemp = eDao.queryEpc(epc);
		Epc epcBean;
		if(eTemp!=null){
			epcBean = new Epc(epc, eTemp.getName());
		}else{
			epcBean = new Epc(epc, "外部跑者");
		}
		list.add(epcBean);
		
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				sAdapter.notifyDataSetChanged();
			}
		});
		
		
	}


	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
		//控制开关字体颜色
		if (b) {
			aSwitch.setSwitchTextAppearance(getActivity(),R.style.s_true);
		}else {
			aSwitch.setSwitchTextAppearance(getActivity(),R.style.s_false);
		}
		HOST.PEO_IS = b == true?1:0;
		ApplicationRunning.setSharedInt("PEO_IS", HOST.PEO_IS);
	}


}
