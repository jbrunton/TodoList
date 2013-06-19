package com.jbrunton.todolist.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jbrunton.todolist.models.Task;
import com.jbrunton.todolist.models.User;

public class UsersDataSource extends DataSource<User> {

	public UsersDataSource(Context context) {
		super(context, TodoListSQLiteOpenHelper.TABLE_USERS,
				new String[] {
				TodoListSQLiteOpenHelper.COLUMN_ID,
				TodoListSQLiteOpenHelper.COLUMN_USERNAME });
	}

	@Override
	public void open() {
		super.open();
		
		// urgh, test data in the wrong place
		if (getAll().size() == 0) {
			create("jbrunton");
			create("nwright");
		}
	}

	public User create(String username) {
		ContentValues values = new ContentValues();
		values.put(TodoListSQLiteOpenHelper.COLUMN_USERNAME, username);
		return create(values);
	}

	protected User createFromCursor(Cursor cursor) {
		User user = new User();
		user.setId(cursor.getLong(0));
		user.setUsername(cursor.getString(1));
		return user;
	}
} 
