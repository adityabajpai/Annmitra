package com.example.asus.annmitra2.Controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.annmitra2.Models.DownloadDocument;
import com.example.asus.annmitra2.Models.Ration;
import com.example.asus.annmitra2.R;

import java.util.List;

/**
 * Created by asus on 9/21/2018.
 */

public class RationShopAdapter extends RecyclerView.Adapter<RationShopAdapter.ViewHolder> {

    private Context context;
    private List<Ration> rations;

    public RationShopAdapter(Context context, List<Ration> rations1) {
        this.context = context;
        this.rations = rations1;
    }

    public RationShopAdapter(List<Ration> rations) {
        this.rations = rations;
    }

    @NonNull
    @Override
    public RationShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ration_card, parent, false);
        RationShopAdapter.ViewHolder viewHolder = new RationShopAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RationShopAdapter.ViewHolder holder, int position) {
        final Ration ration = rations.get(position);
        holder.textView_name.setText(ration.getName());
        holder.textView_shop_code.setText(ration.getShop_code());
        holder.textView_shop_number.setText(ration.getShop_number());
        holder.textView_address.setText(ration.getAddress());
        holder.textView_mobileno.setText(ration.getMobileno());
        holder.textView_mobileno.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone=holder.textView_mobileno.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                context.startActivity(intent);
            }
        });
        holder.directionsLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("geo:0,0?q=28.752925,77.498721(kiet)"));
                intent.setData(Uri.parse("geo:0,0?q="+ration.getLat()+","+ration.getLng()+" (Shop owner "+ration.getName()+")"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name, textView_shop_code, textView_shop_number, textView_address,textView_mobileno;
        public LinearLayout directionsLayout;
        public Button direction;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.nameOfOwner);
            textView_shop_code = itemView.findViewById(R.id.shopCode);
            textView_shop_number = itemView.findViewById(R.id.shopNumber);
            textView_address = itemView.findViewById(R.id.address);
            textView_mobileno=itemView.findViewById(R.id.mobilenumber);
            direction=itemView.findViewById(R.id.getDirection);
            directionsLayout = itemView.findViewById(R.id.linearLayout5);
        }
    }
}
