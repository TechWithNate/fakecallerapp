package com.tech.with.nate.callprank;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ContactFragment extends Fragment{

    RecyclerView recyclerView;
    ContactsAdapter adapter;
    ArrayList<ContactModel> contactModels = new ArrayList<>();
    EditText search_bar;



    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);

        recyclerView = view.findViewById(R.id.contact_recyclerView);
        search_bar = view.findViewById(R.id.search_bar);

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });



        //Check permission
        checkPermission();



        return view;
    }

    private void filter(String text){
        ArrayList<ContactModel> filteredList = new ArrayList<>();

        for (ContactModel c: Utils.getInstance(getContext()).getAllContacts()){
            if (c.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(c);
            }else if (c.getContact().contains(text.toLowerCase())){
                filteredList.add(c);
            }
        }
        adapter.filterList(filteredList);
    }

    private void checkPermission(){

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            // Request permission if not granted
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }else {
            // Get contacts when permission is granted
            getContactList();
        }

    }

    private void getContactList(){

        // Initialize Uri
//        Uri uri = ContactsContract.Contacts.CONTENT_URI;
//
//        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
//
//        Cursor cursor = getActivity().getContentResolver().query(
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
//                Cursor phoneCursor = getActivity().getContentResolver().query(
//                        uriPhone, null, selection, new String[]{id}, null
//                );
//
//                if (phoneCursor.moveToNext()){
//                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    ContactModel model = new ContactModel();
//                    model.setName(name);
//                    model.setContact(number);
//                    contactModels.add(model);
//                    phoneCursor.close();
//                }
//            }
//            cursor.close();
//
//        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new ContactsAdapter(this.getActivity(), Utils.getInstance(getContext()).getAllContacts(), false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED){
            getContactList();
        }else {
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            checkPermission();
        }
    }

}
