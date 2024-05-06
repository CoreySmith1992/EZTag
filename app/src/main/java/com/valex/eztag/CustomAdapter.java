package com.valex.eztag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList id, asset, make, model, current_user, serial;

    CustomAdapter(Context context,
                  Activity activity,
                  ArrayList id,
                  ArrayList asset,
                  ArrayList make,
                  ArrayList model,
                  ArrayList current_user,
                  ArrayList serial){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.asset = asset;
        this.make = make;
        this.model = model;
        this.current_user = current_user;
        this.serial = serial;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position){

        holder.asset_id_txt.setText(String.valueOf(asset.get(position)));
        holder.make_txt.setText(String.valueOf(make.get(position)));
        holder.model_txt.setText(String.valueOf(model.get(position)));
        holder.current_user_txt.setText(String.valueOf(current_user.get(position)));
        holder.serial_txt.setText(String.valueOf(serial.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("asset", String.valueOf(asset.get(position)));
                intent.putExtra("make", String.valueOf(make.get(position)));
                intent.putExtra("model", String.valueOf(model.get(position)));
                intent.putExtra("current_user", String.valueOf(current_user.get(position)));
                intent.putExtra("serial", String.valueOf(serial.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount(){
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView asset_id_txt, make_txt, model_txt, current_user_txt, serial_txt;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            asset_id_txt = itemView.findViewById(R.id.asset_id_txt);
            make_txt = itemView.findViewById(R.id.make_txt);
            model_txt = itemView.findViewById(R.id.model_txt);
            current_user_txt = itemView.findViewById(R.id.current_user_txt);
            serial_txt = itemView.findViewById(R.id.serial_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }



}
