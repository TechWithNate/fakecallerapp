package com.tech.with.nate.callprank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactDetails extends AppCompatActivity {

    public static final String CONTACT_ID_KEY = "contactId";

    private TextView username;
    private TextView number;
    private RelativeLayout bottom_layer;
    private ImageView arrow_back;
    private ImageView sendMessage;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        username = findViewById(R.id.username);
        number = findViewById(R.id.contact);
        bottom_layer = findViewById(R.id.bottom_layer);
        arrow_back = findViewById(R.id.back_arrow);
        sendMessage = findViewById(R.id.message);



        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        if (null != intent) {
            String contactId = intent.getStringExtra(CONTACT_ID_KEY);
            ContactModel inComingContact = Utils.getInstance(getApplicationContext()).getContactById(contactId);
            if (null != inComingContact) {
                setData(inComingContact);

                handleRecentContacts(inComingContact);
            }
        }



//        bottom_layer.setOnClickListener(view -> {
//            Intent intent1 = new Intent(ContactDetails.this, Call.class);
//            intent1.putExtra("name", username.getText());
//            intent1.putExtra("number", contact.getText());
//            startActivity(intent1);
//        });

//        View view = findViewById(R.id.my_view);
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//                    // Handle volume up key press
//                    Intent intent1 = new Intent(ContactDetails.this, Call.class);
//                    intent1.putExtra("name", "Nathan");
//                    intent1.putExtra("number", "0122443422");
//                    startActivity(intent1);
//                    return true;
//                } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//                    // Handle volume down key press
//                    return true;
//                }
//                return false;
//            }
//        });

    }
    /*

     */

    private void handleRecentContacts(ContactModel contact) {
        //TODO: Check if any error
        ArrayList<ContactModel> recentCalls = Utils.getInstance(getApplicationContext()).getRecentContacts();
        makeCall(contact);

       // boolean alreadyCalled = false;
//
//        for (ContactModel c: recentCalls){
//            if (c.getContactId().equals(contact.getContactId())){
//                alreadyCalled = true;
//            }
//        }
//
//        if (alreadyCalled){
//            //TODO: Increment called times
//            makeCall(contact);
//        }else{
//            makeCall(contact);
//        }

    }

    public void setData(ContactModel contact){
        username.setText(contact.getName());
        number.setText(contact.getContact());
    }

    private void makeCall(ContactModel contact){
        bottom_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getInstance(getApplicationContext()).addToRecent(contact)){
                    //TODO: Navigate to make call activity
                    Intent intent = new Intent(ContactDetails.this, Call.class);
                    intent.putExtra("name", username.getText());
                    intent.putExtra("number", number.getText());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        int action, keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (KeyEvent.ACTION_UP == action){
                    //TODO: Make Call
                    Toast.makeText(this, "Volume Up Pressed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContactDetails.this, Call.class);
                    intent.putExtra("name", username.getText());
                    intent.putExtra("number", number.getText());
                    startActivity(intent);
                    finish();
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                    if (KeyEvent.ACTION_DOWN == action){
                        //TODO: Set an action
                        Toast.makeText(this, "Volume Down Pressed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ContactDetails.this, Call.class);
                        intent.putExtra("name", username.getText());
                        intent.putExtra("number", number.getText());
                        startActivity(intent);
                        finish();
                    }
                    break;
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPause() {



        super.onPause();
    }
}