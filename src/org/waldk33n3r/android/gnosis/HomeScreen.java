package org.waldk33n3r.android.gnosis;

import org.waldk33n3r.android.gnosis.bible.Bible;
import org.waldk33n3r.android.gnosis.games.frog.Main;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeScreen extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.home_screen);

		// Add event listener to buttons here
		this.findViewById(R.id.frog_button).setOnClickListener(this);
		View bibleButton = this.findViewById(R.id.bible_button);
		bibleButton.setOnClickListener(this);
		View aboutButton = this.findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.frog_button:
				startActivity(new Intent(this, Main.class));
				break;
			case R.id.bible_button:
				startActivity(new Intent(this, Bible.class));
				break;
			case R.id.stats_button:
				startActivity(new Intent(this, Statistics.class));
				break;
			case R.id.about_button:
				startActivity(new Intent(this, About.class));
				break;
			default:
				Log.e("Activity", "Loading new activity");
				startActivity(new Intent(this, About.class));
				Log.e("Activity", "Ending new activity");
				break;
		}
	}
}

/*
 * @Override public void onCreate(Bundle savedInstance){
 * super.onCreate(savedInstance); setContentView(R.layout.home_screen);
 * 
 * //Add event listener to buttons here View quizButton =
 * this.findViewById(R.id.quiz_button); quizButton.setOnClickListener(this);
 * View bibleButton = this.findViewById(R.id.bible_button);
 * bibleButton.setOnClickListener(this); View statsButton =
 * this.findViewById(R.id.stats_button); statsButton.setOnClickListener(this);
 * View aboutButton = this.findViewById(R.id.about_button);
 * aboutButton.setOnClickListener(this); }
 * 
 * @Override public void onClick(View v){ switch(v.getId()){ case
 * R.id.quiz_button: startActivity(new Intent(this, QuizMenu.class)); break;
 * case R.id.bible_button: startActivity(new Intent(this, Bible.class)); break;
 * case R.id.stats_button: startActivity(new Intent(this, Statistics.class));
 * break; case R.id.about_button: startActivity(new Intent(this, About.class));
 * break; } }
 * 
 * @Override public boolean onCreateOptionsMenu(Menu menu) {
 * super.onCreateOptionsMenu(menu); MenuInflater inflater = getMenuInflater();
 * inflater.inflate(R.menu.menu, menu); return true; }
 * 
 * 
 * <Button android:id="@+id/quiz_button" android:layout_width="fill_parent"
 * android:layout_height="wrap_content" android:text="@string/quiz_label"
 * android:gravity="center" android:textSize="25sp" /> <Button
 * android:id="@+id/bible_button" android:layout_width="fill_parent"
 * android:layout_height="wrap_content" android:text="@string/bible_label"
 * android:textSize="25sp" android:gravity="center" /> <Button
 * android:id="@+id/stats_button" android:layout_width="fill_parent"
 * android:layout_height="wrap_content" android:text="@string/stats_label"
 * android:textSize="25sp" android:gravity="center" /> <Button
 * android:id="@+id/about_button" android:layout_width="fill_parent"
 * android:layout_height="wrap_content" android:text="@string/about_label"
 * android:textSize="25sp" android:gravity="center" />
 */
