package com.example.k1dave6412.b10409020_hw4.data;

/**
 * Created by k1dave6412 on 2017/5/4.
 */


import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, "John");
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, 12);
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, "Tim");
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, 2);
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, "Jessica");
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, 99);
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, "Larry");
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, 1);
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, "Kim");
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, 45);
        cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 1);
        list.add(cv);

        try
        {
            db.beginTransaction();
            db.delete (DB_Contract.DB_Entry.TABLE_NAME,null,null);

            for(ContentValues c:list){
                db.insert(DB_Contract.DB_Entry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}
