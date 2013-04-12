package org.waldk33n3r.android.gnosis.games;

import org.waldk33n3r.android.gnosis.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v4.app.NavUtils;

public class Games extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games);
		findViewById(R.id.frog_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.frog_button:
				startActivity(new Intent(this, org.waldk33n3r.android.gnosis.games.frog.Main.class));
				break;
		}
	}

}
