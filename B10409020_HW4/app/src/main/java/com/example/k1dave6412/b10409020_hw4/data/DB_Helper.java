package com.example.k1dave6412.b10409020_hw4.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.k1dave6412.b10409020_hw4.data.DB_Contract.DB_Entry.*;

/**
 * Created by k1dave6412 on 2017/5/4.
 */

public class DB_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "memberList.db";
    private static final int DATABASE_VERSION = 1;

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MEMBERLIST_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                COLUMN_GUEST_AGES + " INTEGER NOT NULL, " +
                COLUMN_GUEST_SEX + " INTEGER NOT NULL " +
                "); ";

        db.execSQL(SQL_CREATE_MEMBERLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
