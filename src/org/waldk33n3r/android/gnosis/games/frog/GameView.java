package org.waldk33n3r.android.gnosis.games.frog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.waldk33n3r.android.gnosis.R;
import org.waldk33n3r.android.gnosis.games.Question;
import org.waldk33n3r.android.gnosis.games.QuestionDatabaseHandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class GameView extends View {

	private Paint paint;
	private TextPaint textPaint;

	private Bitmap lillyImg;
	private Bitmap frog;
	private Bitmap frogJump;
	private Bitmap log;

	private enum FrogState {
		SIT, JUMP
	}

	private FrogState curFrogState = FrogState.SIT;

	private LillyPad frogLoc;
	private LillyPad logLilly;
	private LillyPad startLilly;

	private ArrayList<LillyPad> lillies;

	private LillyField field;

	private Random rand;

	private String[] strings;

	private int qNum;
	private int y;
	private int curLevel;
	private Question curQ;
	ArrayList<Question> questions;

	StaticLayout layout;

	QuestionDatabaseHandler db;

	Display display;
	private boolean nextScreen ;

	public GameView(Context context) {
		super(context);
		WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
		display = wm.getDefaultDisplay();
		paint = new Paint();
		textPaint = new TextPaint();
		textPaint.setColor(Color.YELLOW);
		lillyImg = BitmapFactory.decodeResource(getResources(), R.drawable.lily_pad2_trans);
//		lillyImg = Bitmap.createScaledBitmap(tmp, (int) (tmp.getWidth() * ((float) display.getWidth() / 300)), (int) (tmp.getHeight() * ((float) display.getHeight() / 1200)), true);
		frog = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sitting_frog_trans), 100, 100, true);
		frogJump = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jumping_frog_trans), 100, 120, true);
		Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.log_trans);
		Log.w("dafdsF",display.getWidth()+" " + lillyImg.getWidth());
		log = Bitmap.createScaledBitmap(tmp, display.getWidth(), (int) (tmp.getHeight() * ((float)display.getHeight()/ 1200)), true);
		rand = new Random();
		// Log.e("DB","Getting handler");
		db = new QuestionDatabaseHandler(getContext());
		// Log.e("Question count", "" + db.getQuestionsCount());
		lillies = new ArrayList<LillyPad>();
		init();
