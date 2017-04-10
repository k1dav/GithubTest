package com.example.k1dav.b10409020_hw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private ImageButton start;
    private int ans;
    private RecyclerView mList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        myAdapter = new MyAdapter(20);
        mList = (RecyclerView) findViewById(R.id.list_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);

        start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "新回合開始！", Toast.LENGTH_SHORT).show();
                myAdapter.reset(getNumber());
            }
        });
    }

    private void init() {
        input = (EditText) findViewById(R.id.input);
        start = (ImageButton) findViewById(R.id.restart);
        ans = (int) (Math.random() * 20 + 1);
        Toast.makeText(this, "新回合開始！", Toast.LENGTH_SHORT).show();
    }

    private int getNumber() {
        return Integer.parseInt(input.getText().toString());
    }
}
