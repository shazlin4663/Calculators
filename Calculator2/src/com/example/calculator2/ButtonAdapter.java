package com.example.calculator2;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class ButtonAdapter extends BaseAdapter {  
    protected static final String LOG_TAG = "ButtonAdapter";
	private MainActivity mContext;  
    public String[] numPad = {   
            "AC","Delete",
            "7", "8","9","-",
            "4","5","6","+",
            "1","2","3","*",
            "0",".","=","/",
            };  
    
    // Gets the context so it can be used later  
    public ButtonAdapter(MainActivity c) {  
     mContext = c;  
    }  
     
    // Total number of things contained within the adapter  
    public int getCount() {  
     return numPad.length;  
    }  
     
     // Require for structure, not really used in my code.  
    public Object getItem(int position) {  
     return null;  
    }  
     
    // Require for structure, not really used in my code. Can  
    // be used to get the id of an item in the adapter for   
    // manual control.   
    public long getItemId(int position) {  
     return position;  
    }  

	@Override     
    public View getView(final int position,   
                              View convertView, ViewGroup parent) {  
     Button btn;  

     if (convertView == null) {

      // if it's not recycled, initialize some attributes  
      btn = new Button(mContext);
      btn.setLayoutParams(new GridView.LayoutParams(100, 100));     
      btn.setPadding(8, 8, 8, 8);     
      }
     
     else {  
      btn = (Button) convertView;  
     }  
     
     btn.setText(numPad[position]);   
     btn.setTextColor(Color.MAGENTA);    
     btn.setId(position);  
     btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		//	Log.i(LOG_TAG, "Item Clicked");
			mContext.onCalculatorButtonClicked(numPad[position]);
		}
	});
     return btn;  
    }
  
   }  