package com.amaker.provider;


import android.net.Uri;

import android.provider.BaseColumns;


/**
 
* @author KeXu
* Table Number
 
*/


public final class Tables  implements BaseColumns{
	    
public static final String AUTHORITY = "com.amaker.provider.TABLES";       
public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/table");
     
public static final String DEFAULT_SORT_ORDER = "num DESC";     
public static final String NUM = "num";				      
public static final String DESCRIPTION= "description";	}
