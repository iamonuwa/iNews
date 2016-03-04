package com.nowayo.news.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by matrix on 03/03/2016.
 */
public class AppProvider extends ContentProvider{

    private static final String TAG = AppProvider.class.getName();

    private DatabaseHelper mDBHandler;

    private static final int POSTS = 100;

    private static final int POST_ID = 101;

    private static final UriMatcher sUriMatcher = builderUriMatcher();

    private static UriMatcher builderUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(AppContract.CONTENT_AUTHORITY,
                AppContract.PATH_TABLE,
                POSTS);

        matcher.addURI(AppContract.CONTENT_AUTHORITY,
                AppContract.PATH_TABLE
                + "/#",
                POST_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDBHandler = new DatabaseHelper(getContext());
                return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;

        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(AppContract.AppEntry.TABLE_NAME);

        switch (sUriMatcher.match(uri)){
            case POSTS :

                retCursor = queryBuilder.query(mDBHandler.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case POST_ID :

                String sql = " SELECT * FROM " + AppContract.AppEntry.TABLE_NAME + " WHERE " + AppContract.AppEntry._ID
                        + " = ?";

                retCursor = mDBHandler.getReadableDatabase().query(
                        AppContract.AppEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        retCursor.setNotificationUri(getContext().getContentResolver(),
                uri);

        return retCursor;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case POSTS :
                    return AppContract.AppEntry.CONTENT_ITEMS_TYPE;
            case POST_ID :
                    return AppContract.AppEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        Uri returnUri;

        SQLiteDatabase db = mDBHandler.getWritableDatabase();
        switch (match){
            case POSTS :
                Long id = db.insert(AppContract.PATH_TABLE, "", values);

                if(id > 0)
                    returnUri = AppContract.AppEntry.buildAcronymUri(id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mDBHandler.getWritableDatabase();

        switch (sUriMatcher.match(uri)){
            case POSTS :
                db.beginTransaction();
                int returnCount = 0;

                try{
                    for (ContentValues contentValues : values){
                        final long id  =
                                db.insert(AppContract.PATH_TABLE,
                                        null,
                                        contentValues);

                        if(id != 1)
                            returnCount++;
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                getContext().getContentResolver().notifyChange(uri, null);

                return returnCount;

            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted = 0;
        final SQLiteDatabase db = mDBHandler.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case POSTS:
                int id = db.delete(AppContract.PATH_TABLE,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: "+uri);
        }
        if(selection == null || rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDBHandler.getWritableDatabase();

        int rowsUpdated = 0;

        switch (sUriMatcher.match(uri)){
            case POSTS:
                int id = db.update(AppContract.PATH_TABLE,
                        values,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0)
            getContext().getContentResolver().notifyChange(uri,
                    null);
        return rowsUpdated;
    }
}
