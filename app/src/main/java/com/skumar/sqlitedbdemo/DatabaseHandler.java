package com.skumar.sqlitedbdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + AppConstants.TABLE_STUDENT + "(" +
                AppConstants.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AppConstants.STUDENT_NAME + " TEXT, " +
                AppConstants.STUDENT_EMAIL + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + AppConstants.TABLE_STUDENT;
        db.execSQL(sql);
        onCreate(db);
    }
}
