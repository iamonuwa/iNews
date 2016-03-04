package com.nowayo.news;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nowayo.news.providers.AppContract;
import com.nowayo.news.providers.DBHandler;
import com.nowayo.news.providers.DatabaseHelper;

public class Latest extends AppCompatActivity {

    private static final String TAG = Latest.class.getName();

    DBHandler db = new DBHandler(this);
    Context mContext;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        insert();
    }

    public void insert(){
        DatabaseHelper db = new DatabaseHelper(this);

        SQLiteDatabase mdb = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(AppContract.AppEntry.COLUMN_ID, "1");
        cv.put(AppContract.AppEntry.COLUMN_TITLE, "Hello World");
        cv.put(AppContract.AppEntry.COLUMN_CONTENT, "Testing My Database through ContentProviders");
        cv.put(AppContract.AppEntry.COLUMN_IMAGE,"NONE");
        cv.put(AppContract.AppEntry.COLUMN_DATE, "TODAY");
        Uri uri = AppContract.BASE_CONTENT_URI;

        Uri data = getApplicationContext().getContentResolver().insert(uri, cv);
//        Uri data = (Uri) getApplicationContext().getContentResolver().query(uri, new String[]{AppContract.AppEntry.COLUMN_TITLE},null, null,null );
        Log.d("Inserted Data ", String.valueOf(data));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_latest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
