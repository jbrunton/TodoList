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

import com.jbrunton.todolist.data.TasksDataSource;
import com.jbrunton.todolist.models.Task;

public class TaskListAdapter extends ArrayAdapter<Task>{
	private FragmentActivity mContext;
	private TasksDataSource mDataSource;
	
	public TaskListAdapter(FragmentActivity context, TasksDataSource dataSource) {
		super(context, R.layout.task_list_item, dataSource.getAllTasks());
		mContext = context;
		mDataSource = dataSource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = this.mContext.getLayoutInflater().inflate(R.layout.task_list_item, parent, false);
		// we need this to be final as we're assigning it in the anonymous class "onCheckedChanged" below.  And it would complain if it wasn't final
		final Task task = this.getItem(position);
		( (TextView) view.findViewById(R.id.title)).setText(task.getTitle());
		
		CheckBox complete = (CheckBox) view.findViewById(R.id.complete);
		complete.setChecked(task.getComplete());
		complete.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				task.setComplete(isChecked);
				mDataSource.saveTask(task);
			}});
		
		return view;
	}
}
