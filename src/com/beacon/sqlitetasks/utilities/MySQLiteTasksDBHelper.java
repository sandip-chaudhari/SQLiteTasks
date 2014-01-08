package com.beacon.sqlitetasks.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteTasksDBHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_SQLTASKS 	= "sqltasks";
	public static final String COLUMN_ID 		= "_id";
	public static final String COLUMN_SQLTASK 	= "sqltask";

	private static final String DATABASE_NAME 	= "sqltasks.db";
	private static final int DATABASE_VERSION 	= 1;

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table "
		+ TABLE_SQLTASKS + "(" + COLUMN_ID
		+ " integer primary key autoincrement, " + COLUMN_SQLTASK
		+ " text not null);";

	public MySQLiteTasksDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteTasksDBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLTASKS);
		onCreate(db);
  }
	
}
