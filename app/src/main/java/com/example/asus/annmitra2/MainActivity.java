package com.example.asus.annmitra2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asus.annmitra2.Views.ContactUsActivity;
import com.example.asus.annmitra2.Views.DownloadDocumentActivity;
import com.example.asus.annmitra2.Views.ImportantLinksActivity;
import com.example.asus.annmitra2.Views.NotificationActivity;
import com.example.asus.annmitra2.Views.ShopActivity;
import com.example.asus.annmitra2.Views.SubmitQueryActivity;

public class MainActivity extends AppCompatActivity
{

    private LinearLayout linearLayout_Notification, linearLayout_shopActivity,
            linearLayout_importantLinks, linearLayout_downloadDocument, linearLayout_contactUs, linearLayout_submitQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isPermissionGranted())
        {
            isPermissionGranted();
        }
        //getting references of each view to be clicked
        linearLayout_Notification = findViewById(R.id.notificationActivity);
        linearLayout_shopActivity = findViewById(R.id.shopActivity);
        linearLayout_importantLinks = findViewById(R.id.importantLinksActivity);
        linearLayout_downloadDocument = findViewById(R.id.downloadDocumentsActivity);
        linearLayout_contactUs = findViewById(R.id.contactUsActivity);
        linearLayout_submitQuery = findViewById(R.id.submitQueryActivity);
        linearLayout_contactUs = findViewById(R.id.linearLayoutContactUs);

        //event handling on each view to start respective activities
        linearLayout_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        linearLayout_shopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopActivity.class));
            }
        });

        linearLayout_importantLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImportantLinksActivity.class));
            }
        });

        linearLayout_downloadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DownloadDocumentActivity.class));
            }
        });
        linearLayout_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
            }
        });

        linearLayout_submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubmitQueryActivity.class));
            }
        });
    }

    public  boolean isPermissionGranted()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)
            {

                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
                {
                    return true;
                }
                else
                {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    return false;
                }
            }
            else
            {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        }
        else
        { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
//        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){


            // permission granted

        }
    }
}
