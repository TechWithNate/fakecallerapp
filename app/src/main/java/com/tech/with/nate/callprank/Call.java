package com.tech.with.nate.callprank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Call extends AppCompatActivity {

    TextView contact_calling;
    TextView contact_number;
    ImageView decline_call, accept_btn;
    ArrayList<RecentContactModel> model = new ArrayList<>();
    String name;
    String tel;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Uri uri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        Ringtone tone = RingtoneManager.getRingtone(getApplicationContext(), uri);
        //tone.play();

        MediaPlayer player = MediaPlayer.create(getApplicationContext(), uri);
        player.start();
        player.setLooping(true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        contact_calling = findViewById(R.id.contact_calling);
        contact_number = findViewById(R.id.contact_number);
        decline_call = findViewById(R.id.decline_call);
        accept_btn = findViewById(R.id.accept_call);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        tel = intent.getStringExtra("number");

        contact_calling.setText(name + " Calling...");
        contact_number.setText(tel);

        decline_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                finish();
//                Intent i = new Intent(Call.this, SettingsFragment.class);
//                i.putExtra("name", name);
//                i.putExtra("tel", tel);
//                startActivity(i);


            }
        });

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Call.this, CallProgress.class);
                i.putExtra("name", name);
                i.putExtra("number", tel);
                startActivity(i);
                player.stop();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}