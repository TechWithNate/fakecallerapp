package com.tech.with.nate.callprank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        FrameLayout content = findViewById(R.id.content);
        replaceFragment(new RecentFragment());

//        View view = findViewById(R.id.my_view);
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//                    // Handle volume up key press
//                    Intent intent = new Intent(Home.this, Call.class);
//                    intent.putExtra("name", "Nathan");
//                    intent.putExtra("number", "0122443422");
//                    startActivity(intent);
//                    return true;
//                } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//                    // Handle volume down key press
//                    return true;
//                }
//                return false;
//            }
//        });



        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.recent_menu:
                        replaceFragment(new RecentFragment());
                        break;
                    case R.id.contact_menu:
                        replaceFragment(new ContactFragment());
                        break;
                    case R.id.settings_menu:
                        replaceFragment(new SettingsFragment());
                        break;
                }


                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        int action, keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (KeyEvent.ACTION_UP == action){

                    Intent intent = new Intent(Home.this, Call.class);
                    intent.putExtra("name", "Nathan");
                    intent.putExtra("number", "012452512554");
                    startActivity(intent);
                    finish();
                }
                break;

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (KeyEvent.ACTION_DOWN == action){
                    Intent intent = new Intent(Home.this, Call.class);
                    intent.putExtra("name", "Nathan");
                    intent.putExtra("number", "012452512554");
                    startActivity(intent);
                    finish();
                }
                break;
        }

        return super.dispatchKeyEvent(event);
    }

}