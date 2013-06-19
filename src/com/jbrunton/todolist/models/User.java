package com.jbrunton.todolist.models;

import android.content.ContentValues;

import com.jbrunton.todolist.data.DataEntity;
import com.jbrunton.todolist.data.TodoListSQLiteOpenHelper;

public class User extends DataEntity {
	
	private String mUsername;
	
	public String getUsername() { return mUsername; }
	public void setUsername(String username) { mUsername = username; }
	
	public ContentValues getValues() {
		ContentValues values = super.getValues();
		values.put(TodoListSQLiteOpenHelper.COLUMN_USERNAME, getUsername());
		return values;
	}

}
