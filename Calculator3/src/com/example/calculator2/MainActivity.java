package com.example.calculator2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends Activity {
	static final String LOG_TAG ="MAIN ACTIVITY";
	GridView gridview;
	TextView textView;
	String textEquation = "";
	static boolean showTest = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		gridview = (GridView) findViewById(R.id.gridView1);  
		textView = (TextView) findViewById(R.id.editText);

		gridview.setAdapter(new ButtonAdapter(this));		
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
	
	public void onCalculatorButtonClicked(String text) {
		double firNum, secNum, result = 0;
		
		if (showTest) {
			if (Character.isDigit(text.charAt(0)))
				textEquation = "";
				
			showTest = false;
	//		Log.i(LOG_TAG, text.charAt(0)+"");
		}
		
		if (text.equals("AC")) {
			textView.setText("");
			textEquation = "";
		}
		else if (text.equals("=")) {
			for (int index = 1; index < textEquation.length(); index++) {
				if (textEquation.charAt(index) == '*') {
					firNum = getLeftNum(index);
					secNum = getRightNum(index);
					result = firNum * secNum;
				}
				else if (textEquation.charAt(index) == '/') {
					firNum = getLeftNum(index);
					secNum = getRightNum(index);	
					result = firNum / secNum;
				}
				else if (textEquation.charAt(index) == '-') {
					firNum = getLeftNum(index);
					secNum = getRightNum(index);	
					result = firNum - secNum;
				}
				else if (textEquation.charAt(index) == '+') {
					firNum = getLeftNum(index);
					secNum = getRightNum(index);
					result = firNum + secNum;
				}
			}
			textView.setText("" + result);
			textEquation = result + "";
			showTest = true;
		}
		else if (text.equals("Delete")) {
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
