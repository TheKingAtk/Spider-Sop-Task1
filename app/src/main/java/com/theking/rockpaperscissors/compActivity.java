package com.theking.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class compActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp);
    }
    public void start (View view) {
        EditText p1name = findViewById(R.id.p1name);
        String p1 = p1name.getText().toString();
        EditText nor = findViewById(R.id.nor);

        if(p1.length()==0) {
            Toast toast = Toast.makeText(this,"Enter P1's name",Toast.LENGTH_SHORT);
            toast.show();
        }

        else if (nor.getText().toString().length()==0) {
            Toast toast = Toast.makeText(this,"Enter number of rounds",Toast.LENGTH_SHORT);
            toast.show();
        }
        else  {
            int rounds = Integer.parseInt(nor.getText().toString());
            Intent intent = new Intent(this,gameComp.class);
            intent.putExtra("p1",p1);
            intent.putExtra("rounds",rounds);
            int[] score = {0,0};
            intent.putExtra("score",score);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
    }
}
