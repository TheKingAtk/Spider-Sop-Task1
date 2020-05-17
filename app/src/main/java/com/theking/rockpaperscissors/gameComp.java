package com.theking.rockpaperscissors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class gameComp extends AppCompatActivity {
    private long startTime=4000,saveTime;
    int[] score = {0,0};
    FrameLayout con1,con2;
    String p1f,p2f;
    StringBuilder p1,p2,r;
    int rounds;
    ImageView p1Chose,p2Chose;
    int firstClick=0,p1Code,p2Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_comp);
        p1=new StringBuilder();
        p2=new StringBuilder();
        r=new StringBuilder();
        Intent intent = getIntent();
        TextView roundsLeft = findViewById(R.id.roundScreen);

        con1 = findViewById(R.id.container1);
        con2 = findViewById(R.id.container2);
        if(savedInstanceState !=null) {
            startTime=savedInstanceState.getLong("startTime");
            score[0]=savedInstanceState.getInt("score1");
            score[1]=savedInstanceState.getInt("score2");
        }
        p1f=intent.getStringExtra("p1");
        p2f="Comp";
        score=intent.getIntArrayExtra("score");
        p1.append(intent.getStringExtra("p1")).append("\t\t\t:").append(score[0]);
        p2.append("Comp").append("\t\t\t:").append(score[1]);
        TextView textView = findViewById(R.id.p1),textView1=findViewById(R.id.p2);
        textView.setText(p1);
        textView1.setText(p2);
        rounds = intent.getIntExtra("rounds",3);
        r.append("Rounds Left : ").append(rounds-1);
        roundsLeft.setText(r);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("startTime",saveTime);
        savedInstanceState.putInt("score1",score[0]);
        savedInstanceState.putInt("score2",score[1]);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onGame1(View view) {
        ImageView i1 = findViewById(R.id.rock1);
        ImageView i2 = findViewById(R.id.paper1);
        ImageView i3 = findViewById(R.id.scissor1);
        i1.setOnClickListener(null);
        i2.setOnClickListener(null);
        i3.setOnClickListener(null);
        if(view==i1) {
            p2Code=0;
        }
        else if(view==i2) {
            p2Code=1;
        }
        else if(view==i3) {
            p2Code=2;
        }
        p1Chose=(ImageView) view;
        firstClick++;
        Random random = new Random();
        p2Code=random.nextInt(3);
        firstClick++;
        if(p2Code==0) {
            p2Chose=findViewById(R.id.rock2);
        }
        else if(p2Code==1) {
            p2Chose=findViewById(R.id.paper2);
        }
        else {
            p2Chose=findViewById(R.id.scissor2);
        }
        clickHap();
    }

    public void clickHap() {
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(firstClick!=2) {
                    Intent intent = new Intent(gameComp.this,gameComp.class);
                    intent.putExtra("p1",p1f);
                    intent.putExtra("p2",p2f);
                    intent.putExtra("rounds",rounds);
                    intent.putExtra("score",score);
                    startActivity(intent);
                }
            }
        };
        if(firstClick==1) {
            countDownTimer.start();
        }
        else {
            countDownTimer.cancel();
            check();
        }

    }
    public void check() {
        int scorer;
        ImageView[] imageViews = new ImageView[6];
        imageViews[0] = findViewById(R.id.rock1);
        imageViews[1] = findViewById(R.id.paper1);
        imageViews[2] = findViewById(R.id.scissor1);
        imageViews[3] = findViewById(R.id.rock2);
        imageViews[4] = findViewById(R.id.paper2);
        imageViews[5] = findViewById(R.id.scissor2);
        for(int i=0;i<6;i++) {
            if(imageViews[i]!=p1Chose && imageViews[i]!=p2Chose) {
                imageViews[i].setImageResource(R.color.yellow);
            }
        }
        if(p1Code>p2Code) {
            if(p1Code==2 && p2Code==0) {
                con2.setBackgroundColor(Color.RED);
                con1.setBackgroundColor(Color.GREEN);
                scorer=1;
                score[0]++;
            }
            else {
                con1.setBackgroundColor(Color.RED);
                con2.setBackgroundColor(Color.GREEN);
                scorer=2;
                score[1]++;
            }
        }
        else if (p1Code<p2Code){
            if(p1Code==0 && p2Code==2) {
                con1.setBackgroundColor(Color.RED);
                con2.setBackgroundColor(Color.GREEN);
                scorer=2;
                score[1]++;
            }
            else {
                con2.setBackgroundColor(Color.RED);
                con1.setBackgroundColor(Color.GREEN);
                scorer=1;
                score[0]++;
            }
        }

        if(rounds>1) {
            final Intent intent = new Intent(this,gameComp.class);
            intent.putExtra("p1",p1f);
            intent.putExtra("p2",p2f);
            intent.putExtra("rounds",rounds-1);
            intent.putExtra("score",score);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 1000);
        }
        else
        {   p1.delete(0,p1.length());
            p2.delete(0,p2.length());
            if(score[0]>score[1]) {
                p1.append(p1f).append(" WINS!!");
                p2.append(p2f).append(" LOOSES!!");
            }
            else if(score[0]<score[1]) {
                p2.append(p2f).append(" WINS!!");
                p1.append(p1f).append(" LOOSES!!");
            }
            else {
                p2.append(p2f).append(" DRAWS!!");
                p1.append(p1f).append(" DRAWS!!");
            }
            TextView textView = findViewById(R.id.p1),textView1=findViewById(R.id.p2);
            textView.setText(p1);
            textView1.setText(p2);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent main = new Intent(gameComp.this,MainActivity.class);
                    startActivity(main);
                }
            }, 2000);
        }
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(gameComp.this,MainActivity.class);
        startActivity(main);
    }
}
