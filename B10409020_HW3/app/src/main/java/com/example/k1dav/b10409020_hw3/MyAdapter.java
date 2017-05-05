package com.example.k1dav.b10409020_hw3;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private int min, max, ans;
    private int[] status;
    private String toastMessage;

    public MyAdapter(int items) {
        reset(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public LinearLayout mRoot;

        public ViewHolder(View view) {
            super(view);
            mRoot = (LinearLayout) itemView.findViewById(R.id.linear);
            mTextView = (TextView) itemView.findViewById(R.id.info_text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (guess(getPosition())) {
                Toast.makeText(v.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        }
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTextView.setText(String.valueOf(position + 1));
        if (status[position] == 0) {
            holder.mRoot.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else if (status[position] == -1) {
            holder.mRoot.setBackgroundColor(Color.parseColor("#b4b4b4"));
        } else if (status[position] == 1) {
            holder.mRoot.setBackgroundColor(Color.parseColor("#ff4747"));
        }
    }

    @Override
    public int getItemCount() {
        return status.length;
    }

    public void reset(int num) {
        ans = (int) (Math.random() * num);
        min = 0;
        max = num;
        status = new int[num];
        for (int i = 0; i < getItemCount(); i++) {
            Array.setInt(this.status, i, 0);
        }
        notifyDataSetChanged();
        Log.d("ans", Integer.toString(ans));
    }

    public boolean guess(int position) {
        if (position < min || position > max) {
            return false;
        }
        if (position == ans) {
            status[position] = 1;
            toastMessage = "恭喜你答對了！";
            return true;
        } else if (position < ans) {
            for (int i = min; i <= position; i++) {
                status[i] = -1;
            }
            toastMessage = "比" + (position + 1) + "大";
            min = position;
        } else if (position > ans) {
            for (int i = position; i < max; i++) {
                status[i] = -1;
            }
            toastMessage = "比" + (position + 1) + "小";
            max = position;
        }
        return true;
    }
}
