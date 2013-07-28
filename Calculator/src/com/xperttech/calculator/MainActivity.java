package com.xperttech.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText firTextNum, secTextNum;
	private TextView result;
	double firNum, secNum;
	String[] operator = new String[] { "+", "-", "*", "/" };
	int operationIndex;
	private Spinner operationsSpinner;
	boolean firstText = false, secondText = false;

	private void runAnimation() {
		Animation test = AnimationUtils.loadAnimation(this, R.anim.scale);
		test.reset();
		result.clearAnimation();
		result.startAnimation(test);
		/*
		 * test = AnimationUtils.loadAnimation(this, R.anim.rotate);
		 * test.reset(); result.clearAnimation(); result.startAnimation(test);
		 */

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // setup content view

		// cal = (Button) findViewById(R.id.calculate);
		// operatorText = (EditText) findViewById(R.id.operator);

		firTextNum = (EditText) findViewById(R.id.firNum);
		secTextNum = (EditText) findViewById(R.id.secNum);
		operationsSpinner = (Spinner) findViewById(R.id.operator);

		result = (TextView) findViewById(R.id.result);

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, operator);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		operationsSpinner.setAdapter(arrayAdapter);

		if (firTextNum.requestFocus()) {
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		}

		operationsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						operationIndex = arg0.getSelectedItemPosition();
						showResult();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		firTextNum.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (!(firTextNum.getText().toString().matches(""))) {
					firNum = Double.parseDouble(firTextNum.getText().toString());
					showResult();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		secTextNum.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (!(secTextNum.getText().toString().matches(""))) {

					secNum = Double.parseDouble(secTextNum.getText().toString());
					showResult();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

		/*
		 * cal.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { InputMethodManager
		 * inputManager = (InputMethodManager) //
		 * getSystemService(Context.INPUT_METHOD_SERVICE); // keyboard function
		 * 
		 * inputManager.hideSoftInputFromWindow(getCurrentFocus()
		 * .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); // hide //
		 * keyboard
		 * 
		 * firNum = Double.parseDouble(firTextNum.getText().toString()); secNum
		 * = Double.parseDouble(secTextNum.getText().toString()); operator =
		 * operatorText.getText().toString();
		 * 
		 * if (operator.equals("+")) result.setText("" + (firNum + secNum));
		 * else if (operator.equals("-")) result.setText("" + (firNum -
		 * secNum));
		 * 
		 * else if (operator.equals("*")) result.setText("" + (firNum *
		 * secNum));
		 * 
		 * else if (operator.equals("/")) result.setText("" + (firNum /
		 * secNum)); else result.setText("Please enter correct operator.");
		 * runAnimation(); } });
		 */
	}

	void showResult() {
		if (!(firTextNum.getText().toString().matches(""))
				&& !(secTextNum.getText().toString().matches(""))) {
		if (operator[operationIndex].equals("+"))
			result.setText("" + (firNum + secNum));
		else if (operator[operationIndex].equals("-"))
			result.setText("" + (firNum - secNum));

		else if (operator[operationIndex].equals("*"))
			result.setText("" + (firNum * secNum));

		else if (operator[operationIndex].equals("/"))
			result.setText("" + (firNum / secNum));
		else
			result.setText("Please enter correct operator.");
		runAnimation();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
