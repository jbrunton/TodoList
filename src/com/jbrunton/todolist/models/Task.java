package com.jbrunton.todolist.models;

import com.jbrunton.todolist.data.DataEntity;

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
	
}
