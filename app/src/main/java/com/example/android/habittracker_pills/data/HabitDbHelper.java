package com.example.android.habittracker_pills.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker_pills.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    //Constructor to create a new HabitDbHelper object
    //@param context is the context of the application that called the constructor

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creates the Habits database
    // @param db is a SQLiteDatabase object
    @Override
    public void onCreate(SQLiteDatabase db) {
        // String containing the SQL statement used to create the habits table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_TIME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_PILLS_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_PILLS_QUANTITY + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DELETE TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
