package com.amaker.wlo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amaker.provider.Menus;
import com.amaker.util.HttpUtil;
/**
 * 
 * @author KeXu
 * Synchronizing tool
 */
public class UpdateActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Wireless Order System - Data Synchronizing");

		ListView listView = getListView();

		String[] items = {"Refreshing Menu[MenuTbl]", "Refreshing Table[TableTbl]" };

		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {

		case 0:
			confirm(1);
			break;

		case 1:
			confirm(2);
			break;
		default:
			break;
		}
	}

	private void confirm(final int item) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Refresh?").setCancelable(false).setPositiveButton(
				"OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (item == 1) {

							updateMenu();
						} else {

							updateTable();
						}
					}
				}).setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void updateMenu() {

		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet";
		try {

			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(in);
			NodeList nl = doc.getElementsByTagName("menu");
			ContentResolver cr = getContentResolver();
			Uri uri1 = Menus.CONTENT_URI;

			cr.delete(uri1, null, null);


			for (int i = 0; i < nl.getLength(); i++) {
				ContentValues values = new ContentValues();
				int id = Integer.parseInt(doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue());
				String name = doc.getElementsByTagName("name").item(i)
						.getFirstChild().getNodeValue();
				String pic = doc.getElementsByTagName("pic").item(i)
						.getFirstChild().getNodeValue();
				int price = Integer.parseInt(doc.getElementsByTagName("price")
						.item(i).getFirstChild().getNodeValue());
				int typeId = Integer.parseInt(doc
						.getElementsByTagName("typeId").item(i).getFirstChild()
						.getNodeValue());

				String remark = doc.getElementsByTagName("remark").item(i)
						.getFirstChild().getNodeValue();
				

				values.put("_id", id);
				values.put("name", name);
				values.put("price", price);
				values.put("pic", pic);
				values.put("typeId", typeId);
				values.put("remark", remark);

				cr.insert(uri1, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTable() {

	}
}
