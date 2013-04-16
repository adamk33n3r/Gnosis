package org.waldk33n3r.android.gnosis.games.frog;

import org.waldk33n3r.android.gnosis.games.Entity;

import android.graphics.Bitmap;

public class LillyPad extends Entity {
		
	/**
	 * LillyPad
	 * @param x - x coord
	 * @param y - y coord
	 * @param scale - percent of screen
	 */
	public LillyPad(float x, float y, float scale, Bitmap lilly) {
		this(x, y, scale / 100, scale / 100, lilly);
	}
	
	private LillyPad(float x, float y, float width, float height, Bitmap lilly) {
		super(x, y, width, height, lilly);
	}

	
	

}
