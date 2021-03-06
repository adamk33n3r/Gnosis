package org.waldk33n3r.android.gnosis.bible;

import java.io.IOException;
import java.util.ArrayList;

import org.waldk33n3r.android.gnosis.games.Games;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Bible extends ListActivity {
	BibleDatabaseHandler bdb = new BibleDatabaseHandler(this);
	ArrayAdapter<String> adapter;
	String[] allBooks = bdb.booksOfBible;
	int[] allBooksChapters = bdb.chaptersInEachBook;
	String[] gen1verses = bdb.versesOfGenesis1;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, allBooks);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String[] allChapters = new String[allBooksChapters[position]];
		for(int i=0;i<allChapters.length;i++){
			allChapters[i] = "Chapter " + String.valueOf(i+1);
		}
		//ArrayAdapter<String> new_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allChapters);
		Bundle b = new Bundle();
		b.putStringArray("array", allChapters);
		b.putString("book", allBooks[position]);
		Intent i = new Intent(this, BibleChapters.class);
		i.putExtras(b);
		startActivity(i);
	}
}
