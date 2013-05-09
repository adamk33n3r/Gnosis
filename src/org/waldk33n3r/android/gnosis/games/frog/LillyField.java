package org.waldk33n3r.android.gnosis.games.frog;

import java.util.ArrayList;

import android.util.Log;

public class LillyField {
	public ArrayList<LillyRow> rows = new ArrayList<LillyRow>(4);
	
	public LillyField() {
		
	}

	public boolean add(LillyRow row) {
		if (rows.size() == 4) {
			Log.e("FrogGame","Rows already full");
			return false;
		}
		rows.add(row);
		return true;
	}
}
