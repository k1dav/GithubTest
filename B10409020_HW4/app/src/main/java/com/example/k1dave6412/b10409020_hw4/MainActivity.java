package com.example.k1dave6412.b10409020_hw4;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.k1dave6412.b10409020_hw4.data.DB_Contract;
import com.example.k1dave6412.b10409020_hw4.data.DB_Helper;

public class MainActivity extends AppCompatActivity {
    private MyAdapter mAdapter;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Cursor cur = mDb.query(
                DB_Contract.DB_Entry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DB_Contract.DB_Entry._ID
        );;

        switch (item.getItemId()){
            case R.id.sort_age:
                cur = mDb.query(
                        DB_Contract.DB_Entry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        DB_Contract.DB_Entry.COLUMN_GUEST_AGES
                );
                break;
            case R.id.sort_gender:
                cur = mDb.query(
                        DB_Contract.DB_Entry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        DB_Contract.DB_Entry.COLUMN_GUEST_SEX
                );
                break;
            case R.id.sort_name:
                cur = mDb.query(
                        DB_Contract.DB_Entry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        DB_Contract.DB_Entry.COLUMN_GUEST_NAME
                );
                break;
            default:
                break;
        }
        mAdapter.swapCursor(cur);
        return true;
    }

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
        mAdapter.swapCursor(getAllGuests());
        guestRecycler.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                long id = (long) viewHolder.itemView.getTag();
                removeGuest(id);
                mAdapter.swapCursor(getAllGuests());
            }
        }).attachToRecyclerView(guestRecycler);
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

    private boolean removeGuest(long id) {
        return mDb.delete(DB_Contract.DB_Entry.TABLE_NAME, DB_Contract.DB_Entry._ID + "=" + id, null) > 0;
    }

    public void customDialog(View v) {
        final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog, null);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.dialog_title)
                .setView(item)
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nameText = (EditText) item.findViewById(R.id.name_input);
                        EditText ageText = (EditText) item.findViewById(R.id.age_input);
                        RadioButton female = (RadioButton) item.findViewById(R.id.female_button);

                        String name = nameText.getText().toString();
                        String age = ageText.getText().toString();

                        if (!name.isEmpty() || !age.isEmpty()) {
                            if (female.isChecked()) {
                                ContentValues cv = new ContentValues();
                                cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, name);
                                cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, Integer.parseInt(age));
                                cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 2);
                                mDb.insert(DB_Contract.DB_Entry.TABLE_NAME, null, cv);
                            } else {
                                ContentValues cv = new ContentValues();
                                cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_NAME, name);
                                cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_AGES, Integer.parseInt(age));
                                cv.put(DB_Contract.DB_Entry.COLUMN_GUEST_SEX, 1);
                                mDb.insert(DB_Contract.DB_Entry.TABLE_NAME, null, cv);
                            }
                            mAdapter.swapCursor(getAllGuests());
                        }
                    }
                }).show();

    }
}

