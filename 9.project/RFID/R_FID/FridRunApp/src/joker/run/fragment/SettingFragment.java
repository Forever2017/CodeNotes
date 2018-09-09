package joker.run.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import joker.run.sqliteorm.Epc;
import joker.run.sqliteorm.EpcDao;
import joker.run.ui.ActivityJoker;
import joker.run.ui.FragmentJoker;
import joker.run.ui.R;

@SuppressLint("ValidFragment")
public class SettingFragment extends FragmentJoker implements AdapterView.OnItemSelectedListener,OnClickListener{

    public SettingFragment(int Layout) {  super(Layout);  }

    private Spinner SpinnerJoker;
    private Button DownloadEpc;
    @Override
    public void init() {
        super.init();

        SpinnerJoker = (Spinner) findViewById(R.id.SpinnerJoker);
        DownloadEpc = (Button) findViewById(R.id.DownloadEpc);

        SpinnerJoker.setOnItemSelectedListener(this);
        DownloadEpc.setOnClickListener(this);
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
			EpcDao ed = new EpcDao(getActivity());
			ed.add(new Epc("1001", "小明"));
			ed.add(new Epc("1002", "小红"));
			ed.add(new Epc("1003", "韩梅梅"));
			ed.add(new Epc("1004", "小白"));
			ed.add(new Epc("1005", "隔壁老王"));
			
			Toast("下载成功.");
			
			break;
		default:
			break;
		}
	}

    
}
