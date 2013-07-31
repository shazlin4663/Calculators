package com.example.calculator2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends Activity {
	 public static final String PREFS_NAME = "MyPrefsFiless";
	 ListView listView;
	 List<String> list = new ArrayList<String>();
	 ArrayAdapter<String> arrayAd;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		listView = (ListView) findViewById(R.id.listView);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		if (!(settings.getString("resultHistory", "0").isEmpty()) )
			getStringHistory(settings.getString("resultHistory", "0"));
		
		arrayAd = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, 
				android.R.id.text1, list);
		
		listView.setAdapter(arrayAd);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent secondIntent = new Intent();
				secondIntent.putExtra("result", list.get(arg2));
				setResult(RESULT_OK, secondIntent);
				finish();
			}
		});
		
	}
	private void getStringHistory (String history) {
		 String[] tempHistory = history.split("[,]");
		 for (int x = 0; x < tempHistory.length; x++) {
			list.add(tempHistory[x]);
		 }
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		setResult(Activity.RESULT_CANCELED);
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
