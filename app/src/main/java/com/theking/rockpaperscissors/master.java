package com.theking.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class master extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        Intent intent = getIntent();
        String p1 = intent.getStringExtra("p1");
        String p2 = intent.getStringExtra("p2");
        int rounds = (intent.getIntExtra("rounds",3));
        rounds--;
        Intent intent1 = new Intent(this,game1v1.class);
        intent.putExtra("p1",p1);
        intent.putExtra("p2",p2);
        intent.putExtra("rounds",rounds);
        startActivity(intent1);
    }
}
