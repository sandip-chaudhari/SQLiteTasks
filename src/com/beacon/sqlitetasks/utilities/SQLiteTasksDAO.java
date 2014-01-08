package com.beacon.sqlitetasks.utilities;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.beacon.sqlitetasks.models.SQLiteTaskModel;

public class SQLiteTasksDAO {
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteTasksDBHelper dbHelper;
	private String[] allColumns = { MySQLiteTasksDBHelper.COLUMN_ID,
				MySQLiteTasksDBHelper.COLUMN_SQLTASK };

	public SQLiteTasksDAO(Context context) {
		dbHelper = new MySQLiteTasksDBHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public SQLiteTaskModel addSqlTask(String sqltask) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteTasksDBHelper.COLUMN_SQLTASK, sqltask);
		long insertId = database.insert(MySQLiteTasksDBHelper.TABLE_SQLTASKS, null,
							values);
		
		Cursor cursor = database.query(MySQLiteTasksDBHelper.TABLE_SQLTASKS,
				allColumns, MySQLiteTasksDBHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		
		cursor.moveToFirst();
		
		SQLiteTaskModel newSqlTask = cursorToSqlTask(cursor);
		cursor.close();
		return newSqlTask;
	}

	public List<SQLiteTaskModel> getAllSqlTasks() {
		List<SQLiteTaskModel> sqltasks = new ArrayList<SQLiteTaskModel>();

		Cursor cursor = database.query(MySQLiteTasksDBHelper.TABLE_SQLTASKS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			SQLiteTaskModel sqltask = cursorToSqlTask(cursor);
			sqltasks.add(sqltask);
			cursor.moveToNext();
		}
		
		// make sure to close the cursor
		cursor.close();
		return sqltasks;
  }

  private SQLiteTaskModel cursorToSqlTask(Cursor cursor) {
	  SQLiteTaskModel sqltask = new SQLiteTaskModel();
	  sqltask.setId(cursor.getLong(0));
	  sqltask.setSqlTask(cursor.getString(1));
	  
	  return sqltask;
  }
}
