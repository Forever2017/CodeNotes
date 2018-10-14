package com.frid.ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frid.data.FridApplication;
import com.frid.fridapp.R;
import com.frid.adapter.ProjectPagerAdapter;
import com.frid.ui.fragment.BoxFragment;
import com.frid.ui.fragment.ListFragment;
import com.frid.ui.fragment.QueryFragment;
import com.frid.ui.fragment.SettingFragment;
import com.frid.view.RSFragmentActivity;
import com.polling.PollingService;
import com.polling.PollingUtils;
import device.frid.Device;

public class Main extends RSFragmentActivity implements OnClickListener,OnPageChangeListener {
	private ViewPager mViewPager;
	private ProjectPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	/*底部四个按钮*/
	private LinearLayout TabBtn_02,TabBtn_03,TabBtn_04,TabBtn_05;
	/*四个子页面*/
	private Fragment tab02,tab03,tab04,tab05;
	/*按钮上的图标*/
	private ImageButton ImgBut_02,ImgBut_03,ImgBut_04,ImgBut_05;

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
		//打开轮询服务
		PollingUtils.startPollingService(this, FridApplication.POLLING_TIME, PollingService.class, PollingService.ACTION);
	}

	private void initView()
	{
		TabBtn_02 = (LinearLayout) findViewById(R.id.id_tab_bottom_02);
		TabBtn_03 = (LinearLayout) findViewById(R.id.id_tab_bottom_03);
		TabBtn_04 = (LinearLayout) findViewById(R.id.id_tab_bottom_04);
		TabBtn_05 = (LinearLayout) findViewById(R.id.id_tab_bottom_05);

		TabBtn_02.setOnClickListener(this);
		TabBtn_03.setOnClickListener(this);
		TabBtn_04.setOnClickListener(this);
		TabBtn_05.setOnClickListener(this);
  
		ImgBut_02 = (ImageButton) TabBtn_02.findViewById(R.id.btn_tab_bottom_02);
		ImgBut_03 = (ImageButton) TabBtn_03.findViewById(R.id.btn_tab_bottom_03);
		ImgBut_04 = (ImageButton) TabBtn_04.findViewById(R.id.btn_tab_bottom_04);
		ImgBut_05 = (ImageButton) TabBtn_05.findViewById(R.id.btn_tab_bottom_05);

		tab02 = new ListFragment();//盘点
		tab03 = new BoxFragment();//保险箱
		tab04 = new QueryFragment();//核对
		tab05 = new SettingFragment();//设置

		/*mFragments.add(tab02);
		mFragments.add(tab03); 
		mFragments.add(tab04);
		mFragments.add(tab05);*/
		
		/**  0仓管(盘点 核对 设置)    1送货(保险箱+设置) */
		if(FridApplication.Identity == 0 || FridApplication.Identity == 3){
			mFragments.add(tab02);
			mFragments.add(tab04);
			mFragments.add(tab05);
			
			TabBtn_03.setVisibility(View.GONE);
			/*TabBtn_02.setVisibility(View.VISIBLE);
			TabBtn_03.setVisibility(View.GONE);
			TabBtn_04.setVisibility(View.VISIBLE);
			TabBtn_05.setVisibility(View.VISIBLE);
			onPageSelected(0);
			mViewPager.setCurrentItem(0);*/
		}else{
			mFragments.add(tab03); 
			mFragments.add(tab05);
			
			TabBtn_02.setVisibility(View.GONE);
			TabBtn_04.setVisibility(View.GONE);
			/*TabBtn_02.setVisibility(View.GONE);
			TabBtn_03.setVisibility(View.VISIBLE);
			TabBtn_04.setVisibility(View.GONE);
			TabBtn_05.setVisibility(View.VISIBLE);
//			onPageSelected(1);
//			mViewPager.setCurrentItem(1);
			onClick(TabBtn_03);*/
		}
		onPageSelected(0);
		
		
		
		MainState = (TextView) findViewById(R.id.MainState);
		/*MainState.setText(device.connection()?"连接正常.":"连接失败,请重启设备.");*/
		//正常改成绿色，异常显示红色
		if(device.connection()){//连接正常
			MainState.setText("连接正常.");
			MainState.setTextColor(getResources().getColor(R.color.TitleColor));
		}else{
			MainState.setText("连接失败,请重启设备.");
			MainState.setTextColor(Color.RED);
		}
		
	}

	protected void resetTabBtn() 
	{
		ImgBut_02.setImageResource(R.drawable.tab_02_normal);
		ImgBut_03.setImageResource(R.drawable.tab_03_normal);
		ImgBut_04.setImageResource(R.drawable.tab_04_normal);
		ImgBut_05.setImageResource(R.drawable.tab_05_normal);
	}
	@Override
	public void onClick(View v) {
		resetTabBtn();
		/**  0仓管(盘点 核对 设置)    1送货(保险箱+设置) */
		if(FridApplication.Identity == 0){
			switch (v.getId())
			{
			case R.id.id_tab_bottom_02:
				ImgBut_02.setImageResource(R.drawable.tab_02_pressed);
				mViewPager.setCurrentItem(0);
				break;
			case R.id.id_tab_bottom_04:
				ImgBut_04.setImageResource(R.drawable.tab_04_pressed);
				mViewPager.setCurrentItem(1);
				break;
			case R.id.id_tab_bottom_05:
				ImgBut_05.setImageResource(R.drawable.tab_05_pressed);
				mViewPager.setCurrentItem(2);
				break;
			}
		}else{
			switch (v.getId())
			{
			case R.id.id_tab_bottom_03:
				ImgBut_03.setImageResource(R.drawable.tab_03_pressed);
				mViewPager.setCurrentItem(0);
				break;
			case R.id.id_tab_bottom_05:
				ImgBut_05.setImageResource(R.drawable.tab_05_pressed);
				mViewPager.setCurrentItem(1);
				break;
			}
		}
		
		
		
		/*switch (v.getId())
		{
		case R.id.id_tab_bottom_02:
			ImgBut_02.setImageResource(R.drawable.tab_02_pressed);
			mViewPager.setCurrentItem(0);
			break;
		case R.id.id_tab_bottom_03:
			ImgBut_03.setImageResource(R.drawable.tab_03_pressed);
			mViewPager.setCurrentItem(1);
			break;
		case R.id.id_tab_bottom_04:
			ImgBut_04.setImageResource(R.drawable.tab_04_pressed);
			mViewPager.setCurrentItem(2);
			break;
		case R.id.id_tab_bottom_05:
			ImgBut_05.setImageResource(R.drawable.tab_05_pressed);
			mViewPager.setCurrentItem(3);
			break;
		}*/
	}
	@Override
	public void onPageSelected(int position) {
		resetTabBtn();
		/**  0仓管(盘点 核对 设置)    1送货(保险箱+设置) */
		if(FridApplication.Identity == 0){
			switch (position)
			{
			case 0:
				ImgBut_02.setImageResource(R.drawable.tab_02_pressed);
				break;
			case 1:
				ImgBut_04.setImageResource(R.drawable.tab_04_pressed);
				break;
			case 2:
				ImgBut_05.setImageResource(R.drawable.tab_05_pressed);
				break;
			}
		}else{
			switch (position)
			{
			case 0:
				ImgBut_03.setImageResource(R.drawable.tab_03_pressed);
				break;
			case 1:
				ImgBut_05.setImageResource(R.drawable.tab_05_pressed);
				break;
			}
		}
		/*switch (position)
		{
		case 0:
			ImgBut_02.setImageResource(R.drawable.tab_02_pressed);
			break;
		case 1:
			ImgBut_03.setImageResource(R.drawable.tab_03_pressed);
			break;
		case 2:
			ImgBut_04.setImageResource(R.drawable.tab_04_pressed);
			break;
		case 3:
			ImgBut_05.setImageResource(R.drawable.tab_05_pressed);
			break;
		}*/
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

}