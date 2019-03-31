package com.example.asus.annmitra2.Views;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.annmitra2.Connection.RequestHandler;
import com.example.asus.annmitra2.Connection.URLS;
import com.example.asus.annmitra2.MainActivity;
import com.example.asus.annmitra2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SubmitQueryActivity extends AppCompatActivity
{

    private TextInputEditText name,mobile,area,query;
    private Button hit;
    String getName,getArea,getQuery;
    long getMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_query);

        //storing references of each view element
        name=findViewById(R.id.Name);
        mobile=findViewById(R.id.MobileNo);
        area=findViewById(R.id.Village);
        query=findViewById(R.id.Query);
        hit=findViewById(R.id.submit);

        hit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getName=name.getText().toString();
                String getterMobile=mobile.getText().toString();
                getArea=area.getText().toString();
                getQuery=query.getText().toString();
                if(getName.length()==0||getArea.length()==0||getQuery.length()==0||getterMobile.length()!=10)
                {
                    Toast.makeText(getApplicationContext(),R.string.incorrect_fields,Toast.LENGTH_SHORT).show();
                    name.setText("");
                    mobile.setText("");
                    area.setText("");
                    query.setText("");
                }
                else
                {
                    getMobile=Long.parseLong(getterMobile);
                    new PostQuery().execute();
                }
            }
        });
    }

    //performing background tasks
    private class PostQuery extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("name",getName);
            hm.put("mobileno",getMobile);
            hm.put("area",getArea);
            hm.put("city","ताता");
            hm.put("myquery",getQuery);
            Log.d("sent data",""+hm);
            return requestHandler.sendPostRequest(URLS.myquery,hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Log.d("status",s);
            if(s.length()!=0)
            {
                try
                {
                    JSONObject jsonObject=new JSONObject(s);
                    JSONObject info=jsonObject.getJSONObject("info");
                    if(info.getString("status").equals("success"))
                    {
                        Toast.makeText(SubmitQueryActivity.this,"दज हो गया",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SubmitQueryActivity.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(SubmitQueryActivity.this,"फिर से डाले",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        mobile.setText("");
                        area.setText("");
                        query.setText("");
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
}
