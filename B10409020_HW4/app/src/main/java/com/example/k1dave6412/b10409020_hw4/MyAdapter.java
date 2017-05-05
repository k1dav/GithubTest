package com.example.k1dave6412.b10409020_hw4;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.k1dave6412.b10409020_hw4.data.DB_Contract;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    public MyAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.recycler_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(DB_Contract.DB_Entry.COLUMN_GUEST_NAME));
        int ages = mCursor.getInt(mCursor.getColumnIndex(DB_Contract.DB_Entry.COLUMN_GUEST_AGES));
        int sex = mCursor.getInt(mCursor.getColumnIndex(DB_Contract.DB_Entry.COLUMN_GUEST_SEX));
        long id = mCursor.getLong(mCursor.getColumnIndex(DB_Contract.DB_Entry._ID));

        holder.mTextNumber.setText(String.valueOf(id));
        holder.mTextName.setText(name);
        holder.mTextAge.setText(String.valueOf(ages));

        if(sex == 1){
            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.male);
            holder.mImageSex.setImageBitmap(icon);
        }else{
            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.female);
            holder.mImageSex.setImageBitmap(icon);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextNumber;
        public TextView mTextName;
        public TextView mTextAge;
        public ImageView mImageSex;

        public MyViewHolder(View view) {
            super(view);
            mTextNumber = (TextView) itemView.findViewById(R.id.numberText);
            mTextName = (TextView) itemView.findViewById(R.id.nameText);
            mTextAge = (TextView) itemView.findViewById(R.id.ageText);
            mImageSex = (ImageView) itemView.findViewById(R.id.sexImage);
        }
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();

        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }


}
