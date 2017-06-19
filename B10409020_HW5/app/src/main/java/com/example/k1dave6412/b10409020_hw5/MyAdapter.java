package com.example.k1dave6412.b10409020_hw5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.k1dave6412.b10409020_hw5.data.Contract;


/**
 * Created by k1dave6412 on 2017/5/29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_view_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(Contract.Entry._ID);
        int activityIndex = mCursor.getColumnIndex(Contract.Entry.COLUMN_ACTIVITY);
        int timeIndex = mCursor.getColumnIndex(Contract.Entry.COLUMN_TIME);
        int checkIndex = mCursor.getColumnIndex(Contract.Entry.COLUMN_CHECK);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String activityName = mCursor.getString(activityIndex);
        String time = mCursor.getString(timeIndex);
        boolean check;
        if (mCursor.getInt(checkIndex) == 1) {
            check = true;
        } else {
            check = false;
        }

        holder.itemView.setTag(id);
        holder.TitleTextView.setText(activityName);
        holder.TimeTextView.setText(time);
        holder.CheckBoxActivity.setChecked(check);
        holder.CheckBoxActivity.setTag(id);

        holder.CheckBoxActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContentValues contentValues = new ContentValues();
                if (buttonView.isChecked() == true) {
                    contentValues.put(Contract.Entry.COLUMN_CHECK, 1);
                } else {
                    contentValues.put(Contract.Entry.COLUMN_CHECK, 0);
                }
                int id = (int) buttonView.getTag();
                String stringId = Integer.toString(id);

                Uri uri = Contract.Entry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();

                buttonView.getContext().getContentResolver().update(uri, contentValues, null, null);
            }
        });
    }

    public Cursor swapCursor(Cursor c) {
        if (mCursor == c) {
            return null;
        }
        Cursor temp = mCursor;
        this.mCursor = c;
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TitleTextView;
        TextView TimeTextView;
        CheckBox CheckBoxActivity;

        public MyViewHolder(View View) {
            super(View);
            TitleTextView = (TextView) View.findViewById(R.id.TitleText);
            TimeTextView = (TextView) View.findViewById(R.id.TimeText);
            CheckBoxActivity = (CheckBox) View.findViewById(R.id.CheckActivity);
        }
    }
}
