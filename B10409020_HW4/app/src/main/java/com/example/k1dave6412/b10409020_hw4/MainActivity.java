package com.example.k1dave6412.b10409020_hw4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.k1dave6412.b10409020_hw4.data.DB_Contract;
import com.example.k1dave6412.b10409020_hw4.data.DB_Helper;
import com.example.k1dave6412.b10409020_hw4.data.TestUtil;

public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter;
    private SQLiteDatabase mDb;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView guestRecycler;

        guestRecycler = (RecyclerView) this.findViewById(R.id.guest_list);
        guestRecycler.setLayoutManager(new LinearLayoutManager(this));

        DB_Helper dbHelper = new DB_Helper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllGuests();

        mAdapter = new MyAdapter(this, cursor);
        guestRecycler.setAdapter(mAdapter);

        new TestUtil().insertFakeData(mDb);


    }

    private Cursor getAllGuests() {
        return mDb.query(
                DB_Contract.DB_Entry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DB_Contract.DB_Entry._ID
        );
    }
}

