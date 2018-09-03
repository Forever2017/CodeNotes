package com.frid.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class ProjectPagerAdapter extends FragmentPagerAdapter{
	private List<Fragment> mFragments;
	
	public ProjectPagerAdapter(FragmentManager fm,List<Fragment> mFragments) {
		super(fm);
		this.mFragments = mFragments;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}


	@Override
	public int getCount() {
		return mFragments.size();
	}

	/*@Override
	public void destroyItem(View container, int position, Object object) {
		//super.destroyItem(container, position, object);
		return null;
	}*/
}