package com.amaker.wlo;


import android.app.Activity;

import android.app.AlertDialog;

import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;


import com.amaker.util.HttpUtil;



public class LoginActivity extends Activity {
	
// Claim login and cancel button	
private Button cancelBtn,loginBtn;
	
// Claim user and password	
private EditText userEditText,pwdEditText;
	

@Override
	
public void onCreate(Bundle savedInstanceState) {
		
super.onCreate(savedInstanceState);
		
// Set titles		
setTitle("Wireless Order - User Login");
		
// Set layout for current layout
		
setContentView(R.layout.login_system);
		
// Initiaize a button by findViewById		
cancelBtn = (Button)findViewById(R.id.cancelButton);
		
// Initiaize a button by findViewById		
loginBtn = (Button)findViewById(R.id.loginButton);
		
// Initiaize a button by findViewById			
userEditText = (EditText)findViewById(R.id.userEditText);
		
// Initiaize a button by findViewById			
pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		
cancelBtn.setOnClickListener(new OnClickListener() {
			
@Override
			
public void onClick(View v) {
				
finish();
			
}
		
});
		
		

loginBtn.setOnClickListener(new OnClickListener() {
			
@Override
			
public void onClick(View v) {
				
if(validate()){
					
if(login()){
						
Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class
);
						
startActivity(intent);
					
}
else{
						
showDialog("User or password is worng, please try again£¡");					
}
				
}
			
}
		
});
	
}
	

// Login method
	
private boolean login(){
		
// Gain user name		
String username = userEditText.getText().toString();
		
// Gain password 		
String pwd = pwdEditText.getText().toString();
		
// Gain 
		
String result=query(username,pwd);
		
if(result!=null&&result.equals("0")){
		return false;
		
}
else{
			
saveUserMsg(result);
			
return true;
		
}
	
}
	
	
// Save user message 
	
private void saveUserMsg(String msg){
		
// User No
		
String id = "";
		
// User name		
String name = "";
		
// Gain infomation array
		
String[] msgs = msg.split(";");
		
int idx = msgs[0].indexOf("=");
		
id = msgs[0].substring(idx+1);
		
idx = msgs[1].indexOf("=");
		
name = msgs[1].substring(idx+1);
		
// Share message		
SharedPreferences pre = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
		
SharedPreferences.Editor editor = pre.edit();
	
editor.putString("id", id);
		
editor.putString("name", name);
		
editor.commit();
	
}
	
	
// Validate method	
private boolean validate(){
		
String username = userEditText.getText().toString();
		
if(username.equals("")){
		
showDialog("Please input user name£¡");
			
return false;
		
}
		
String pwd = pwdEditText.getText().toString();
		
if(pwd.equals("")){

showDialog("Please input password£¡");
			
return false;
		
}
		
return true;
	
}
	
private void showDialog(String msg){
		
AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
builder.setMessage(msg)
.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           
public void onClick(DialogInterface dialog, int id) {
		           
}
		       
});
		
AlertDialog alert = builder.create();
		
alert.show();
	
}
	
// Query by user name and password
private String query(String account,String password){
		
// Query parameters
		
String queryString = "account="+account+"&password="+password;
		
// URL
		
String url = HttpUtil.BASE_URL+"servlet/LoginServlet?"+queryString;
		
// Return result
		
return HttpUtil.queryStringForPost(url);
    
}

}