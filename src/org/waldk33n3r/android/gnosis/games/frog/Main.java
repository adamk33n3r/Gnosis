package org.waldk33n3r.android.gnosis.games.frog;

import org.waldk33n3r.android.gnosis.R;
import org.waldk33n3r.android.gnosis.R.layout;
import org.waldk33n3r.android.gnosis.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class Main extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frog_main);
		findViewById(R.id.frog_play_button).setOnClickListener(this);
		findViewById(R.id.frog_scores_button).setOnClickListener(this);
		findViewById(R.id.frog_back_button).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.frog_play_button:
				startActivity(new Intent(this, Game.class));
				break;
			case R.id.frog_scores_button:
				break;
			case R.id.frog_back_button:
				finish();
				break;
		}
	}

}
