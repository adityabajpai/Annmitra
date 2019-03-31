package com.example.asus.annmitra2.Views;

import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.annmitra2.Connection.RequestHandler;
import com.example.asus.annmitra2.Connection.URLS;
import com.example.asus.annmitra2.Controllers.NotificationAdapter;
import com.example.asus.annmitra2.Controllers.RationShopAdapter;
import com.example.asus.annmitra2.Models.Contacts;
import com.example.asus.annmitra2.Models.Notifications;
import com.example.asus.annmitra2.Models.Ration;
import com.example.asus.annmitra2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class RationShopActivity extends AppCompatActivity
{

    private RecyclerView recyclerView_RationShop;
    private List<Ration> rations = new ArrayList<>();
    private RationShopAdapter rationShopAdapter;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration_shop);
        progress=new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.setCancelable(false);
        progress.setIndeterminate(false);
        progress.show();
        new RationShopsGet().execute();
        recyclerView_RationShop = findViewById(R.id.recyclerViewRationShop);
        rationShopAdapter = new RationShopAdapter(getApplicationContext(),rations);
        recyclerView_RationShop.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_RationShop.setItemAnimator(new DefaultItemAnimator());
        recyclerView_RationShop.setAdapter(rationShopAdapter);
        rationShopAdapter.notifyDataSetChanged();
    }

    //Async method
    private class RationShopsGet extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("check",1);
            return requestHandler.sendPostRequest(URLS.allshops,hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            Ration ration1 = null;
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
                        String address=getAddress(count.getDouble("lat"),count.getDouble("lng"));
                        ration1 = new Ration(count.getString("name"),count.getString("shop_code"),
                                count.getString("shop_no"),address,count.getString("mobileno"),count.getDouble("lat"),
                                count.getDouble("lng"));
                        rations.add(ration1);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            rationShopAdapter.notifyDataSetChanged();
        }
    }

    public String getAddress(double lat, double lng)
    {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
//            add = add + "\n" + obj.getCountryName();
//            add = add + "\n" + obj.getCountryCode();
//            add = add + "\n" + obj.getAdminArea();
//            add = add + "\n" + obj.getPostalCode();
//            add = add + "\n" + obj.getSubAdminArea();
//            add = add + "\n" + obj.getLocality();
//            add = add + "\n" + obj.getSubThoroughfare();
//            add = add + "\n" + obj.getSubLocality();

            Log.e("IGA", "Address" + add);
            return add;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
