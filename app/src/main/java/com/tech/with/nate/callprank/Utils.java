package com.tech.with.nate.callprank;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static Utils instance;

    private static ArrayList<ContactModel> allContacts;
    private static ArrayList<ContactModel> recentContacts;
    private Context context;

    private Utils(Context context){
        this.context = context;
        if (null == allContacts) {
            allContacts = new ArrayList<>();
            fetchContacts();
        }

        if (null == recentContacts) {
            recentContacts = new ArrayList<>();
        }

    }


    public static synchronized Utils getInstance(Context context) {

        if (null != instance){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }


    public static ArrayList<ContactModel> getAllContacts() {
        return allContacts;
    }

    public static ArrayList<ContactModel> getRecentContacts() {
        return recentContacts;
    }


    //Chat GPT trial
    @SuppressLint("Range")
    private void fetchContacts() {
        ContentResolver contentResolver = context.getContentResolver();
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, sort);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (phoneCursor != null && phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        ContactModel contact = new ContactModel(name, phoneNumber, id);
                        allContacts.add(contact);
                    }
                    if (phoneCursor != null) {
                        phoneCursor.close();
                    }
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        // Initialize Uri
//        Uri uri = ContactsContract.Contacts.CONTENT_URI;
//
//        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
//
//        Cursor cursor = context.getContentResolver().query(
//                uri, null, null, null, sort
//        );
//
//        if (cursor.getCount() > 0){
//
//            while (cursor.moveToNext()){
//                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
//                Cursor phoneCursor = context.getContentResolver().query(
//                        uriPhone, null, selection, new String[]{id}, null
//                );
//
//                if (phoneCursor.moveToNext()){
//                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    ContactModel model = new ContactModel();
//                    model.setName(name);
//                    model.setContact(number);
//                    allContacts.add(model);
//                    phoneCursor.close();
//                }
//            }
//            cursor.close();
//
//        }

    }

    public ContactModel getContactById(String id){
        for (ContactModel c: allContacts){
            if (c.getContactId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public boolean addToRecent(ContactModel model){
        return recentContacts.add(model);
    }

}
