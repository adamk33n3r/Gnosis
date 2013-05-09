package org.waldk33n3r.android.gnosis.games.frog;

import org.waldk33n3r.android.gnosis.games.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

public class LillyPad extends Entity {

	private int dir;
	private boolean textVisible;
	private TextPaint paint;
	private StaticLayout layout;
	String text;
	private int rowNum;
	int speed;

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
	public LillyPad(String text, int speed, int flowdir, float x, float y, float width, float height, int rowNum, Bitmap lilly) {
		super(x, y, width, height, lilly);
		this.rowNum = rowNum;
		this.speed = speed;
		this.dir = flowdir;
		this.text = text;
		this.paint = new TextPaint();
		this.paint.setTextSize(30 * (width / 75) / (text.length() > 4 ? 2 : 1));
		this.paint.setColor(Color.YELLOW);
		layout = new StaticLayout(this.text, 0, this.text.length(), paint, (int) width, Alignment.ALIGN_CENTER, 1f, 1f, false);
		this.textVisible = true;
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
		x = newPos < x ? newPos - 100/* width */: newPos;
	}

	public void moveLeft(View view, int dist) {
		x = x - dist < -100/* width */? view.getWidth() - dist : x - dist;
	}

	public void setText(String text) {
		this.text = text;
		this.paint.setTextSize(30 * (width / 75) / (text.length() > 4 ? 2 : 1));
		if (width > 100)
			this.paint.setTextSize(36);
		layout = new StaticLayout(this.text, paint, (int) width-50, Alignment.ALIGN_CENTER, 1f, 1f, false);
	}

	public boolean isTextVisible() {
		return textVisible;
	}

	public void setTextVisible(boolean flag) {
		textVisible = flag;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (this.isTextVisible()) {
			if (width > 100) {
				canvas.translate(x + 40, y + 30);
				layout.draw(canvas);
				canvas.translate(-x - 40, -y - 30);
			} else {
				canvas.translate(x, y + 30 * (1 / layout.getLineCount()));
				layout.draw(canvas);
				canvas.translate(-x, -y - 30 * (1 / layout.getLineCount()));
			}
		}
	}

	public boolean inRow(int qNum) {
		return qNum == rowNum;
	}

}
