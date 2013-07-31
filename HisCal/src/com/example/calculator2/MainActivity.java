package com.example.calculator2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFiless";
	static final String LOG_TAG ="MAIN ACTIVITY";
	GridView gridview;
	TextView textView;
	String textEquation = "";
	static String storeResult = "";
	char operation;
	Button btn;
	static int countHistory = 0;
	static boolean refreshResult = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		gridview = (GridView) findViewById(R.id.gridView1);  
		textView = (TextView) findViewById(R.id.editText);
		
		
		gridview.setAdapter(new ButtonAdapter(this));	
		btn = (Button) findViewById(R.id.historyBtn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		//	startActivity(new Intent(MainActivity.this, SecondActivity.class));
				startActivityForResult(intent, 101);
		}
	});
}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 101 && resultCode == Activity.RESULT_OK) {	
			String result = data.getStringExtra("result");
			textView.setText(result);
		}
	}
	
	public void saveHistory(String saveResult) {
		SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = setting.edit();
		editor.putString("resultHistory", saveResult);	
		editor.commit();
		}
		
	private double getLeftNum (int position) {
		String strNumTemp = "";
		
		for (int leftPos = position-1; leftPos >= 0; leftPos--) {
			if ( Character.isDigit(textEquation.charAt(leftPos)) 
					|| textEquation.charAt(leftPos) == '.' || textEquation.charAt(0) == '-') {
				strNumTemp = strNumTemp.concat(textEquation.charAt(leftPos) + "");
			}
			else
				leftPos = -1;
		}
		
		StringBuilder reverse = new StringBuilder(strNumTemp);
		//log.i();
		return Double.parseDouble(reverse.reverse().toString());
	}
	
	private double getRightNum (int position) {
		String strNumTemp = "";
		
		for (int rightPos = position+1; rightPos < textEquation.length(); rightPos++) {
			if ( Character.isDigit(textEquation.charAt(rightPos)) 
					|| textEquation.charAt(rightPos) == '.' || textEquation.charAt(0) == '-') {
				strNumTemp = strNumTemp.concat(textEquation.charAt(rightPos) + "");
			}
			else
				rightPos = -1;
		}
		
		return Double.parseDouble(strNumTemp);
	}
	private double calculateNumber() {
		double firNum, secNum, total = 0;
		
		for (int index = 1; index < textEquation.length(); index++) {
			if (textEquation.charAt(index) == '*') {
				firNum = getLeftNum(index);
				secNum = getRightNum(index);
				total = firNum * secNum;
			
			}
			else if (textEquation.charAt(index) == '/') {
				firNum = getLeftNum(index);
				secNum = getRightNum(index);
				total = firNum / secNum;
	
			}
			else if (textEquation.charAt(index) == '-') {
				firNum = getLeftNum(index);
				secNum = getRightNum(index);
				total = firNum - secNum;			
			}
			else if (textEquation.charAt(index) == '+') {
				firNum = getLeftNum(index);
				secNum = getRightNum(index);
				total = firNum + secNum;
			}
		}
		return total;
	}
	private int checkFormatOfEquation(String equation) {
		int flag = 0;
		if (equation.endsWith("+") || equation.endsWith("-") 
				|| equation.endsWith("*") || equation.endsWith("/")) {
			return 0; // if last character contains operator return 0
		}
		for (int x = 1; x < equation.length(); x++) {
			if (equation.charAt(x) == '-')
				flag++;
			if (equation.charAt(x) == '+')
				flag++;
			if (equation.charAt(x) == '*')
				flag++;
			if (equation.charAt(x) == '/')
				flag++;
		}
		return flag; // check for more than one operators
	}
	public void onCalculatorButtonClicked(String text) {
		double result;
		String[] tempStr;
		
		if (refreshResult) {
			if (Character.isDigit(text.charAt(0)))
				textEquation = "";
			refreshResult = false;
		}
		if (text.equals("AC")) {
			textView.setText("");
			textEquation = "";
		}
		else if (text.equals("=")) {
			// check if is empty, don't calculate 
			// if user enters wrong format of equation don't calculate
			if (!textEquation.isEmpty()) {
				if (checkFormatOfEquation(textEquation) == 1) {
					result = calculateNumber();
					
					textView.setText("" + result); 
					textEquation = result + "";
					refreshResult = true;
					storeResult = storeResult.concat(textEquation) + ",";
				
					if (countHistory == 9) {
						tempStr = storeResult.split("[,]");
						tempStr[0] = "";
						storeResult = "";
						for (int x = 1; x < tempStr.length; x++) {
							storeResult = storeResult.concat(tempStr[x]) + ",";
						}
						countHistory--;
					}
					
					saveHistory(storeResult);
					
					countHistory++;
				}
			}
			
			}
		else if (text.equals("Delete")) {
			if (!textEquation.isEmpty())
				textEquation = textEquation.substring(0, textEquation.length()-1);
				textView.setText(textEquation);
		}
		else {
			textEquation = textEquation.concat(text);
			textView.setText(textEquation);
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
