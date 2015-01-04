package com.amaker.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MenuProvider extends ContentProvider{
	// Database helper
	private DBHelper dbHelper;
    // Uri tools
    private static final UriMatcher sUriMatcher;
    // Provisions for requesting and refreshing
    private static final int MENUS = 1;
    private static final int MENUS_ID = 2;
    // Search for columns
    private static HashMap<String, String> menuProjectionMap;
    static {
    	// Uri tools
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Menus.AUTHORITY, "menu1", MENUS);
        sUriMatcher.addURI(Menus.AUTHORITY, "menu1/#", MENUS_ID);
        menuProjectionMap = new HashMap<String, String>();
        menuProjectionMap.put(Menus._ID, Menus._ID);
        menuProjectionMap.put(Menus.NAME, Menus.NAME);
        menuProjectionMap.put(Menus.PIC, Menus.PIC);
        menuProjectionMap.put(Menus.PRICE, Menus.PRICE);
        menuProjectionMap.put(Menus.REMARK, Menus.REMARK);
        menuProjectionMap.put(Menus.TYPE_ID, Menus.TYPE_ID);
    }

	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		return true;
	}
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(DBHelper.TABLES_TABLE_NAME2,null, values);
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(Menus.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
		return null;
	}
	

	public String getType(Uri uri) {
		return null;
	}


	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        switch (sUriMatcher.match(uri)) {

	        case MENUS:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME2);
	            qb.setProjectionMap(menuProjectionMap);
	            break;
	        case MENUS_ID:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME2);
	            qb.setProjectionMap(menuProjectionMap);
	            qb.appendWhere(Menus._ID + "=" + uri.getPathSegments().get(1));
	            break;
	        default:
	            throw new IllegalArgumentException("Uri´íÎó£¡ " + uri);
	        }

	        String orderBy;
	        if (TextUtils.isEmpty(sortOrder)) {
	            orderBy = Menus.DEFAULT_SORT_ORDER;
	        } else {
	            orderBy = sortOrder;
	        }

	        SQLiteDatabase db = dbHelper.getReadableDatabase();
	        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
	        c.setNotificationUri(getContext().getContentResolver(), uri);
	        return c;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(DBHelper.TABLES_TABLE_NAME2, null, null);
		return 0;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}
