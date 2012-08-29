package com.testterra.main;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class AlternativeDB {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_SETTINGS = "number_of_tests";
	
	
	
	
	private static final String DATABASE_NAME = "alt.sqlite";
	private static final String DATABASE_TABLE = "settings";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			
		}	
		
	
	}
	
	
	
	
	public AlternativeDB(Context c){
		ourContext = c;
	}

	public AlternativeDB open() throws SQLException{
		
		
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getReadableDatabase();
		return this;
	}
	
	
	 public void close(){
		 ourHelper.close();
	 }
	 
	 public long getSize() {
		 
		    String sql = "SELECT COUNT(*) FROM " + DATABASE_TABLE;
		    SQLiteStatement statement = ourDatabase.compileStatement(sql);
		    long count = statement.simpleQueryForLong();
		    return count;
		}

	
	

	public String getKEY_Numb(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_SETTINGS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String data = c.getString(1);
			return data;
		}
		return null;
	}
	
	public void DBexec(int new_test_numb){
		
		ourDatabase.execSQL("DELETE FROM 'settings' WHERE _id =  '1'");
		ourDatabase.execSQL("INSERT OR REPLACE  INTO 'settings' ('_id','number_of_tests') VALUES ('1','"+new_test_numb+"')");
		
	}
	
	
}
