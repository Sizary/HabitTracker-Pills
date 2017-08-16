package com.example.android.habittracker_pills;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.android.habittracker_pills.data.HabitDbHelper;
import com.example.android.habittracker_pills.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {


    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private HabitDbHelper habitDbHelper;

    // Cursor object
    private Cursor takingPillsHabitsCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the database helper with the current context
        habitDbHelper = new HabitDbHelper(this);
        addHabits();

        takingPillsHabitsCursor = returntakingPillsHabitsCursor();

        // Call habits to the log
        outputCursor();

        // Delete habits table
        removeHabitsTable();
    }

    private void addHabits() {
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        // Add the habit to ContentValues object
        contentValues.put(HabitEntry.COLUMN_PILLS_NAME, "Aspirin");
        contentValues.put(HabitEntry.COLUMN_TIME, "11:00");
        contentValues.put(HabitEntry.COLUMN_PILLS_QUANTITY, 1);
        db.insert(HabitEntry.TABLE_NAME, null, contentValues);
        contentValues.clear();

        // Add the habit to ContentValues object
        contentValues.put(HabitEntry.COLUMN_PILLS_NAME, "Stoperan");
        contentValues.put(HabitEntry.COLUMN_TIME, "12:00");
        contentValues.put(HabitEntry.COLUMN_PILLS_QUANTITY, 1);
        db.insert(HabitEntry.TABLE_NAME, null, contentValues);
        contentValues.clear();

        // Add the habit to ContentValues object

        contentValues.put(HabitEntry.COLUMN_PILLS_NAME, "Aspirin");
        contentValues.put(HabitEntry.COLUMN_TIME, "13:00");
        contentValues.put(HabitEntry.COLUMN_PILLS_QUANTITY, 2);
        db.insert(HabitEntry.TABLE_NAME, null, contentValues);
        contentValues.clear();
    }

    // Get all of the habit rows containing "Aspirin"
    private Cursor returntakingPillsHabitsCursor() {

        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_PILLS_NAME,
                HabitEntry.COLUMN_TIME,
                HabitEntry.COLUMN_PILLS_QUANTITY};

        // Query the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                HabitEntry.COLUMN_PILLS_NAME + " LIKE ?",
                new String[]{"%Aspirin%"},
                null,
                null,
                null);

        // Return all data for "Aspirin" in the row to the cursor
        return cursor;
    }

    private void outputCursor() {
        try {
            Log.i(LOG_TAG, "We can find " + takingPillsHabitsCursor.getCount() +
                    " rows presenting \'Aspirin\' on the below lines: ");
            Log.i(LOG_TAG, HabitEntry._ID + " - " + HabitEntry.COLUMN_PILLS_NAME + " - " + HabitEntry.COLUMN_TIME + " - "
                    + HabitEntry.COLUMN_PILLS_QUANTITY);

            // Get the column indexes
            int idColumnIndex = takingPillsHabitsCursor.getColumnIndex(HabitEntry._ID);
            int habitColumnIndex = takingPillsHabitsCursor.getColumnIndex(HabitEntry.COLUMN_PILLS_NAME);
            int timeColumnIndex = takingPillsHabitsCursor.getColumnIndex(HabitEntry.COLUMN_TIME);
            int durationColumnIndex = takingPillsHabitsCursor.getColumnIndex(HabitEntry.COLUMN_PILLS_QUANTITY);

            // Cursor move to the next row
            while (takingPillsHabitsCursor.moveToNext()) {

                // Get the values from the database into variables
                int currentID = takingPillsHabitsCursor.getInt(idColumnIndex);

                String currentName = takingPillsHabitsCursor.getString(habitColumnIndex);
                String currentTime = takingPillsHabitsCursor.getString(timeColumnIndex);
                int currentDuration = takingPillsHabitsCursor.getInt(durationColumnIndex);

                // Print row to the log
                Log.i(LOG_TAG, currentID + " - "  + currentName + " - " + currentTime + " - "
                        + currentDuration);
            }
        } finally {
            // Close the cursor
            takingPillsHabitsCursor.close();
        }
    }

    private void removeHabitsTable(){
        // Put the database in writable mode
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        // Delete the habits table from our database
        db.delete(HabitEntry.TABLE_NAME,null,null);
    }

}
