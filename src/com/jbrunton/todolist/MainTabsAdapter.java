package com.jbrunton.todolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainTabsAdapter extends FragmentPagerAdapter{
	public MainTabsAdapter (FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		return new TaskListFragment();
	}

	@Override
	public int getCount() {
		// 3 tabs
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0: return "Today";
		case 1: return "This Week";
		default: return "Later";
		}
	}

}
