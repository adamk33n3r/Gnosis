package org.waldk33n3r.android.gnosis.games.frog;

import org.waldk33n3r.android.gnosis.games.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

public class LillyPad extends Entity {

	private int dir;
	private String text;
	private TextPaint paint;
	private StaticLayout layout;

	/**
	 * LillyPad
	 * 
	 * @param flowdir
	 *            - Either 0 or 1
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param lilly
	 *            - Bitmap to draw
	 */
	public LillyPad(String question, int flowdir, float x, float y, float width, float height, Bitmap lilly) {
		super(x, y, width, height, lilly);
		this.dir = flowdir;
		this.text = question;
		this.paint = new TextPaint();
		this.paint.setTextSize(36);
		this.paint.setColor(Color.YELLOW);
		layout = new StaticLayout(text, 0, text.length(), paint, (int) width, Alignment.ALIGN_NORMAL, 1f, 1f, false);
	}

	/**
	 * Move based on flow
	 * 
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
		x = newPos < x ? newPos - 200/* width */: newPos;
	}

	public void moveLeft(View view, int dist) {
		x = x - dist < -200/* width */? view.getWidth() - dist : x - dist;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (this.isVisible()) {
			canvas.translate(x, y);
			layout.draw(canvas);
			canvas.translate(-x, -(y));
		}
		// canvas.drawText(text, x, y + height / 2, paint);
	}

}
