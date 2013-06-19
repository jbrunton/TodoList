package com.jbrunton.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.jbrunton.todolist.data.TasksDataSource;

/**
 * An activity representing a list of Tasks. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link TaskDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TaskListFragment} and the item details (if present) is a
 * {@link TaskDetailFragment}.
 * <p>
 * This activity also implements the required {@link TaskListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class TaskListActivity extends FragmentActivity implements
		TaskListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private static int TASK_DETAIL_REQUEST;
	
	private TasksDataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);
		
		mDataSource = new TasksDataSource(this);
		mDataSource.open();

		if (findViewById(R.id.task_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((TaskListFragment) getSupportFragmentManager().findFragmentById(
					R.id.task_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link TaskListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(long id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putLong(TaskDetailFragment.ARG_ITEM_ID, id);
			TaskDetailFragment fragment = new TaskDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.task_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, TaskDetailActivity.class);
			detailIntent.putExtra(TaskDetailFragment.ARG_ITEM_ID, id);
			startActivityForResult(detailIntent, TASK_DETAIL_REQUEST);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == TASK_DETAIL_REQUEST) {
				TaskListFragment taskListFragment = (TaskListFragment) getSupportFragmentManager().findFragmentById(
						R.id.task_list);
				taskListFragment.getAdapter().clear();
				taskListFragment.getAdapter().addAll(mDataSource.getAll());
			}
		}
	}
}
