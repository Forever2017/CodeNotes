package com.run.ui;

import java.util.ArrayList;
import java.util.List;

import com.run.adapter.ProjectPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ViewPager mViewPager;
	private ProjectPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	/*底部四个按钮*/
	private TextView TabBtn_01,TabBtn_02,TabBtn_03;
	/*四个子页面*/
	private Fragment tab01,tab02,tab03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		initView();
		mAdapter = new ProjectPagerAdapter(getSupportFragmentManager(), mFragments);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		//设置缓存页面
		mViewPager.setOffscreenPageLimit(mFragments.size()-1);

	}



}
