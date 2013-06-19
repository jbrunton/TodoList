package com.jbrunton.todolist.models;

import android.content.ContentValues;

import com.jbrunton.todolist.data.DataEntity;
import com.jbrunton.todolist.data.TodoListSQLiteOpenHelper;

public class Task extends DataEntity {

	private String mTitle;
	private String mDetails;
	private boolean mComplete;
	
	public String getTitle() { return mTitle; }
	public void setTitle(String title) { mTitle = title; }
	
	public String getDetails() { return mDetails; }
	public void setDetails(String details) { mDetails = details; }
	
	public boolean getComplete() { return mComplete; }
	public void setComplete(boolean complete) { mComplete = complete; }
	
	public ContentValues getValues() {
		ContentValues values = super.getValues();
		values.put(TodoListSQLiteOpenHelper.COLUMN_TITLE, getTitle());
		values.put(TodoListSQLiteOpenHelper.COLUMN_DETAILS, getDetails());
		values.put(TodoListSQLiteOpenHelper.COLUMN_COMPLETE, getComplete() ? 1 : 0);
		return values;
	}

}
