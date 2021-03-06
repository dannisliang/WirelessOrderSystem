package com.amaker.wlo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LogoutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}
	
	
	private void logout(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you really want to quit��")
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	  SharedPreferences pres = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
		        	  SharedPreferences.Editor editor = pres.edit();
		        	  editor.putString("id", "");
		        	  editor.putString("name", "");
		        	  
		        	  Intent intent = new Intent();
		        	  intent.setClass(LogoutActivity.this, LoginActivity.class);
		        	  startActivity(intent);
		           }
		       }).setNegativeButton("Negative", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}
