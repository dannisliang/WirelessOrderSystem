package com.amaker.provider;


import android.net.Uri;

import android.provider.BaseColumns;



/**
 * 
@author KeXu
 
* Table Information 
*/


public final class Menus implements BaseColumns{
		    
public static final String AUTHORITY = "com.amaker.provider.MENUS";// Visit Uri
    
public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/menu1");   
public static final String DEFAULT_SORT_ORDER = "typeId DESC";       
public static final String TYPE_ID = "typeId";			     
public static final String NAME= "name";				// Name
        
public static final String PRICE= "price";				// Price
        
public static final String PIC= "pic";					// Pictures       
public static final String REMARK= "remark";			// Remark

}
