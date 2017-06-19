package com.example.k1dave6412.b10409020_hw5.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by k1dave6412 on 2017/5/29.
 */

public class DbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "list.db";
    private static final int VERSION = 1;

    DbHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE "  + Contract.Entry.TABLE_NAME + " (" +
                Contract.Entry._ID                + " INTEGER PRIMARY KEY, " +
                Contract.Entry.COLUMN_ACTIVITY + " TEXT NOT NULL, " +
                Contract.Entry.COLUMN_TIME + " TEXT NOT NULL, " +
                Contract.Entry.COLUMN_CHECK    + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Entry.TABLE_NAME);
        onCreate(db);
    }
}
