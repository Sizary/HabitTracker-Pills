package com.example.android.habittracker_pills.data;

import android.provider.BaseColumns;

public final class HabitContract {

    // Empty constructor
    private HabitContract(){}

    // Inner class to define database table and columns
    public static final class HabitEntry implements BaseColumns{

        //Table Name
        public final static String TABLE_NAME = "habits";

        //Table Columns
        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PILLS_NAME = "name";
        public final static String COLUMN_TIME = "time";
        public final static String COLUMN_PILLS_QUANTITY = "quantity";
    }
}
