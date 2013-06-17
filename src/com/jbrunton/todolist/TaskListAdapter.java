package com.jbrunton.todolist;

import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.jbrunton.todolist.dummy.DummyContent.Task;

public class TaskListAdapter extends ArrayAdapter<Task>{
	private FragmentActivity mContext;
	public TaskListAdapter(FragmentActivity context, List<Task> tasks) {
		super(context, R.layout.task_list_item, tasks);
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = this.mContext.getLayoutInflater().inflate(R.layout.task_list_item, parent, false);
		final Task task = this.getItem(position);
		( (TextView) view.findViewById(R.id.title)).setText(task.title);
		
		CheckBox complete = (CheckBox) view.findViewById(R.id.complete);
		complete.setChecked(task.complete);
		complete.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				task.complete = isChecked;
			}});
		
		return view;
	}
}
