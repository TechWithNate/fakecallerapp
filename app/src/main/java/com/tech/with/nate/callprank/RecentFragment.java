package com.tech.with.nate.callprank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecentFragment extends Fragment {

    RecyclerView recyclerView;
    ContactsAdapter adapter;
    ArrayList<ContactModel> contactModels;
    EditText search_bar;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_fragment, container, false);

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
                filter(editable.toString().trim());
            }
        });

//        Intent intent = getActivity().getIntent();
//        String name = intent.getStringExtra("name");
//        String contact = intent.getStringExtra("tel");
//        RecentContactModel contactModel = new RecentContactModel();
//        contactModel.setNumber(name);
//        contactModel.setNumber(contact);
//        recentContactModels.add(contactModel);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        adapter = new RecentsAdapter(this.getActivity(), recentContactModels);
//        recyclerView.setAdapter(adapter);


        recyclerView = view.findViewById(R.id.recent_recyclerView);
        contactModels = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new ContactsAdapter(getActivity(), Utils.getInstance(getActivity()).getRecentContacts(), true);
        recyclerView.setAdapter(adapter);


        return view;
    }

    public void filter(String search){
        ArrayList<ContactModel> filteredList = new ArrayList<>();

        for (ContactModel c: Utils.getInstance(getContext()).getRecentContacts()){
            if (c.getName().toLowerCase().trim().contains(search.toLowerCase().trim())){
                filteredList.add(c);
            }else if (c.getContact().contains(search)){
                filteredList.add(c);
            }
        }
        adapter.filterList(filteredList);

    }

}
