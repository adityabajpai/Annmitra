package com.example.asus.annmitra2.Views;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.asus.annmitra2.Connection.RequestHandler;
import com.example.asus.annmitra2.Connection.URLS;
import com.example.asus.annmitra2.Controllers.NotificationAdapter;
import com.example.asus.annmitra2.Models.ImportantLinks;
import com.example.asus.annmitra2.Models.Notifications;
import com.example.asus.annmitra2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView_Notifications;
    private List<Notifications> notifications = new ArrayList<>();
    private NotificationAdapter notificationAdapter;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        progress=new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.setCancelable(false);
        progress.setIndeterminate(false);
        progress.show();
        new NotificationsGet().execute();
//        Toast.makeText(NotificationActivity.this, "NotificationActivity", Toast.LENGTH_SHORT).show();

        recyclerView_Notifications = findViewById(R.id.recyclerViewNotifications);
        notificationAdapter = new NotificationAdapter(notifications);
        recyclerView_Notifications.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_Notifications.setItemAnimator(new DefaultItemAnimator());
        recyclerView_Notifications.setAdapter(notificationAdapter);

//        Notifications notifications1 = new Notifications("abc", "kjcbvinvkcacsoiqncmqoc", "13-09-2018 5:55pm");
//        notifications.add(notifications1);
//        notifications1 = new Notifications("abc", "kjcbvinvkcacsoiqncmqoc", "13-09-2018 5:55pm");
//        notifications.add(notifications1);
//        notifications1 = new Notifications("abc", "kjcbvinvkcacsoiqncmqoc", "13-09-2018 5:55pm");
//        notifications.add(notifications1);
//        notificationAdapter.notifyDataSetChanged();
    }


    //Async method
    private class NotificationsGet extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("check","1");
            return requestHandler.sendPostRequest(URLS.notif,hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            Notifications notifications1 = null;
            super.onPostExecute(s);
            progress.dismiss();
            try
            {
                JSONObject jsonObject=new JSONObject(s);
                JSONObject info=jsonObject.getJSONObject("info");
                if(info.getString("status").equals("success"))
                {
                    JSONArray records=jsonObject.getJSONArray("records");
                    for(int i=0;i<records.length();i++)
                    {
                        JSONObject count=records.getJSONObject(i);
                        notifications1 = new Notifications(count.getString("subject"),count.getString("description"),count.getString("time"));
                        notifications.add(notifications1);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            notificationAdapter.notifyDataSetChanged();
        }
    }

}
