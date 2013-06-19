package com.jbrunton.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jbrunton.todolist.data.TasksDataSource;
import com.jbrunton.todolist.models.Task;

/**
 * A fragment representing a single Task detail screen. This fragment is either
 * contained in a {@link TaskListActivity} in two-pane mode (on tablets) or a
 * {@link TaskDetailActivity} on handsets.
 */
public class TaskDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Task mItem;
	
	private TasksDataSource mDataSource;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public TaskDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		mDataSource = new TasksDataSource(getActivity());
		mDataSource.open();
		
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			long taskId = getArguments().getLong(ARG_ITEM_ID);
			mItem = mDataSource.find(taskId);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpTo(getActivity(), new Intent(getActivity(),TaskListActivity.class));
			return true;
		case R.id.action_done:
			saveTask();
			getActivity().setResult(Activity.RESULT_OK, new Intent());
			getActivity().finish();
			return true;
		default: return super.onOptionsItemSelected(item);
		}
			
	}
	
	protected void saveTask() {
		if (mItem != null) {
			EditText title = (EditText)getView().findViewById(R.id.title);
			mItem.setTitle(title.getText().toString());
			EditText details = (EditText)getView().findViewById(R.id.details);
			mItem.setDetails(details.getText().toString());
			CheckBox complete = (CheckBox) getView().findViewById(R.id.complete);
			mItem.setComplete(complete.isChecked());
			EditText due_date = (EditText)getView().findViewById(R.id.due_date);
			mItem.setTitle(due_date.getText().toString());
			mDataSource.saveTask(mItem);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_task_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.title))
					.setText(mItem.getTitle());
			((TextView) rootView.findViewById(R.id.details))
					.setText(mItem.getDetails());
			((CheckBox) rootView.findViewById(R.id.complete))
					.setChecked(mItem.getComplete());
			((TextView) rootView.findViewById(R.id.due_date))
					.setText(mItem.getDueDate());
		}

		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.task_details, menu);
	}	
	
}
