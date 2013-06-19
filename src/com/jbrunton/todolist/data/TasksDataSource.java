package com.jbrunton.todolist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jbrunton.todolist.models.Task;

public class TasksDataSource extends DataSource<Task> {

	public TasksDataSource(Context context) {
		super(context, TodoListSQLiteOpenHelper.TABLE_TASKS,
				new String[] {
						TodoListSQLiteOpenHelper.COLUMN_ID,
						TodoListSQLiteOpenHelper.COLUMN_TITLE,
						TodoListSQLiteOpenHelper.COLUMN_DETAILS,
						TodoListSQLiteOpenHelper.COLUMN_COMPLETE });
	}

	@Override
	public void open() {
		super.open();

		// urgh, test data in the wrong place
		if (getAll().size() == 0) {
			create("Task 1");
			create("Task 2");
			create("Task 3");
		}
	}
	
	public Task create(String title) {
		ContentValues values = new ContentValues();
		values.put(TodoListSQLiteOpenHelper.COLUMN_TITLE, title);
		values.put(TodoListSQLiteOpenHelper.COLUMN_COMPLETE, 0);
		return create(values);
	}

	protected Task createFromCursor(Cursor cursor) {
		Task task = new Task();
		task.setId(cursor.getLong(0));
		task.setTitle(cursor.getString(1));
		task.setDetails(cursor.getString(2));
		task.setComplete(cursor.getInt(3) == 0 ? false : true);
		return task;
	}
} 
