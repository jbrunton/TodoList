package com.jbrunton.todolist.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jbrunton.todolist.models.Task;

public class TasksDataSource {

	// Database fields
	private SQLiteDatabase database;
	private TodoListSQLiteOpenHelper dbHelper;
	private String[] allColumns = {
			TodoListSQLiteOpenHelper.COLUMN_ID,
			TodoListSQLiteOpenHelper.COLUMN_TITLE,
			TodoListSQLiteOpenHelper.COLUMN_DETAILS,
			TodoListSQLiteOpenHelper.COLUMN_COMPLETE };

	public TasksDataSource(Context context) {
		dbHelper = new TodoListSQLiteOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		
		// urgh, test data in the wrong place
		if (getAllTasks().size() == 0) {
			createTask("Task 1");
			createTask("Task 2");
			createTask("Task 3");
		}
	}

	public void close() {
		dbHelper.close();
	}

	public Task createTask(String title) {
		ContentValues values = new ContentValues();
		values.put(TodoListSQLiteOpenHelper.COLUMN_TITLE, title);
		values.put(TodoListSQLiteOpenHelper.COLUMN_COMPLETE, 0);
		long insertId = database.insert(TodoListSQLiteOpenHelper.TABLE_TASKS,
				null, values);
		Cursor cursor = database.query(TodoListSQLiteOpenHelper.TABLE_TASKS,
				allColumns, TodoListSQLiteOpenHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Task task = cursorToTask(cursor);
		cursor.close();
		return task;
	}

	public void deleteTask(Task task) {
		long id = task.getId();
		System.out.println("Task deleted with id: " + id);
		database.delete(TodoListSQLiteOpenHelper.TABLE_TASKS, TodoListSQLiteOpenHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public void saveTask(Task task) {
		long id = task.getId();
		
		ContentValues values = new ContentValues();
		values.put(TodoListSQLiteOpenHelper.COLUMN_TITLE, task.getTitle());
		values.put(TodoListSQLiteOpenHelper.COLUMN_COMPLETE, task.getComplete());
		values.put(TodoListSQLiteOpenHelper.COLUMN_DETAILS, task.getDetails());
		
		System.out.println("Task saved with id: " + id);
		database.update(TodoListSQLiteOpenHelper.TABLE_TASKS, values,
				TodoListSQLiteOpenHelper.COLUMN_ID + " = " + id, null);
	}
	
	public Task find(long id) {
		for (Task task : getAllTasks()) {
			if (task.getId() == id) {
				return task;
			}
		}
		return null;
	}

	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<Task>();

		Cursor cursor = database.query(TodoListSQLiteOpenHelper.TABLE_TASKS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Task task = cursorToTask(cursor);
			tasks.add(task);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return tasks;
	}

	private Task cursorToTask(Cursor cursor) {
		Task task = new Task();
		task.setId(cursor.getLong(0));
		task.setTitle(cursor.getString(1));
		return task;
	}
} 
