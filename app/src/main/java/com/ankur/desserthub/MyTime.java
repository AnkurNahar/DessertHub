package com.ankur.desserthub;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MyTime extends AppCompatActivity {
    private String tme;
    private int ST;
    private long initTime;
    private TextView tv;
    private Button B1;
    private  Button B2;
    private Button B3;
    private CountDownTimer CD;
    private boolean timerRunning=false;
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_time);


        //getting time from intent and converting string to int
        tme = getIntent().getStringExtra("time");
        //startTime=Long.valueOf(tme);
        ST=Integer.parseInt(tme);
        initTime=ST*60000;
        timeLeft=initTime;

        tv=findViewById(R.id.countdown);
        B1=findViewById(R.id.b_start_pause);
        B2=findViewById(R.id.b_reset);
        B3=findViewById(R.id.b_end);


        updateCDText();
    }


    //if timer paused->start timer and vice versa
    public void onSPClick(View view){
        if(timerRunning){
            pauseTimer();
        }
        else {
            startTimer();
        }
    }



    public void onRClick(View view){
        resetTimer();
    }


    public void endTimer(View view){
        finish();
    }




    private void startTimer(){
        //view updated every 1000ms or 1sec
        CD=new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft=millisUntilFinished;
                updateCDText();
            }

            @Override
            public void onFinish() {
                timerRunning=false;
                B1.setText("start");
                B1.setVisibility(View.INVISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);

            }
        }.start();
        timerRunning=true;
        B1.setText("pause");
        B2.setVisibility(View.INVISIBLE);
        B3.setVisibility(View.INVISIBLE);
    }



    private  void updateCDText(){

        int minutes=(int) timeLeft/60000;
        int seconds=(int) timeLeft/1000%60;

        //set textView text
        String TLDisplay=""+minutes+":"+seconds;
        tv.setText(TLDisplay);
    }




    private void pauseTimer(){
        CD.cancel();
        timerRunning=false;
        B1.setText("start");
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
    }





    private void resetTimer(){
        timeLeft=initTime;
        updateCDText();
        B2.setVisibility(View.INVISIBLE);
        B1.setVisibility(View.VISIBLE);
    }




}
