package com.example.asus.annmitra2.Controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.annmitra2.Models.Contacts;
import com.example.asus.annmitra2.Models.DownloadDocument;
import com.example.asus.annmitra2.R;

import java.util.List;

/**
 * Created by asus on 9/22/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private List<Contacts> contacts;

    public ContactAdapter(Context context, List<Contacts> downloadDocuments) {
        this.context = context;
        this.contacts = downloadDocuments;
    }

    public ContactAdapter(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card, parent, false);
        ContactAdapter.ViewHolder viewHolder = new ContactAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactAdapter.ViewHolder holder, final int position) {
        Contacts con = contacts.get(position);
        holder.textView_name.setText(con.getName());
        holder.textView_officerRank.setText(con.getOfficerRank());
        holder.textView_MobileNo.setText(con.getMobileno());
        holder.textView_area.setText(con.getArea());
        holder.textView_MobileNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone=holder.textView_MobileNo.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name, textView_officerRank, textView_MobileNo,textView_area;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.nameOfOfficer);
            textView_officerRank = itemView.findViewById(R.id.officerRank);
            textView_MobileNo = itemView.findViewById(R.id.contactNumber);
            textView_area=itemView.findViewById(R.id.contactArea);

        }
    }
}
