package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Crop_info_detail extends AppCompatActivity {
    String jsonString;
    TextView textView1;
    TextView textView2;
    Crop_info_detail_Adapter adapter;
    RetrofitService retrofitService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.crop_info_detail);
        Intent intent = getIntent();
        textView1 = findViewById(R.id.crop_detail_name);
        textView2 = findViewById(R.id.crop_detail_sname);
        String name= intent.getExtras().getString("Cropname");
        textView1.setText(name);


        Log.i("nametag : ", name);

//        android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.recommend_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        adapter = new Crop_info_detail_Adapter();
//        recyclerView.setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);

            retrofitService.getcropInfo(name).enqueue(new Callback<ResultModel_CropData>() {

                @Override
                public void onResponse(Call<ResultModel_CropData> call, Response<ResultModel_CropData> response) {


                    ResultModel_CropData result = response.body();
//                    textView1.setText( result.getResult().get(0).getSUBTITLE1());
                    textView2.setText(result.getResult().get(0).getNAME2());
                    Log.i("resultModel:", result.getResult().get(0).getSUBCONTENT1());
                    Log.i("resultModel:", result.getResult().get(0).getSUBCONTENT2());
                    Log.i("resultModel:", result.getResult().get(0).getSUBCONTENT3());

                    Log.i("resultModel:", result.getResult().get(0).getSUBTITLE1());
                    Log.i("resultModel:", result.getResult().get(0).getSUBTITLE2());
                    Log.i("resultModel:", result.getResult().get(0).getSUBTITLE3());




                }

                @Override
                public void onFailure(Call<ResultModel_CropData> call, Throwable t) {

                }
            });





    }


}
