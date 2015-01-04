package com.amaker.wlo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class OrderActivity extends Activity {
	// Table No
	private Spinner tableNoSpinner;
	// Order,add a new dish, start
	private Button orderBtn, addBtn, startBtn;
	// Number of customers
	private EditText personNumEt;
	// Order list 
	private ListView lv;
	// Order ID
	private String orderId;
	// Items with this order
	private List data = new ArrayList();
	// Value with this order 
	private Map map;
	// Adapter of this listview
	private SimpleAdapter sa;
	// ListView data
	private String[] from = { "id", "name","num", "price", "remark" };
	// TextView Drawable ID
	private int[] to = new int[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		tableNoSpinner = (Spinner) findViewById(R.id.tableNoSpinner);
		setTableAdapter();
		
		startBtn = (Button) findViewById(R.id.startButton02);
		startBtn.setOnClickListener(startListener);
		
		addBtn = (Button) findViewById(R.id.addMealButton01);
		addBtn.setOnClickListener(addListener);
		orderBtn = (Button) findViewById(R.id.orderButton02);
		orderBtn.setOnClickListener(orderListener);
		
		personNumEt = (EditText) findViewById(R.id.personNumEditText02);
		
		lv = (ListView) findViewById(R.id.orderDetailListView01);
		setMenusAdapter();
	}
	
	

	private void setMenusAdapter(){

		to[0] = R.id.id_ListView;
		to[1] = R.id.name_ListView;
		to[2] = R.id.num_ListView;
		to[3] = R.id.price_ListView;
		to[4] = R.id.remark_ListView;

		sa = new SimpleAdapter(this, data, R.layout.listview, from, to);

		lv.setAdapter(sa);
	}
	
	

	private void setTableAdapter(){

	    Uri uri = Uri.parse("content://com.amaker.provider.TABLES/table");

		String[] projection = { "_id", "num", "description" };

		Cursor c = managedQuery(uri, projection, null, null, null);

		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, c,
				new String[] { "_id" }, new int[] { android.R.id.text1 });

		tableNoSpinner.setAdapter(adapter2);
	}
	
	

	private OnClickListener startListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy:mm:dd hh:MM");

			String orderTime = sdf.format(date);
		
			SharedPreferences pres = getSharedPreferences("user_msg",
					MODE_WORLD_READABLE);
			String userId = pres.getString("id", "");

			TextView tv = (TextView) tableNoSpinner.getSelectedView();
			String tableId = tv.getText().toString();

			String personNum = personNumEt.getText().toString();

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("orderTime", orderTime));
			params.add(new BasicNameValuePair("userId", userId));
			params.add(new BasicNameValuePair("tableId", tableId));
			params.add(new BasicNameValuePair("personNum", personNum));
			UrlEncodedFormEntity entity1=null;
			try {
				 entity1 =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			String url = HttpUtil.BASE_URL+"servlet/StartTableServlet";

			HttpPost request = HttpUtil.getHttpPost(url);

			request.setEntity(entity1);

			String result= HttpUtil.queryStringForPost(request);

			orderId = result;
			Toast.
			makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
		}
	};
	

	

	private OnClickListener addListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			addMeal();
		}
	};
	

	private void addMeal() {

		LayoutInflater inflater = LayoutInflater.from(this);

		final View v = inflater.inflate(R.layout.order_detail, null);

		final Spinner menuSpinner = (Spinner) v.findViewById(R.id.menuSpinner);

		EditText numEt = (EditText) v.findViewById(R.id.numEditText);

		EditText remarkEt = (EditText) v.findViewById(R.id.add_remarkEditText);
		

		Uri uri = Uri.parse("content://com.amaker.provider.MENUS/menu1");

		String[] projection = { "_id", "name", "price" };

		ContentResolver cr = getContentResolver();

		Cursor c = cr.query(uri, projection, null, null, null);

		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				R.layout.spinner_lo, c,
				new String[] { "_id", "price", "name" }, new int[] {
						R.id.id_TextView01, R.id.price_TextView02,
						R.id.name_TextView03, });

		menuSpinner.setAdapter(adapter2);
		

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder

		.setMessage("Please order")

		.setView(v)

		.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						
						LinearLayout v1 = (LinearLayout) menuSpinner
								.getSelectedView();
						
						TextView id_tv = (TextView) v1
								.findViewById(R.id.id_TextView01);

						TextView price_tv = (TextView) v1
								.findViewById(R.id.price_TextView02);

						TextView name_tv = (TextView) v1
								.findViewById(R.id.name_TextView03);

						EditText num_et = (EditText) v
								.findViewById(R.id.numEditText);

						EditText remark_et = (EditText) v
								.findViewById(R.id.add_remarkEditText);

						String idStr = id_tv.getText().toString();

						String priceStr = price_tv.getText().toString();

						String nameStr = name_tv.getText().toString();

						String numStr = num_et.getText().toString();

						String remarkStr = remark_et.getText().toString();
						

						map = new HashMap();

						map.put("id", idStr);
						map.put("name", nameStr);
						map.put("num", numStr);
						map.put("price", priceStr);
						map.put("remark", remarkStr);
						

						data.add(map);
						

						to[0] = R.id.id_ListView;
						to[1] = R.id.name_ListView;
						to[2] = R.id.price_ListView;
						to[3] = R.id.remark_ListView;
						

						sa = new SimpleAdapter(OrderActivity.this, data,
								R.layout.listview, from, to);

						lv.setAdapter(sa);

					}
				}).setNegativeButton("Cancel", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	

	private OnClickListener orderListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			for (int i = 0; i < data.size(); i++) {

				Map map = (Map)data.get(i);

				String menuId = (String) map.get("id");
				String num = (String)map.get("num");
				String remark = (String)map.get("remark");
				String myOrderId = orderId;
				

				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("menuId", menuId));
				params.add(new BasicNameValuePair("orderId", myOrderId));
				params.add(new BasicNameValuePair("num", num));
				params.add(new BasicNameValuePair("remark", remark));
				UrlEncodedFormEntity entity1=null;
				try {
					 entity1 =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				

				String url = HttpUtil.BASE_URL+"servlet/OrderDetailServlet";

				HttpPost request = HttpUtil.getHttpPost(url);

				request.setEntity(entity1);

				String result= HttpUtil.queryStringForPost(request);
				
				Toast.
				makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
				
				finish();
			}
		}
	};
}
