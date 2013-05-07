package org.waldk33n3r.android.gnosis.bible;

import java.util.ArrayList;
import java.util.List;

import org.waldk33n3r.android.gnosis.games.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BibleDatabaseHandler extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bible";

    // Contacts table name
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_CHAPTERS = "chapters";
    private static final String TABLE_VERSES = "verses";

    //Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_VERSION = "version";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_TEXT = "text";
    private static final String KEY_BOOK_ID = "book_id";
    private static final String KEY_CHAPTER_ID = "chapter_id";
    private static final String KEY_VERSE_ID = "verse_id";
    

    public BibleDatabaseHandler(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

	    //create books table
		String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
			+ KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_TITLE
			+ " TEXT not null," + KEY_VERSION + " TEXT not null" + ")";
		
		//create chapters table
		String CREATE_CHAPTERS_TABLE = "CREATE TABLE " + TABLE_CHAPTERS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
			+ KEY_NUMBER + " INTEGER," + KEY_BOOK_ID + " INTEGER not null"
			+ ")";
		
		//create verses table
		String CREATE_VERSES_TABLE = "CREATE TABLE " + TABLE_VERSES
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
			+ KEY_NUMBER + " INTEGER," + KEY_TEXT + " TEXT not null," + KEY_CHAPTER_ID + " INTEGER not null"
			+ ")";
		
		db.execSQL(CREATE_VERSES_TABLE);
		db.execSQL(CREATE_CHAPTERS_TABLE);
		db.execSQL(CREATE_BOOKS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSES);
		// Create tables again
		onCreate(db);
    }
    
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new verse, chapter, book
    void addVerse(Verse verse, Chapter chapter) {
		SQLiteDatabase db = this.getWritableDatabase();
	
		ContentValues values = new ContentValues();
		values.put(KEY_NUMBER, verse.getNumber());
		values.put(KEY_TEXT, verse.getText());
		values.put(KEY_CHAPTER_ID, chapter.getID()); 
	
		// Inserting Row
		db.insert(TABLE_VERSES, null, values);
		db.close(); // Closing database connection
    }
    
    void addChapter(Chapter chapter, Book book) {
		SQLiteDatabase db = this.getWritableDatabase();
	
		ContentValues values = new ContentValues();
		values.put(KEY_NUMBER, chapter.getNumber()); 
		values.put(KEY_BOOK_ID, book.getID()); 
	
		// Inserting Row
		db.insert(TABLE_CHAPTERS, null, values);
		db.close(); // Closing database connection
    }
    
    void addBook(Book book) {
		SQLiteDatabase db = this.getWritableDatabase();
	
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, book.getTitle()); 
		values.put(KEY_VERSION, book.getVersion()); 
	
		// Inserting Row
		db.insert(TABLE_BOOKS, null, values);
		db.close(); // Closing database connection
    }
    
    //get books, chapters, and verses
    public ArrayList<Verse> getAllVerses() {
		ArrayList<Verse> verseList = new ArrayList<Verse>();

		/*
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(book_id);
		String b_id = sb.toString();
		
		sb = new StringBuilder();
		sb.append("");
		sb.append(chapter_id);
		String c_id = sb.toString();
		
		
		String selectQuery = 
		"SELECT * " +
		" FROM " + TABLE_VERSES +
		" JOIN " + TABLE_CHAPTERS + " ON (" + TABLE_CHAPTERS + ".id = " + TABLE_VERSES + ".chapter_id " +
		" JOIN " + TABLE_BOOKS + " ON (" + TABLE_CHAPTERS + ".book_id = " + TABLE_BOOKS + ".id " +
		" WHERE " + TABLE_VERSES + ".book_id = " + b_id + " AND " + TABLE_VERSES + ".chapter_id = " + c_id;
		*/
		
		String selectQuery = "SELECT * FROM " + TABLE_VERSES;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Verse verse = new Verse();
				verse.setID(Integer.getInteger(cursor.getString(0)));
				verse.setNumber(Integer.getInteger(cursor.getString(1)));
				verse.setText(cursor.getString(2));
				verseList.add(verse);
			} while (cursor.moveToNext());
		}

		return verseList;
	}
    
    public ArrayList<Chapter> getAllChapters() {
		ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
		
		String selectQuery = "SELECT * FROM " + TABLE_CHAPTERS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Chapter chapter = new Chapter();
				chapter.setID(Integer.getInteger(cursor.getString(0)));
				chapter.setNumber(Integer.getInteger(cursor.getString(1)));
				chapterList.add(chapter);
			} while (cursor.moveToNext());
		}

		return chapterList;
	}
    
    public ArrayList<Book> getAllBooks() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		String selectQuery = "SELECT * FROM " + TABLE_BOOKS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Book book = new Book();
				book.setID(Integer.getInteger(cursor.getString(0)));
				book.setTitle(cursor.getString(1));
				book.setVersion(cursor.getString(2));
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		return bookList;
	}
}
