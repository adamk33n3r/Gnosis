package org.waldk33n3r.android.gnosis.games;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionDatabaseHandler extends SQLiteOpenHelper {
	
	//Database Version
	private static final int DATABASE_VERSION = 1;
	
	//Database Name
	private static final String DATABASE_NAME = "questionsManager";
	
	//Questions table name
	private static final String TABLE_QUESTIONS = "questions";
	
	//Questions Table Columns names
	private static final String KEY_ID = "id";
    private static final String KEY_BODY = "body";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTION1 = "option1";
    private static final String KEY_OPTION2 = "option2";
    private static final String KEY_OPTION3 = "option3";
	
	public QuestionDatabaseHandler(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BODY + " TEXT,"
                + KEY_ANSWER + " TEXT" + KEY_OPTION1 + " TEXT" + KEY_OPTION2 +
                " TEXT" + KEY_OPTION3 + " TEXT" + ")";
        db.execSQL(CREATE_QUESTIONS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
 
        // Create tables again
        onCreate(db);
	}
}
