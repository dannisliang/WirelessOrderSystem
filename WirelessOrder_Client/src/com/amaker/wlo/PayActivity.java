package com.amaker.wlo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class PayActivity extends Activity{

	private WebView wv;
	private Button queryBtn,payBtn;
	private EditText orderIdEt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.pay);

		wv = (WebView)findViewById(R.id.pay_webview);

		queryBtn = (Button)findViewById(R.id.pay_query_Button01);

		payBtn = (Button)findViewById(R.id.pay_Button01);

		orderIdEt = (EditText)findViewById(R.id.pay_order_number_EditText01);
		

		queryBtn.setOnClickListener(queryListener);

		payBtn.setOnClickListener(payListener);
	}
	

	OnClickListener queryListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			String orderId = orderIdEt.getText().toString();

			String url = HttpUtil.BASE_URL+"servlet/PayServlet?id="+orderId;

			wv.loadUrl(url);
		}
	};
	

	OnClickListener payListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			String orderId = orderIdEt.getText().toString();

			String url = HttpUtil.BASE_URL+"servlet/PayMoneyServlet?id="+orderId;

			String result = HttpUtil.queryStringForPost(url);

			Toast.makeText(PayActivity.this, result, Toast.LENGTH_LONG).show();

			payBtn.setEnabled(false);
		}
	};

}
