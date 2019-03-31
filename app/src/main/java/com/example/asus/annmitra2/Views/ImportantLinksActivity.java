package com.example.asus.annmitra2.Views;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.widget.Toast;

import com.example.asus.annmitra2.Connection.RequestHandler;
import com.example.asus.annmitra2.Connection.URLS;
import com.example.asus.annmitra2.Controllers.ImportantLinksAdapter;
import com.example.asus.annmitra2.Models.ImportantLinks;
import com.example.asus.annmitra2.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImportantLinksActivity extends AppCompatActivity
{

    private RecyclerView recyclerView_ImportantLinks;
    private List<ImportantLinks> importantLinks = new ArrayList<>();
    private ImportantLinksAdapter importantLinksAdapter;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_links);

//        Toast.makeText(ImportantLinksActivity.this,"ImportantLinksActivity",Toast.LENGTH_SHORT).show();
        progress=new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.setCancelable(false);
        progress.setIndeterminate(false);
        progress.show();
        new LinksGet().execute();
        recyclerView_ImportantLinks = findViewById(R.id.recyclerViewImportantLinks);
        importantLinksAdapter = new ImportantLinksAdapter(getApplicationContext(),importantLinks);

        recyclerView_ImportantLinks.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_ImportantLinks.setItemAnimator(new DefaultItemAnimator());
        recyclerView_ImportantLinks.setAdapter(importantLinksAdapter);

    }

    //Async method
    private class LinksGet extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("check","1");
            return requestHandler.sendPostRequest(URLS.links,hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            ImportantLinks importantLinks1 = null;
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
                        importantLinks1 = new ImportantLinks(count.getString("title"),count.getString("url"),count.getString("time"));
                        importantLinks.add(importantLinks1);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            importantLinksAdapter.notifyDataSetChanged();
        }
    }
}
