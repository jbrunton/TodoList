package com.jbrunton.todolist.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jbrunton.todolist.models.User;

public abstract class DataSource<EntityT extends DataEntity> {
	
	// Database fields
	private SQLiteDatabase mDatabase;
	private TodoListSQLiteOpenHelper mHelper;
	private String mTableName;
	private String[] mColumns;

	
	protected abstract EntityT createFromCursor(Cursor cursor);
	
	public DataSource(Context context, String tableName, String[] columns) {
		mHelper = new TodoListSQLiteOpenHelper(context);
		mTableName = tableName;
		mColumns = columns;
	}

	public void open() throws SQLException {
		mDatabase = mHelper.getWritableDatabase();		
	}

	public void close() {
		mHelper.close();
	}

	public EntityT create(ContentValues values) {
		long insertId = mDatabase.insert(mTableName, null, values);
		Cursor cursor = mDatabase.query(mTableName, mColumns,
				TodoListSQLiteOpenHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		EntityT entity = createFromCursor(cursor);
		cursor.close();
		return entity;
	}

	public void delete(EntityT entity) {
		long id = entity.getId();
		System.out.println("Entity deleted from table " + mTableName + " with id: " + id);
		mDatabase.delete(mTableName, TodoListSQLiteOpenHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public void save(EntityT entity) {
		long id = entity.getId();
		
		ContentValues values = entity.getValues();
		
		System.out.println("User saved with id: " + id);
		mDatabase.update(mTableName, values,
				TodoListSQLiteOpenHelper.COLUMN_ID + " = " + id, null);
	}


	public EntityT find(long id) {
		Cursor cursor = mDatabase.query(mTableName, mColumns,
				TodoListSQLiteOpenHelper.COLUMN_ID + " = " + id, null,
				null, null, null);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			EntityT entity = createFromCursor(cursor);
			return entity;
		} else {
			return null;
		}
	}

	public List<EntityT> getAll() {
		List<EntityT> entities = new ArrayList<EntityT>();

		Cursor cursor = mDatabase.query(mTableName, mColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			EntityT entity = createFromCursor(cursor);
			entities.add(entity);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return entities;
	}
}
