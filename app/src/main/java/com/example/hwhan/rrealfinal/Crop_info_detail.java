package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

public class Crop_info_detail extends AppCompatActivity {
    TextView textView1;
    Crop_info_detail_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_info_detail);
        Intent intent = getIntent();
        textView1 = findViewById(R.id.crop_detail_name);
        String name= intent.getExtras().getString("Cropname");
        textView1.setText(name);
        Log.i("nametag : ", name);
//        android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.recommend_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        adapter = new Crop_info_detail_Adapter();
//        recyclerView.setAdapter(adapter);


    }


}
