package com.jbrunton.todolist.data;

import android.content.ContentValues;

public class DataEntity {
	
	private long mId;
	
	public long getId() { return mId; }
	public void setId(long id) { mId = id; }

	public ContentValues getValues() {
		ContentValues values = new ContentValues();
		return values;
	}
}
