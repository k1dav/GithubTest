package com.example.k1dave6412.b10409020_hw5;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.k1dave6412.b10409020_hw5.data.Contract;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private MyAdapter mAdapter;
    RecyclerView timeRecycler;

    private static final int TASK_LOADER_ID = 290;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeRecycler= (RecyclerView)findViewById(R.id.time_list);
        timeRecycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MyAdapter(this);
        timeRecycler.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle loaderArgs) {

        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mTaskData = null;

            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {

                try {
                    return getContentResolver().query(Contract.Entry.CONTENT_URI,
                            null,
                            null,
                            null,
                            Contract.Entry.COLUMN_ACTIVITY);

                } catch (Exception e) {
                    Log.e("Loader_Error", "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public void customDialog(View view) {
        final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.time_choose, null);
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
                        EditText TaskName = (EditText) item.findViewById(R.id.TimeTitle);
                        TimePicker timePicker = (TimePicker) item.findViewById(R.id.TimePicker);

                        String input = TaskName.getText().toString();
                        int Hour = timePicker.getHour();
                        int Min = timePicker.getMinute();

                        String Time = Integer.toString(Hour) + " : " + Integer.toString(Min);
                        if(Min==0){
                            Time+="0";
                        }

                        if (input.length() == 0) {
                            return;
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Contract.Entry.COLUMN_ACTIVITY, input);
                        contentValues.put(Contract.Entry.COLUMN_TIME,Time );
                        contentValues.put(Contract.Entry.COLUMN_CHECK,1 );

                        Uri uri = getContentResolver().insert(Contract.Entry.CONTENT_URI, contentValues);

                        if(uri != null) {
                            getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, MainActivity.this);
                        }

                    }
                }).show();

    }

}
