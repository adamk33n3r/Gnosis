package org.waldk33n3r.android.gnosis.games;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public abstract class Entity {

	private float x, y, width, height;
	protected Bitmap bitmap;

	public Entity(float x, float y, float width, float height, Bitmap bitmap) {
		Log.e("Entity", width + ":" + height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bitmap = bitmap;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, (int) (width * canvas.getWidth()), (int) (height*canvas.getWidth()), true), x, y, new Paint());
	}

}
