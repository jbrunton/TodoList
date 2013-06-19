package com.jbrunton.todolist;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

public class MainActivity extends FragmentActivity implements TaskListFragment.Callbacks{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.main);
		tabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);
		tabHost.addTab(tabHost.newTabSpec("today").setIndicator("Today"), TaskListFragment.class, savedInstanceState);
		tabHost.addTab(tabHost.newTabSpec("this_week").setIndicator("This Week"), TaskListFragment.class, savedInstanceState);
		tabHost.addTab(tabHost.newTabSpec("later").setIndicator("Later"), TaskListFragment.class, savedInstanceState);
	}

	@Override
	public void onItemSelected(long id) {
		// TODO Auto-generated method stub
		
	}
	
}
