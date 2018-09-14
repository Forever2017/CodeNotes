package joker.run.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import device.frid.Device;
import joker.run.adapter.ProjectPagerAdapter;
import joker.run.fragment.ResultFragment;
import joker.run.fragment.SettingFragment;
import joker.run.fragment.TimeFragment;
import joker.run.sqliteorm.Epc;
import joker.run.sqliteorm.TDao;
import joker.run.util.TimeUitl;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener {
	private ViewPager mViewPager;
	private ProjectPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private Fragment Fragment1, Fragment2, Fragment3;

	private TextView btn_tab_bottom_01,btn_tab_bottom_02,btn_tab_bottom_03;

	private TextView MainState;
	private Device device;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		device = new Device(this);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		initView();
		mAdapter = new ProjectPagerAdapter(getSupportFragmentManager(), mFragments);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		//设置缓存页面
		mViewPager.setOffscreenPageLimit(mFragments.size()-1);

		//设置不休眠
		Settings.System.putInt(getContentResolver(),
				android.provider.Settings.System.SCREEN_OFF_TIMEOUT,Integer.MAX_VALUE);

		test();
	}
	private void test() {
		String m = "600";
		String d = "800";
//		int xxxxx = (int) (Double.parseDouble(m)/(Double.parseDouble(d)/1000));
		String result = TimeUitl.FormatMs((int) (Double.parseDouble(m)/(Double.parseDouble(d)/1000)));
		String result2 = TimeUitl.FormatMs(3500);

		System.out.print("");
//		System.out.print(result);

		
	}

	private void initView()
	{
		btn_tab_bottom_01 = (TextView) findViewById(R.id.btn_tab_bottom_01);
		btn_tab_bottom_02 = (TextView) findViewById(R.id.btn_tab_bottom_02);
		btn_tab_bottom_03 = (TextView) findViewById(R.id.btn_tab_bottom_03);

		btn_tab_bottom_01.setOnClickListener(this);
		btn_tab_bottom_02.setOnClickListener(this);
		btn_tab_bottom_03.setOnClickListener(this);

		Fragment1 = new TimeFragment(R.layout.fragment_time);
		Fragment2 = new ResultFragment(R.layout.fragment_result);
		Fragment3 = new SettingFragment(R.layout.fragment_setting);

		mFragments.add(Fragment1);
		mFragments.add(Fragment2); 
		mFragments.add(Fragment3);

		MainState = (TextView) findViewById(R.id.MainState);
		//正常改成绿色，异常显示红色
		MainState.setText(device.connection()?"连接正常.":"连接失败,请重启设备.");
	}
	protected void resetTabBtn() {
		btn_tab_bottom_01.setTextColor(getResources().getColor(R.color.white));
		btn_tab_bottom_02.setTextColor(getResources().getColor(R.color.white));
		btn_tab_bottom_03.setTextColor(getResources().getColor(R.color.white));

		btn_tab_bottom_01.setBackgroundColor(getResources().getColor(R.color.gray));
		btn_tab_bottom_02.setBackgroundColor(getResources().getColor(R.color.gray));
		btn_tab_bottom_03.setBackgroundColor(getResources().getColor(R.color.gray));
	}
	@Override
	public void onClick(View v) {
		resetTabBtn();
		switch (v.getId())
		{
		case R.id.btn_tab_bottom_01:
			btn_tab_bottom_01.setTextColor(getResources().getColor(R.color.white));
			btn_tab_bottom_01.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
			mViewPager.setCurrentItem(0);
			break;
		case R.id.btn_tab_bottom_02:
			btn_tab_bottom_02.setTextColor(getResources().getColor(R.color.white));
			btn_tab_bottom_02.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
			mViewPager.setCurrentItem(1);
			break;
		case R.id.btn_tab_bottom_03:
			btn_tab_bottom_03.setTextColor(getResources().getColor(R.color.white));
			btn_tab_bottom_03.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
			mViewPager.setCurrentItem(2);
			break;
		}
	}
	@Override
	public void onPageSelected(int position) {
		resetTabBtn();
		switch (position)
		{
		case 0:
			btn_tab_bottom_01.setTextColor(getResources().getColor(R.color.white));
			btn_tab_bottom_01.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
			mViewPager.setCurrentItem(0);
			break;
		case 1:
			btn_tab_bottom_02.setTextColor(getResources().getColor(R.color.white));
			btn_tab_bottom_02.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
			mViewPager.setCurrentItem(1);
			break;
		case 2:
			btn_tab_bottom_03.setTextColor(getResources().getColor(R.color.white));
			btn_tab_bottom_03.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
			mViewPager.setCurrentItem(2);
			break;
		}

	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	//接收
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (TimeReckonActivity.TIME_RECKON == resultCode) {

			onPageSelected(1);
			ResultFragment.update();
		}
	}




}
