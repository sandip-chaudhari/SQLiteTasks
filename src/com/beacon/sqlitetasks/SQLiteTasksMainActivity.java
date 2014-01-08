package com.beacon.sqlitetasks;

import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.beacon.sqlitetasks.utilities.SQLiteTasksDAO;
import com.beacon.sqlitetasks.models.SQLiteTaskModel;

public class SQLiteTasksMainActivity extends ListActivity {
	private SQLiteTasksDAO datasource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_tasks_main);

		datasource = new SQLiteTasksDAO(this);
		datasource.open();

		List<SQLiteTaskModel> values = datasource.getAllSqlTasks();

		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<SQLiteTaskModel> adapter = new ArrayAdapter<SQLiteTaskModel>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
  }

	// Will be called via the onClick attribute
	// of the buttons in activity_sqlite_tasks_main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<SQLiteTaskModel> adapter = (ArrayAdapter<SQLiteTaskModel>) getListAdapter();
		SQLiteTaskModel sqltask = null;
		switch (view.getId()) {
		
		case R.id.add:
			String[] sqltasks = new String[] { "Cool", "Very nice", "Hate it" };
			int nextInt = new Random().nextInt(3);
			
			// save the new sqltask to the database
			sqltask = datasource.addSqlTask(sqltasks[nextInt]);
			adapter.add(sqltask);
			break;
		}
		
		adapter.notifyDataSetChanged();
  }

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
