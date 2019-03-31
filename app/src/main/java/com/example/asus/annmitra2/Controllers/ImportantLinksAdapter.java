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

import com.example.asus.annmitra2.Models.ImportantLinks;
import com.example.asus.annmitra2.R;
import com.example.asus.annmitra2.Views.ImportantLinksActivity;

import java.util.List;

/**
 * Created by asus on 9/16/2018.
 */

public class ImportantLinksAdapter extends RecyclerView.Adapter<ImportantLinksAdapter.ViewHolder>
{

    private Context context;
    private List<ImportantLinks> importantLinks;

    public ImportantLinksAdapter(Context context, List<ImportantLinks> importantLinks)
    {
        this.context = context;
        this.importantLinks = importantLinks;
    }

    public ImportantLinksAdapter(List<ImportantLinks> importantLinks)
    {
        this.importantLinks = importantLinks;
    }

    @NonNull
    @Override
    public ImportantLinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.important_links_card, parent, false);
        ImportantLinksAdapter.ViewHolder viewHolder = new ImportantLinksAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImportantLinksAdapter.ViewHolder holder, int position)
    {
        ImportantLinks importantLinks1 = importantLinks.get(position);
        holder.textView_subject.setText(importantLinks1.getSubject());
        holder.textView_description.setText(importantLinks1.getDescription());
        holder.textView_timeStamp.setText(importantLinks1.getTimeStamp());
        holder.textView_description.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url=holder.textView_description.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return importantLinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView_subject, textView_description, textView_timeStamp;

        public ViewHolder(View itemView)
        {
            super(itemView);
            textView_subject = itemView.findViewById(R.id.subject);
            textView_description = itemView.findViewById(R.id.description);
            textView_timeStamp = itemView.findViewById(R.id.timeStamp);
        }
    }
}

