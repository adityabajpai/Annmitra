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
import com.example.asus.annmitra2.Controllers.DownloadDocumentAdapter;
import com.example.asus.annmitra2.Models.DownloadDocument;
import com.example.asus.annmitra2.Models.ImportantLinks;
import com.example.asus.annmitra2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadDocumentActivity extends AppCompatActivity
{

    private RecyclerView recyclerView_DownloadDocuments;
    private List<DownloadDocument> downloadDocuments = new ArrayList<>();
    private DownloadDocumentAdapter downloadDocumentAdapter;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_document);
        progress=new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.setCancelable(false);
        progress.setIndeterminate(false);
        progress.show();
        new DocxGet().execute();
//        Toast.makeText(DownloadDocumentActivity.this,"DownloadDocumentActivity",Toast.LENGTH_SHORT).show();
        recyclerView_DownloadDocuments = findViewById(R.id.recyclerViewDownloadDocuments);
        downloadDocumentAdapter = new DownloadDocumentAdapter(getApplicationContext(),downloadDocuments);
        recyclerView_DownloadDocuments.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_DownloadDocuments.setItemAnimator(new DefaultItemAnimator());
        recyclerView_DownloadDocuments.setAdapter(downloadDocumentAdapter);
        downloadDocumentAdapter.notifyDataSetChanged();
    }

    //Async method
    private class DocxGet extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("check","1");
            return requestHandler.sendPostRequest(URLS.downloads,hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            DownloadDocument importantLinks1 = null;
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
                        importantLinks1 = new DownloadDocument(count.getString("title"),count.getString("url"),count.getString("time"));
                        downloadDocuments.add(importantLinks1);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            downloadDocumentAdapter.notifyDataSetChanged();
        }
    }
}
