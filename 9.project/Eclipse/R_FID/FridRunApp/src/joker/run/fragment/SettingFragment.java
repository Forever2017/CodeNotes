package joker.run.fragment;

import java.util.List;

import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import joker.run.base.FragmentJoker;
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
	private Button DownloadEpc,UploadEpc;
	private Switch aSwitch;
	@Override
	public void init() {
		super.init();

		SpinnerJoker = (Spinner) findViewById(R.id.SpinnerJoker);
		DownloadEpc = (Button) findViewById(R.id.DownloadEpc);
		UploadEpc = (Button) findViewById(R.id.UploadEpc);
		aSwitch = (Switch) findViewById(R.id.s_v);

		SpinnerJoker.setOnItemSelectedListener(this);
		DownloadEpc.setOnClickListener(this);
		UploadEpc.setOnClickListener(this);
		aSwitch.setOnCheckedChangeListener(this);

		HOST.PEO_IS = ApplicationRunning.getSharedInt("PEO_IS");
		aSwitch.setChecked(HOST.PEO_IS == 0?false:true);
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
			//模拟
			/*EpcDao ed = new EpcDao(getActivity());
			ed.add(new Epc("1001", "小明"));
			ed.add(new Epc("1002", "小红"));
			ed.add(new Epc("1003", "韩梅梅"));
			ed.add(new Epc("1004", "小白"));
			ed.add(new Epc("1005", "隔壁老王"));

			Toast("下载成功.");*/

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

		default:
			break;
		}
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
