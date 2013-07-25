package com.xperttech.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {


	private EditText firTextNum, secTextNum, operatorText;
	private Button cal;
	private TextView result;
	double firNum, secNum;
	String operator;

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
		setContentView(R.layout.activity_main);

		firTextNum = (EditText) findViewById(R.id.firNum);
		secTextNum = (EditText) findViewById(R.id.secNum);
		operatorText = (EditText) findViewById(R.id.operator);
		cal = (Button) findViewById(R.id.calculate);
		result = (TextView) findViewById(R.id.result);

		if (firTextNum.requestFocus()) {
		    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		}
		cal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				InputMethodManager inputManager = (InputMethodManager)  // 
						getSystemService(Context.INPUT_METHOD_SERVICE); // keyboard function

				inputManager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); // hide
																				// keyboard

				firNum = Double.parseDouble(firTextNum.getText().toString());
				secNum = Double.parseDouble(secTextNum.getText().toString());
				operator = operatorText.getText().toString();

				if (operator.equals("+"))
					result.setText("" + (firNum + secNum));
				else if (operator.equals("-"))
					result.setText("" + (firNum - secNum));

				else if (operator.equals("*"))
					result.setText("" + (firNum * secNum));

				else if (operator.equals("/"))
					result.setText("" + (firNum / secNum));
				else
					result.setText("Please enter correct operator.");
				runAnimation();

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
