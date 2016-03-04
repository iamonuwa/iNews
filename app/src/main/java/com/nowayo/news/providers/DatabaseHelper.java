package com.nowayo.news.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matrix on 04/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "nowayo";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE =
                "CREATE TABLE "
                        + AppContract.AppEntry.TABLE_NAME + " ("
                        + AppContract.AppEntry._ID + " INTEGER PRIMARY KEY, "
                        + AppContract.AppEntry.COLUMN_ID + " TEXT NOT NULL, "
                        + AppContract.AppEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                        + AppContract.AppEntry.COLUMN_CONTENT + " TEXT NOT NULL, "
                        + AppContract.AppEntry.COLUMN_IMAGE + " TEXT NOT NULL, "
                        + AppContract.AppEntry.COLUMN_DATE + " TEXT NOT NULL, "
                        + AppContract.AppEntry.COLUMN_EXPIRATION_TIME + " TEXT NOT NULL "
                        + " );";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop Table "+ AppContract.AppEntry.TABLE_NAME);
        onCreate(db);

    }
}
