package org.waldk33n3r.android.gnosis.bible;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BibleChapters extends ListActivity {
	BibleDatabaseHandler bdb = new BibleDatabaseHandler(this);
	ArrayAdapter<String> adapter;
	String[] allBooks = bdb.booksOfBible;
	int[] allBooksChapters = bdb.chaptersInEachBook;
	String[] gen1verses = bdb.versesOfGenesis1;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] allChapters = this.getIntent().getExtras().getStringArray("array");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, allChapters);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		/*String[] allChapters = new String[allBooksChapters[position]];
		for(int i=0;i<allChapters.length;i++){
			allChapters[i] = "Chapter " + String.valueOf(i+1);
		}
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allChapters);
		setListAdapter(adapter); 
		*/
	}
}
