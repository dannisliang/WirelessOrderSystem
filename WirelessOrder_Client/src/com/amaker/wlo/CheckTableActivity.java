package com.amaker.wlo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaker.util.CheckTable;
import com.amaker.util.HttpUtil;
/**
 * Check the availablity of a table
 * @author KeXu
 */
public class CheckTableActivity extends Activity{
	// Display GridView of each table
	private GridView gv;
	// Number of the table
	private int count;
	// Save a list of tables
	private List list = new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set up titles
		setTitle("Wireless order systems-View");
		// Set layout of current activity
		setContentView(R.layout.check_table);
		// Instantiate
        gv = (GridView) findViewById(R.id.check_table_gridview);
        //Get the list of table
        getTableList();
        // Bundle data
        gv.setAdapter(new ImageAdapter(this));
	}
	
	// Gain status of tables
	private void getTableList(){
		// Visit URL
		String url = HttpUtil.BASE_URL+"servlet/CheckTableServlet";
		// Request result
		String result = HttpUtil.queryStringForPost(url);
		// Split the object and transfer into a list
		String[] strs = result.split(";");
		for (int i = 0; i < strs.length; i++) {
			int idx = strs[i].indexOf(",");
			int num = Integer.parseInt(strs[i].substring(0, idx));
			int flag = Integer.parseInt(strs[i].substring(idx+1));
			CheckTable ct = new CheckTable();
			ct.setFlag(flag);
			ct.setNum(num);
			list.add(ct);
		}
	}
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        public ImageAdapter(Context c) {
            mContext = c;
        }
        public int getCount() {
            return list.size();
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

        	LayoutInflater inflater = LayoutInflater.from(CheckTableActivity.this);
        	View v = null;
        	ImageView imageView =null;
        	TextView tv =null;
            if (convertView == null) {
            	v = inflater.inflate(R.layout.check_table_view,null);
                v.setPadding(8, 8, 8, 8);
            } else {
                v = (View) convertView;
            }
            imageView = (ImageView) v.findViewById(R.id.check_table_ImageView01);
            tv = (TextView) v.findViewById(R.id.check_tableTextView01);
            CheckTable ct = (CheckTable) list.get(position);
            if(ct.getFlag()==1){
            	// Set the symbol as occupied
            	imageView.setImageResource(R.drawable.youren);
            }else{
            	// Set the symbol as empty
            	imageView.setImageResource(R.drawable.kongwei);
            }
            // Set table number 
            tv.setText(ct.getNum()+"");
            return v;
        }
    }
}
