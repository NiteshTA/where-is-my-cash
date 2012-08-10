package com.suresh.whereismycash;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockListActivity {
	
	private DbHelper dbHelper = new DbHelper(this);
	private static final int ACTION_ADD_ENTRY = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor c = dbHelper.getAllLoans();
        startManagingCursor(c);
        CustomAdapter adapter = new CustomAdapter(this, c, CursorAdapter.FLAG_AUTO_REQUERY, dbHelper);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.activity_main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_add:
    		startActivityForResult(new Intent(this, CreateActivity.class), ACTION_ADD_ENTRY);
    		break;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    	return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch (requestCode) {
    	case ACTION_ADD_ENTRY:
    		if (resultCode == RESULT_OK) {
    			recreate();
    		}
    		break;
    	default:
    		super.onActivityResult(requestCode, resultCode, data);
    	}
    }
    
}
