package org.waldk33n3r.android.gnosis.games.frog;

import java.util.Random;

import org.waldk33n3r.android.gnosis.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {

	private Paint paint;

	private Bitmap lilly;

	private Random rand;

	public GameView(Context context) {
		super(context);
		paint = new Paint();
		lilly = BitmapFactory.decodeResource(getResources(), R.drawable.lilypad);
		rand = new Random();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.CYAN);
		canvas.drawPaint(paint);
		
		paint.setColor(Color.RED);		
		for (int i = 0; i <= canvas.getHeight();i+=10) {
			canvas.drawLine(i, 0, i, canvas.getHeight(), paint);
			canvas.drawLine(0, i, canvas.getWidth(), i, paint);
		}

		for (int x = 0; x + lilly.getWidth() < canvas.getWidth(); x += lilly.getWidth() + rand.nextInt(30) + 30)
			for (int y = 0; y + lilly.getHeight() < canvas.getHeight(); y += lilly.getHeight() + rand.nextInt(30) + 30) {
				canvas.drawBitmap(lilly, x, y, paint);
			}
		this.invalidate();

	}

}
