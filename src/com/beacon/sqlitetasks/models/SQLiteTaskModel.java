package com.beacon.sqlitetasks.models;

public class SQLiteTaskModel {
	private long id;
	private String sqltask;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSqlTask() {
    return sqltask;
  }

  public void setSqlTask(String sqltask) {
    this.sqltask = sqltask;
  }

  // Will be used by the ArrayAdapter in the ListView
  @Override
  public String toString() {
    return sqltask;
  }
}
