package com.example.asus.annmitra2.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.annmitra2.R;

public class ShopActivity extends AppCompatActivity {

    Button btn_Ration_Shop, btn_PaddyGrains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
//        Toast.makeText(ShopActivity.this,"Shop Activity",Toast.LENGTH_SHORT).show();
        btn_Ration_Shop = findViewById(R.id.RationShop);
        btn_PaddyGrains = findViewById(R.id.paddyGrainsSubmission);
        btn_Ration_Shop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ShopActivity.this,RationShopActivity.class));
            }
        });
        btn_PaddyGrains.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ShopActivity.this,PaddyGrainsActivity.class));
            }
        });
    }
}
