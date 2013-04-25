package org.waldk33n3r.android.gnosis.games.frog;

import org.waldk33n3r.android.gnosis.games.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class LillyPad extends Entity {
	
	
	private int dir;
	private String text;
	private Paint paint;
	
	/**
	 * LillyPad
	 * @param flowdir - Either 0 or 1
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param lilly - Bitmap to draw
	 */
	public LillyPad(String question, int flowdir, float x, float y, float width, float height, Bitmap lilly) {
		super(x, y, width, height, lilly);
		this.dir = flowdir;
		this.text = question;
		this.paint = new Paint();
		this.paint.setTextSize(36);
	}
	
	/**
	 * Move based on flow
	 * @param view
	 * @param dist
	 */
	
	public void move(View view, int dist) {
		if (this.dir == 1)
			moveRight(view, dist);
		else
			moveLeft(view, dist);
	}

	public void moveRight(View view, int dist) {
		float newPos = (x + dist) % view.getWidth();
		x = newPos < x ? newPos - 200/*width*/ : newPos;
	}
	
	public void moveLeft(View view, int dist) {
		x = x - dist < -200/*width*/ ? view.getWidth() - dist : x - dist;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText(text, x, y, paint);
	}
	
	

}
