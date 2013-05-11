package org.waldk33n3r.android.gnosis.bible;

import org.waldk33n3r.android.gnosis.R;
import org.waldk33n3r.android.gnosis.R.layout;
import org.waldk33n3r.android.gnosis.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BibleViewPage extends Activity {

	 /** Called when the activity is first created. */
	 	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        String[] allVerses = this.getIntent().getExtras().getStringArray("array");
	        setContentView(R.layout.bible_view_page);
	        View linearLayout =  findViewById(R.id.info);
	        //LinearLayout layout = (LinearLayout) findViewById(R.id.info);

	        for(int i=0;i<allVerses.length;i++){
	        	TextView valueTV = new TextView(this);
		        valueTV.setText(String.valueOf(i+1) + " " + allVerses[i]);
		        valueTV.setId(i+5);
		        valueTV.setTextSize(16);
		        valueTV.setPadding(0, 0, 0, 0);
		        valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));

		        ((LinearLayout) linearLayout).addView(valueTV);
			}
	    }
	 	
	 	

}
