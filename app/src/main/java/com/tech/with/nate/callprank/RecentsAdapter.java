package com.tech.with.nate.callprank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.ViewHolder> {

    Activity activity;
    ArrayList<RecentContactModel> recentContactModels;

    public RecentsAdapter(Activity activity, ArrayList<RecentContactModel> recentContactModels) {
        this.activity = activity;
        this.recentContactModels = recentContactModels;
    }

    @NonNull
    @Override
    public RecentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsAdapter.ViewHolder holder, int position) {

        RecentContactModel contactModel = recentContactModels.get(position);
        holder.name.setText(contactModel.getName());
        holder.number.setText(contactModel.getNumber());
        //holder.label.setText(contactModel.getName().toUpperCase().charAt(0)+" ");

    }

    @Override
    public int getItemCount() {
        return recentContactModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView number;
        TextView label;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            label = itemView.findViewById(R.id.user_label);


        }
    }

}
