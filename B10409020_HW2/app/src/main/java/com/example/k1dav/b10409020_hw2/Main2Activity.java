package com.example.k1dav.b10409020_hw2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static int count = 1;
    private String[] song={"Hello", "it's me", "I was wondering",
            "If after all these years you'd like to meet", "to go over" ,"everything",
            "They say that time's supposed to heal", "yeah But I ain't done much healing",
            "Hello","can you hear me?","I'm in California dreaming about who we used to be",
            "When we were younger","and free","I've forgotten how it felt before the world fell at our feet",
            "There's such a difference","between us","And a million miles","Hello from the other side",
            "I must've called a thousand times","To tell you I'm sorry","for everything that I've done",
            "But when I call you never seem to be home","Hello from the outside","At least I can say that I've tried",
            "To tell you I'm sorry","for breaking your heart","But it don't matter, it clearly doesn't tear you apart anymore" };
    private TextView lrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lrc=(TextView)findViewById(R.id.textView) ;    //textview set to lrc
        if(this.count<song.length){
            lrc.setText(song[count]);
        }else{
            lrc.setText("No more Lrc");
        }

    }

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.ReturnToActivityI) {
           /* Context context = Main2Activity.this;
            String textToShow = "Return To ActivityI";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();*/
            this.count+=2;

            //toActivity2
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