//		Timer timer = new Timer(10, true, new Executable() {
//
//			@Override
//			public void exec(Object... obj) {
//				((View) obj[0]).post(new Runnable() {
//					@Override
//					public void run() {
//						invalidate();
//					}
//				});
//			}
//
//		}, this);
		// timer.start();
	}

	private void init() {
		questions = new ArrayList<Question>();
		startLilly = new LillyPad("", 0, 0, (display.getWidth() - frog.getWidth()) / 2, (display.getHeight() - log.getHeight())
					+ log.getHeight() / 2 - frog.getHeight() / 2, 100, 100, -1, lillyImg);
		frogLoc = startLilly;
		logLilly = new LillyPad("", 0, 0, 0, 0, display.getWidth(), 150, 5, lillyImg);
		logLilly.setVisible(false);
		for (Question question : db.getAllQuestions()) {
			questions.add(question);
		}
		build();
	}

	private void build() {
		if (field != null) {
			curQ = questions.get(qNum);
			field.showAll();
			field.rows.get(qNum - (curLevel * 5 + 1)).clearText();
			logLilly.setText(curQ.getBody());
			qNum++;
		} else {
			qNum = curLevel * 5;
			curQ = questions.get(qNum);
			logLilly.setText(curQ.getBody());
			Question cur = curQ;
			int[] dirs = new int[5];
			dirs[0] = rand.nextInt(2);
			dirs[1] = rand.nextInt(2);
			dirs[2] = rand.nextInt(2);
			dirs[3] = rand.nextInt(2);
			dirs[4] = rand.nextInt(2);
			field = new LillyField();
			int dx = display.getWidth() / 4;
			int x = 0;
			int y = display.getHeight() - 250;
			int speed, size = (int) (100 * ((float)display.getWidth() / 800));
			for (int i = 0; i < dirs.length; i++) {
				speed = rand.nextInt(8) + 2;
				field.add(new LillyRow(new LillyPad(cur.getAnswer(), speed, dirs[i], x + rand.nextInt(100), y, size, size, i, lillyImg),
						new LillyPad(cur.getOption1(), speed, dirs[i], x + dx + rand.nextInt(100), y, size, size, i, lillyImg), new LillyPad(
								cur.getOption2(), speed, dirs[i], x + dx * 2 + rand.nextInt(100), y, size, size, i, lillyImg), new LillyPad(
								cur.getOption3(), speed, dirs[i], x + dx * 3 + rand.nextInt(100), y, size, size, i, lillyImg)));
				y -= 200;
				cur = questions.get(qNum + i + 1);
				x = rand.nextInt(display.getWidth() - 100);
			}
			qNum++;
		}
		textPaint.setTextSize(36 / (this.curQ.getBody().length() > 500 ? 2 : 1));
		layout = new StaticLayout(this.curQ.getBody(), 0, this.curQ.getBody().length(), textPaint, log.getWidth(), Alignment.ALIGN_CENTER,
				1f, 1f, false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(nextScreen)
			y+=30;
		if (y > display.getHeight() - log.getHeight()) {
			nextScreen = false;
			y = 0;
			frogLoc = startLilly;
			curLevel++;
			field = null;
			build();
		}
		canvas.translate(0, y);
		paint.setColor(Color.BLUE);
		canvas.drawPaint(paint);

		// Question log
		canvas.drawBitmap(log, 0, 0, null);
		paint.setColor(Color.YELLOW);

		// for (Iterator<LillyPad> it = lillies.iterator(); it.hasNext();) {
		// LillyPad lilly = it.next();
		// lilly.move(this, 5);
		// lilly.draw(canvas);
		// }

		canvas.drawBitmap(log, 0, canvas.getHeight() - log.getHeight(), null);
		logLilly.draw(canvas);
		for (LillyRow row : field.rows)
			for (LillyPad pad : row.lillies) {
				pad.move(this, pad.speed);
				pad.draw(canvas);
			}

		// Draw frog
		canvas.drawBitmap(curFrogState == FrogState.SIT ? frog : frogJump, frogLoc.x + frogLoc.width / 2 - frog.getWidth() / 2, frogLoc.y,
				null);
		this.invalidate();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Log.w("Touch Event", String.format("Touched at %s, %s",
			// event.getX(), event.getY()));
			for (LillyRow row : field.rows)
				for (LillyPad lilly : row.lillies) {
					if (new RectF(lilly.getRectF()).contains(event.getX(), event.getY())) {
//						Log.w("Touch Event", "Lilly pad touched");
						if (lilly.inRow(qNum - (5 * curLevel + 1))) {
							if (curQ.getAnswer() != lilly.text) {
								lilly.setVisible(false);
								lilly.setTextVisible(false);
								Toast.makeText(getContext(), "You perished in the depths", Toast.LENGTH_SHORT).show();
								frogLoc = startLilly;
								field = null;
							} else
								frogLoc = lilly;
							build();
						}
						break;
					}
				}
			if (qNum == 5 * (curLevel + 1) + 1) {
				logLilly.setText("");
				if (new RectF(logLilly.getRectF()).contains(event.getX(), event.getY())) {
					frogLoc = logLilly;
					nextScreen = true;
				}
			}
		}
		return true;
	}

	interface Executable {
		public void exec(Object... obj);
	}

	class Timer extends Thread {
		Executable func;
		int wait;
		boolean repeat;
		Object[] params;

		public Timer(int wait, boolean repeat, Executable func, Object... params) {
			this.wait = wait;
			this.repeat = repeat;
			this.func = func;
			this.params = params;
		}

		@Override
		public void run() {
			do {
				try {
					super.sleep(this.wait);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				func.exec(params);
			} while (repeat);
		}
	}

}