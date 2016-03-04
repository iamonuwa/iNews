package com.nowayo.news.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nowayo.news.providers.AppContract.AppEntry;

import java.sql.SQLException;

/**
 * Created by matrix on 03/03/2016.
 */
public class DBHandler {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "news_posts";

    private static final String DATABASE_TABLE = "posts";

    private static final String _ID = "_id";

    private static final String POST_ID = "post_id";

    private static final String POST_TITLE = "post_title";

    private static final String POST_CONTENT = "post_content";

    private static final String POST_IMAGE = "post_image";

    private static final String POST_DATE = "post_date";

    private static final String POST_AUTHOR = "post_author";

    private Context mContext;

    private DatabaseHandler mDatabaseHandler;

    private SQLiteDatabase db;


    private static class DatabaseHandler extends SQLiteOpenHelper {
        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            final String SQL_CREATE_TABLE =
                    "CREATE TABLE "
                            + DATABASE_TABLE + " ("
                            + _ID + " INTEGER PRIMARY KEY, "
                            + POST_ID + " TEXT NOT NULL, "
                            + POST_TITLE + " TEXT NOT NULL, "
                            + POST_CONTENT + " TEXT NOT NULL, "
                            + POST_IMAGE + " TEXT NOT NULL, "
                            + POST_DATE + " TEXT NOT NULL, "
                            + POST_AUTHOR + " TEXT NOT NULL "
                            + " );";

            // Create the table.
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "
                    + AppEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public DBHandler(Context context){
        this.mContext = context;
    }

    public DBHandler open() throws SQLException{
        mDatabaseHandler = new DatabaseHandler(mContext);
        db = mDatabaseHandler.getWritableDatabase();
        return this;
    }

    public void close(){
        mDatabaseHandler.close();
    }

    public Cursor fetchAllNotes() {

        return db.query(DATABASE_NAME, new String[] { POST_ID, POST_TITLE, POST_CONTENT, POST_DATE, POST_IMAGE, POST_AUTHOR}, null, null, null, null, null);
    }

    public long add(String id, String title, String content, String date, String image, String author) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(POST_ID, id);
        initialValues.put(POST_TITLE, title);
        initialValues.put(POST_CONTENT, content);
        initialValues.put(POST_DATE, date);
        initialValues.put(POST_IMAGE, image);
        initialValues.put(POST_AUTHOR, author);
        return db.insert(DATABASE_NAME, null, initialValues);
    }

    public boolean deleteNote(long rowId) {

        return db.delete(DATABASE_NAME, POST_ID + "=" + rowId, null) > 0;
    }

    public Cursor fetchById(long rowId) throws SQLException {

        Cursor mCursor =

                db.query(true, DATABASE_TABLE, new String[] {POST_ID,
                                POST_TITLE, POST_AUTHOR, POST_IMAGE, POST_CONTENT, POST_DATE}, POST_ID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

}