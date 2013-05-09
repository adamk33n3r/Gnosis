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

	private ArrayList<LillyPad> lillies;

	private LillyField field;

	private Random rand;

	private String[] strings;

	private int qNum;
	private Question curQ;
	ArrayList<Question> questions;

	StaticLayout layout;

	QuestionDatabaseHandler db;

	Display display;

	public GameView(Context context) {
		super(context);
		WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
		display = wm.getDefaultDisplay();
		paint = new Paint();
		textPaint = new TextPaint();
		textPaint.setColor(Color.YELLOW);
		lillyImg = BitmapFactory.decodeResource(getResources(), R.drawable.lily_pad2_trans);
		frog = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sitting_frog_trans), 100, 100, true);
		frogJump = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jumping_frog_trans), 100, 120, true);
		Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.log_trans);
		log = Bitmap.createScaledBitmap(tmp, display.getWidth(), tmp.getHeight(), true);
		rand = new Random();
		// Log.e("DB","Getting handler");
		db = new QuestionDatabaseHandler(getContext());
		// Log.e("Question count", "" + db.getQuestionsCount());
		lillies = new ArrayList<LillyPad>();
		init();
		Timer timer = new Timer(100, true, new Executable() {

			@Override
			public void exec(Object... obj) {
				((View) obj[0]).post(new Runnable() {
					@Override
					public void run() {
						invalidate();
					}
				});
			}

		}, this);
		timer.start();
	}

	private void init() {
		questions = new ArrayList<Question>();
		frogLoc = new LillyPad("", 0, (display.getWidth() - frog.getWidth()) / 2, (display.getHeight() - log.getHeight()) + log.getHeight()
				/ 2 - frog.getHeight() / 2, 100, 100, lillyImg);
		logLilly = new LillyPad("", 0, 0, 0, display.getWidth(), 150, lillyImg);
		for (Question question : db.getAllQuestions()) {
			questions.add(question);
		}
		build();
	}

	private void build() {
		if (field != null) {
			curQ = questions.get(qNum);
			field.rows.get(qNum - 1).clearText();
			qNum++;
		} else {
			curQ = questions.get(0);
			Question cur = curQ;
			int[] dirs = new int[4];
			dirs[0] = rand.nextInt(2);
			dirs[1] = rand.nextInt(2);
			dirs[2] = rand.nextInt(2);
			dirs[3] = rand.nextInt(2);
			field = new LillyField();
			int dx = display.getWidth() / 4;
			int y = display.getHeight() - 200;
			for (int i = 0; i < dirs.length; i++) {
				field.add(new LillyRow(new LillyPad(cur.getAnswer(), dirs[i], 0, y, 100, 100, lillyImg), new LillyPad(cur.getOption1(),
						dirs[i], dx, y, 100, 100, lillyImg), new LillyPad(cur.getOption2(), dirs[i], dx * 2, y, 100, 100, lillyImg),
						new LillyPad(cur.getOption3(), dirs[i], dx * 3, y, 100, 100, lillyImg)));
				y -= 200;
				cur = questions.get(i + 1);
			}qNum++;
		}
		textPaint.setTextSize(36 / (this.curQ.getBody().length() > 500 ? 2 : 1));
		layout = new StaticLayout(this.curQ.getBody(), 0, this.curQ.getBody().length(), textPaint, log.getWidth(), Alignment.ALIGN_CENTER,
				1f, 1f, false);
	}

	private void buildRand() {
		if (lillies.size() > 0) {
			curQ = questions.get(qNum);
			Log.e("New Question", curQ.getAnswer());
			if (qNum % 2 == 0) { // If even: means it is 2nd question, 4th,
									// etc...
				lillies.get(4).setText(curQ.getAnswer());
				lillies.get(5).setText(curQ.getOption1());
				lillies.get(6).setText(curQ.getOption2());
				lillies.get(7).setText(curQ.getOption3());
			} else {
				lillies.get(0).setText(curQ.getAnswer());
				lillies.get(1).setText(curQ.getOption1());
				lillies.get(2).setText(curQ.getOption2());
				lillies.get(3).setText(curQ.getOption3());
			}
		} else {
			curQ = questions.get(0);
			int dir1 = rand.nextInt(2);
			int dir2 = rand.nextInt(2);
			int dir3 = rand.nextInt(2);
			int dir4 = rand.nextInt(2);
			// Log.e("Question List",questions.toString());
			int lillyCount = 0;
			while (lillies.size() < 8) {
				// for (int i = 0; i < rand.nextInt(40) + 20; i++) { // Random
				// amount
				int size = 100;
				float x = (rand.nextInt(display.getWidth()));
				float y = rand.nextInt(4) * 200 + display.getHeight() - 1000;

				String text = null;
				if (lillyCount < 4) {
					switch (lillyCount) {
						case 0:
							text = questions.get(qNum).getAnswer();
							break;
						case 1:
							text = questions.get(qNum).getOption1();
							break;
						case 2:
							text = questions.get(qNum).getOption2();
							break;
						case 3:
							text = questions.get(qNum).getOption3();
							break;
					}
				} else {
					switch (lillyCount) {
						case 4:
							text = questions.get(qNum + 1).getAnswer();
							break;
						case 5:
							text = questions.get(qNum + 1).getOption1();
							break;
						case 6:
							text = questions.get(qNum + 1).getOption2();
							break;
						case 7:
							text = questions.get(qNum + 1).getOption3();
							break;
					}
				}
				// if (text.length() > 12)
				// size += 100;

				// Check if this lillypad intersects any others
				boolean intersects = false;
				for (Iterator<LillyPad> it = lillies.iterator(); it.hasNext();) {
					LillyPad lilly = it.next();
					RectF newRect = new RectF(x, y, x + size, y + size);
					intersects = RectF.intersects(lilly.getRectF(), newRect);
					if (intersects)
						break;
				}
				if (!intersects) {

					int dir;
					int row = (int) ((y - 20) / 200);
					if (row == 1)
						dir = dir1;
					else if (row == 2)
						dir = dir2;
					else if (row == 3)
						dir = dir3;
					else
						dir = dir4;

					lillyCount++;
					// Log.e("adsfadsfasdf",lillyCount+" "+text);

					lillies.add(new LillyPad(text, dir, x, y, size, size, lillyImg));
				}
			}
		}
		qNum++;
		textPaint.setTextSize(36 / (this.curQ.getBody().length() > 500 ? 2 : 1));
		layout = new StaticLayout(this.curQ.getBody(), 0, this.curQ.getBody().length(), textPaint, log.getWidth(), Alignment.ALIGN_CENTER,
				1f, 1f, false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

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

		for (LillyRow row : field.rows)
			for (LillyPad pad : row.lillies) {
				pad.move(this, 5);
				pad.draw(canvas);
			}

		paint.setColor(Color.rgb(139, 69, 19));

		// Start log
		canvas.drawBitmap(log, 0, canvas.getHeight() - log.getHeight(), null);
		canvas.drawBitmap(curFrogState == FrogState.SIT ? frog : frogJump, frogLoc.x + frogLoc.width / 2 - frog.getWidth() / 2, frogLoc.y,
				null);
		canvas.translate(0, log.getHeight() / 2);
		layout.draw(canvas);
		canvas.translate(0, -log.getHeight() / 2);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Log.w("Touch Event", String.format("Touched at %s, %s",
			// event.getX(), event.getY()));
			for (LillyRow row : field.rows)
				for (LillyPad lilly : row.lillies) {
					if (new RectF(lilly.getRectF()).contains(event.getX(), event.getY())) {
						Log.w("Touch Event", "Lilly pad touched");
						if (curQ.getAnswer() != lilly.text) {
							lilly.setVisible(false);
							lilly.setTextVisible(false);
						} else {
							frogLoc = lilly;
							build();
						}
						break;
					}
				}
			if (new RectF(logLilly.getRectF()).contains(event.getX(), event.getY())) {
				frogLoc = logLilly;
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