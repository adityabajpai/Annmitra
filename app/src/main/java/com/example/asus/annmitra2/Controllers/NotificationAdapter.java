package com.example.asus.annmitra2.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.annmitra2.Models.Notifications;
import com.example.asus.annmitra2.R;

import java.util.List;

/**
 * Created by asus on 9/16/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private List<Notifications> notifications;

    public NotificationAdapter(Context context, List<Notifications> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    public NotificationAdapter(List<Notifications> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card,parent,false);
        NotificationAdapter.ViewHolder viewHolder = new NotificationAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        Notifications notifications1= notifications.get(position);
        holder.textView_subject.setText(notifications1.getSubject());
        holder.textView_description.setText(notifications1.getDescription());
        holder.textView_timeStamp.setText(notifications1.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_subject,textView_description,textView_timeStamp;
        public ViewHolder(View itemView) {
            super(itemView);
            textView_subject = itemView.findViewById(R.id.subject);
            textView_description = itemView.findViewById(R.id.description);
            textView_timeStamp = itemView.findViewById(R.id.timeStamp);
        }
    }
}

