package org.waldk33n3r.android.gnosis.games;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuestionDatabaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "questionsManager";

	// Questions table name
	private static final String TABLE_QUESTIONS = "questions";

	// Questions Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_BODY = "body";
	private static final String KEY_ANSWER = "answer";
	private static final String KEY_OPTION1 = "option1";
	private static final String KEY_OPTION2 = "option2";
	private static final String KEY_OPTION3 = "option3";

	public QuestionDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_BODY + " TEXT,"
				+ KEY_ANSWER + " TEXT," + KEY_OPTION1 + " TEXT," + KEY_OPTION2
				+ " TEXT," + KEY_OPTION3 + " TEXT" + ")";
		db.execSQL(CREATE_QUESTIONS_TABLE);
		addQuestion(new Question("Who was Jesus mother?", "Mary", "Martha",
				"Ruth", "Diana"));
		addQuestion(new Question(
				"What was the name of the garden where Adam and Eve lived?",
				"Garden of Eden", "Garden of the Gods", "Garden of Gethsemane",
				"Hanging Gardens"));
		addQuestion(new Question("With what food did Jesus feed 5,000 people?",
				"Loaves of bread and fishes", "Bread and Wine",
				"Fish and Chips", "Cheese and Crackers"));
		addQuestion(new Question(
				"What method did the Romans use to kill Jesus?", "Crucifixion",
				"Hanging", "Water Torture", "Guillotine"));
		addQuestion(new Question(
				"From which part of Adam's body did God create Eve?", "Rib",
				"Heart", "Stomach", "Brain"));
		addQuestion(new Question(
				"Who, when accused of being with Jesus, lied and said that he did not know him, three times?",
				"Peter", "Judas", "John", "Thomas"));
		addQuestion(new Question(
				"Which creature tricked Eve into eating of the forbidden fruit?",
				"Serpent", "Fox", "Angel", "Wolf"));
		addQuestion(new Question(
				"At Christ's crucifixion what did the soldiers place on his head?",
				"Crown of Thorns", "Crown of Gold", "Robe of Many Colors",
				"Helmet"));
		addQuestion(new Question(
				"What is the first line of the Lord's Prayer?",
				"Our Father which art in heaven",
				"My God, why have you forsaken me?",
				"The Lord is my shepherd I shall not want",
				"The Lord bless you and keep you"));
		addQuestion(new Question("What relationship was Ruth to Naomi?",
				"Daughter-in-law", "Mother", "Aunt", "Friend"));
		addQuestion(new Question(
				"Who lied to God when he was asked where his brother was?",
				"Cain", "Abel", "Seth", "Enoch"));
		addQuestion(new Question(
				"Which Old Testament character showed his faith by being willing to offer his son on an altar to God?",
				"Abraham", "Isaac", "Ishmael", "Jacob"));
		addQuestion(new Question(
				"What significant event is recorded in Genesis chapters 1 and 2?",
				"Creation", "The Fall", "The Flood", "The Exodus"));
		addQuestion(new Question("What was inscribed above Jesus' cross?",
				"King of the Jews ", "King of Kings", "Lord of Lords",
				"Son of God"));
		addQuestion(new Question(
				"Whose mother placed him in an ark of bulrushes?", "Moses",
				"Noah", "Joseph", "Joshua"));
		addQuestion(new Question(
				"For how many days and nights did it rain in the story of the flood?",
				"Forty", "Twelve", "Three", "One-Hundred"));
		addQuestion(new Question("What was special about Jesus' mother?",
				"She was a virgin", "She was an only Child",
				"She was without sin", "She was a Gentile"));
		addQuestion(new Question(
				"Who gave gifts to Jesus when he was a young child?", "magi",
				"King Herod", "Pontius Pilate", "pharisees"));
		addQuestion(new Question(
				"What happened to Jonah after he was thrown overboard?",
				"He was swallowed by a great fish", "He swam to Tarsus",
				"He drowned", "He was rescued by pirates"));
		addQuestion(new Question("In whose image was man created?", "God's",
				"Monkey", "Fish", "his own"));
		addQuestion(new Question("How many apostles did Jesus choose?",
				"Twelve", "Three", "Seven", "Forty"));
		addQuestion(new Question("What are the wages of sin?", "Death",
				"Prison", "30 pieces of silver", "nothing"));
		addQuestion(new Question(
				"Who is the first mother mentioned in the Bible?", "Eve",
				"Noah's wife", "Sarah", "Tamar"));
		addQuestion(new Question(
				"Who else, other than the wise men, came to visit Jesus when he was a small child?",
				"Shepherds", "King Herod", "Pontius Pilate", "pharisees"));
		addQuestion(new Question(
				"Who lied when he was asked to reveal the source of his great strength?",
				"Samson", "Gideon ", "David", "Eli"));
		addQuestion(new Question(
				"What was the name of the man Jesus' mother was engaged to at the time she became pregnant?",
				"Joseph", "Joshua", "Josiah", "Job"));
		addQuestion(new Question(
				"Which book of the Bible records many of the hymns David wrote?",
				"Psalms", "Proverbs", "Samuel I", "Kings I"));
		addQuestion(new Question("From what disaster did the Ark save Noah?",
				"Flood", "Asteroid", "Famine", "Anarchy"));
		addQuestion(new Question(
				"What happened to Jesus forty days after his resurrection?",
				"He ascended into heaven", "He was killed again",
				"He turned water into wine", "He left to Jerusalem"));
		addQuestion(new Question(
				"What animals did Jesus cause to run into the sea and drown?",
				"Pigs", "Dogs", "Cats", "Sheep"));
		addQuestion(new Question("On what were the Ten Commandments written?",
				"Two tables of stone", "On a wall", "In man's heart",
				"On the Ark of the Covenant"));
		addQuestion(new Question("What did Jesus sleep in after he was born?",
				"Manger", "Palace", "Stable", "Crib"));
		addQuestion(new Question("What was man created from?",
				"Dust of the ground", "Apes", "Fish", "Worms"));
		addQuestion(new Question(
				"What did Jesus do to each of the disciples during the Last Supper?",
				"Washed their feet", "Blessed their families",
				"Cleaned their homes", "Told them a parable"));
		addQuestion(new Question(
				"To which city did God ask Jonah to take his message?",
				"Nineveh", "Jerusalem", "Cairo", "Babylon"));
		addQuestion(new Question("Who was David's father?", "Jesse",
				"Jonathon", "Saul", "Samuel"));
		addQuestion(new Question(
				"Which of the gospels appears last in the Bible?", "John",
				"Matthew", "Mark", "Luke"));
		addQuestion(new Question(
				"What is the only sin that cannot be forgiven?",
				"Blasphemy against the Holy Spirit", "Murder", "Adultery",
				"Suicide"));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

		// Create tables again
		onCreate(db);
	}

	// Adding new question
	public void addQuestion(Question question) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_BODY, question.getBody());
		values.put(KEY_ANSWER, question.getAnswer());
		values.put(KEY_OPTION1, question.getOption1());
		values.put(KEY_OPTION2, question.getOption2());
		values.put(KEY_OPTION3, question.getOption3());
		Log.e("DB", "Inserting a question");
		db.insert(TABLE_QUESTIONS, null, values);
		db.close();
	}

	// Getting single question
	public Question getQuestion(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { KEY_ID,
				KEY_BODY, KEY_ANSWER, KEY_OPTION1, KEY_OPTION2, KEY_OPTION3 },
				KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}

		Question question = new Question(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5));

		return question;
	}

	// Getting All Questions as a List
	public List<Question> getAllQuestions() {
		List<Question> questionList = new ArrayList<Question>();

		String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Question question = new Question();
				question.setID(Integer.parseInt(cursor.getString(0)));
				question.setBody(cursor.getString(1));
				question.setAnswer(cursor.getString(2));
				question.setOption1(cursor.getString(3));
				question.setOption2(cursor.getString(4));
				question.setOption3(cursor.getString(5));

				questionList.add(question);
			} while (cursor.moveToNext());
		}

		return questionList;
	}

	// Getting question Count
	public int getQuestionsCount() {
		String questionQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(questionQuery, null);
		int count = cursor.getCount();
		cursor.close();

		return count;
	}

	// Updating single question
	public int updateQuestion(Question question) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_BODY, question.getBody());
		values.put(KEY_ANSWER, question.getAnswer());
		values.put(KEY_OPTION1, question.getOption1());
		values.put(KEY_OPTION2, question.getOption2());
		values.put(KEY_OPTION3, question.getOption3());

		return db.update(TABLE_QUESTIONS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(question.getID()) });
	}

	// Deleting single question
	public void deleteQuestion(Question question) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_QUESTIONS, KEY_ID + " = ?",
				new String[] { String.valueOf(question.getID()) });
		db.close();
	}
}
