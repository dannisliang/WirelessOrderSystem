package com.amaker.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * @author KeXu
 * Tools for database
 */
public class DBHelper extends SQLiteOpenHelper{
    // Name of the Database
    private static final String DATABASE_NAME = "Wireless.db";
    // Version of the Database
    private static final int DATABASE_VERSION = 2;
    // Name of the Table
    public static final String TABLES_TABLE_NAME = "TableTbl";
    public static final String TABLES_TABLE_NAME2 = "MenuTbl";
	// Construction Method
	public DBHelper(Context context) {
		// Start a Database
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}

	// Create and visit
	public void onCreate(SQLiteDatabase db) {
       /* db.execSQL("CREATE TABLE " + TABLES_TABLE_NAME + " ("
                + Tables._ID + " INTEGER PRIMARY KEY,"
                + Tables.NUM + " TEXT,"
                + Tables.DESCRIPTION + " TEXT"
                + ");");
        */
        db.execSQL("CREATE TABLE " + TABLES_TABLE_NAME2 + " ("
                + Menus._ID + " INTEGER PRIMARY KEY,"
                + Menus.TYPE_ID + " INTEGER,"
                + Menus.NAME + " TEXT,"
                + Menus.PRICE + " INTEGER,"
                + Menus.PIC + " TEXT,"
                + Menus.REMARK + " TEXT"
                + ");");
	}

	// Set a version
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Delete the table
		//db.execSQL("DROP TABLE IF EXISTS TableTbl");
		db.execSQL("DROP TABLE IF EXISTS MenuTbl");
        onCreate(db);
	}

}
