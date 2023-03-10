package com.tech.with.nate.callprank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CallProgress extends AppCompatActivity {
    
    
    TextView contact_name, contact_number, timer_label;
    Timer timer;
    TimerTask timerTask;
    double time = 0.0;
    ImageView end_call, hold_call;
    boolean timer_started = true;
    boolean timer_hold = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_progress);
        
        contact_name = findViewById(R.id.contact_name);
        contact_number = findViewById(R.id.contact_number);
        timer_label = findViewById(R.id.timer);
        end_call = findViewById(R.id.end_call);
        hold_call = findViewById(R.id.hold);
        timer = new Timer();

        startTimer();


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String tel = intent.getStringExtra("number");

        contact_name.setText(name);
        contact_number.setText(tel);

        end_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerTask.cancel();
                finish();
            }
        });
        
        
    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timer_label.setText(getTimerText());
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText(){
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }
}