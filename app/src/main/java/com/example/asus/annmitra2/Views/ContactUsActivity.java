package com.example.asus.annmitra2.Views;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.annmitra2.Connection.RequestHandler;
import com.example.asus.annmitra2.Connection.URLS;
import com.example.asus.annmitra2.Controllers.ContactAdapter;
import com.example.asus.annmitra2.Controllers.RationShopAdapter;
import com.example.asus.annmitra2.Models.Contacts;
import com.example.asus.annmitra2.Models.ImportantLinks;
import com.example.asus.annmitra2.Models.Ration;
import com.example.asus.annmitra2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactUsActivity extends AppCompatActivity
{

    private RecyclerView recyclerView_ContactUs;
    private List<Contacts> contacts = new ArrayList<>();
    private ContactAdapter contactAdapter;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        progress=new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.setCancelable(false);
        progress.setIndeterminate(false);
        progress.show();
        new ContactsGet().execute();
        recyclerView_ContactUs = findViewById(R.id.recyclerViewContactUs);
        contactAdapter = new ContactAdapter(getApplicationContext(),contacts);
        recyclerView_ContactUs.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_ContactUs.setItemAnimator(new DefaultItemAnimator());
        recyclerView_ContactUs.setAdapter(contactAdapter);
    }


    //Async method
    private class ContactsGet extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("check",1);
            return requestHandler.sendPostRequest(URLS.contactus,hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            Contacts contacts1 = null;
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
                        contacts1 = new Contacts(count.getString("name"),count.getString("desgn"),count.getString("mobileno"),count.getString("area"));
                        contacts.add(contacts1);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            contactAdapter.notifyDataSetChanged();
        }
    }
}
