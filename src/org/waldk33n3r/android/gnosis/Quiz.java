package org.waldk33n3r.android.gnosis;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;

public class Quiz extends Activity {

	// A reference to start game button
    View btn_startGame;
 
    // Activity reference
    Activity activity;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
 
      // Request application to run in fullscreen mode
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
 
      // save this activity's reference
      activity = this;
 
      // set content view to main.xml file
      setContentView(R.layout.quiz);
    }
}
