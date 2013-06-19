package com.jbrunton.todolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TodoListSQLiteOpenHelper extends SQLiteOpenHelper {
	public static final String TABLE_TASKS = "tasks";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DETAILS = "details";
	public static final String COLUMN_COMPLETE = "complete";
	
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERNAME = "username";

	private static final String DATABASE_NAME = "tasks.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String TASKS_CREATE = "create table "
			+ TABLE_TASKS + "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_TITLE + " text not null, "
			+ COLUMN_DETAILS + " text, "
			+ COLUMN_COMPLETE + " integer not null);";

	private static final String USERS_CREATE = "create table "
			+ TABLE_TASKS + "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_USERNAME + " text not null);";
	
	private static final String DATABASE_CREATE = TASKS_CREATE + USERS_CREATE;

	public TodoListSQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TodoListSQLiteOpenHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	} 
}
