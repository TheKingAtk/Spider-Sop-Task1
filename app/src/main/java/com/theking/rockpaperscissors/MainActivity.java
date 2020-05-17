package com.theking.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dual(View view ){
        Intent intent = new Intent(this,DualActivity.class);
        startActivity(intent);
    }
    public void comp(View view) {
        Intent intent = new Intent(this,compActivity.class);
        startActivity(intent);
    }
}
