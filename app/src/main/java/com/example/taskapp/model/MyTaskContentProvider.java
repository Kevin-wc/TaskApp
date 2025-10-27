package com.example.taskapp.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyTaskContentProvider extends ContentProvider {
    private MainDatabaseHelper mHelper;
    public final static String DB_NAME = "TaskDB";
    public final static String COLUMN_TASK = "task";
    public final static String COLUMN_OWNER = "owner";
    public final static String TABLE_NAME = "TaskTable";

    public final static String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                    " _ID INTEGER PRIMARY KEY, " +
                    COLUMN_TASK + " TEXT, " +
                    COLUMN_OWNER + " TEXT )";
    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.taskapp.model.provider");

    protected final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context){
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            mHelper = new MainDatabaseHelper(getContext());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public MyTaskContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String owner = values.getAsString(COLUMN_OWNER);
        String task = values.getAsString(COLUMN_TASK);

        long id = mHelper.getWritableDatabase().insert("TaskTable", null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs
        ,null,null,sortOrder);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}