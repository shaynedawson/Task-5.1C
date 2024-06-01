package com.example.itube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "iTube.db"; // Database name
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_USERS = "users";
    private static final String TABLE_PLAYLIST = "playlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_URL = "url";
    private static final String TAG = "DatabaseHelper";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FULLNAME + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE); // Execute SQL statement to create users table

        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " + TABLE_PLAYLIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_URL + " TEXT" + ")";
        db.execSQL(CREATE_PLAYLIST_TABLE); // Execute SQL statement to create playlist table


    }

    // Upgrades the database when the version number is increased
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS); // Drop users table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST); // Drop playlist table if it exists
        onCreate(db); // Recreate tables

        Log.i(TAG, "Database upgraded from version " + oldVersion + " to " + newVersion); // Log upgrade
    }

    // Adds a new user to the database
    public boolean addUser(String fullname, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get writable database
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, fullname);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values); // Insert user into database
        db.close(); // Close database

        return result != -1; // Returns true if insert was successful
    }

    // Checks if a user exists with the given username and password
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_ID };
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null); // Query database

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    // Adds a video URL to the playlist table
    public boolean addVideoToPlaylist(String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL, url);

        long result = db.insert(TABLE_PLAYLIST, null, values); // Insert URL into playlist table
        db.close();

        if (result == -1) {
            Log.e(TAG, "Failed to insert URL: " + url); // Log failure
            return false;
        } else {
            Log.i(TAG, "Successfully inserted URL: " + url); // Log success
            return true;
        }
    }

    // Retrieves all video URLs from the playlist table
    public Cursor getPlaylist() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PLAYLIST, null);
    }
}
