package com.tech.with.nate.callprank;

import static com.tech.with.nate.callprank.ContactDetails.CONTACT_ID_KEY;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    Activity activity;
    ArrayList<ContactModel> contactModels;
    private boolean isVisible;


    public ContactsAdapter(Activity activity, ArrayList<ContactModel> contactModels, boolean isVisible) {
        this.activity = activity;
        this.contactModels = contactModels;
        this.isVisible = isVisible;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        ContactModel model = contactModels.get(position);
        holder.name.setText(model.getName());
        holder.number.setText(model.getContact());
        holder.label.setText(model.getName().toUpperCase().charAt(0)+" ");
        holder.number.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        //holder.callTimes.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        holder.detailsBtn.setVisibility(isVisible ? View.VISIBLE : View.GONE);


        //Setting on Click Listener for the recycler View
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ContactDetails.class);
                intent.putExtra(CONTACT_ID_KEY, model.getContactId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    //Creating a filter list to get contacts
    public void filterList(ArrayList<ContactModel> filteredList){
        this.contactModels = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView name;
        TextView number;
        TextView label;
        //TextView callTimes;
        ImageView detailsBtn;
        RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            label = itemView.findViewById(R.id.user_label);
            parent = itemView.findViewById(R.id.parent);
            //callTimes = itemView.findViewById(R.id.call_times);
            detailsBtn = itemView.findViewById(R.id.detailsBtn);


        }




//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            Toast.makeText(activity, view.getId() + " Clicked", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(activity, ContactDetails.class);
//            intent.putExtra("name", contactModels.get(position).getName());
//            intent.putExtra("number", contactModels.get(position).getContact());
//            activity.startActivity(intent);
//        }
    }
}
