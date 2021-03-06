package org.waldk33n3r.android.gnosis.games.frog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.waldk33n3r.android.gnosis.R;
import org.waldk33n3r.android.gnosis.R.layout;
import org.waldk33n3r.android.gnosis.R.menu;
import org.waldk33n3r.android.gnosis.games.User;
import org.waldk33n3r.android.gnosis.games.UserDatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;

public class Main extends Activity implements OnClickListener {

	TableLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frog_main);
		findViewById(R.id.frog_play_button).setOnClickListener(this);
		findViewById(R.id.frog_back_button).setOnClickListener(this);
		layout = (TableLayout) findViewById(R.id.frog_scores_tablelayout);

	}

	@Override
	public void onResume() {
		super.onResume();
		layout.removeAllViews();

		UserDatabaseHandler db = new UserDatabaseHandler(this);
		ArrayList<TextView> tViews = new ArrayList<TextView>();
		TextView tView = new TextView(this);
		tView.setText("High Scores");
		tView.setGravity(Gravity.CENTER);
		tView.setTextSize(30);
		tView.setTypeface(null, Typeface.BOLD);
		tViews.add(tView);
		List<User> list = db.getAllUsers();
		Collections.sort(list, new Comparator<User>() {

			@Override
			public int compare(User lhs, User rhs) {
				return rhs.getScore() - lhs.getScore();
			}
			
		});
		for (User user : list) {
			TextView tmp = new TextView(this);
			tmp.setText(user.getUsername() + ": " + user.getScore());
			tmp.setGravity(Gravity.CENTER);
			tmp.setTextSize(20);
			tViews.add(tmp);
		}
		for (TextView view : tViews)
			layout.addView(view);
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
			case R.id.frog_back_button:
				finish();
				break;
		}
	}

}
