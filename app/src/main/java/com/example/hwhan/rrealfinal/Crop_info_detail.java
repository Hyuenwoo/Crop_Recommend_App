package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.widget.Button;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Crop_info_detail extends AppCompatActivity {
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




       android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.crop_info_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
       adapter = new Crop_info_detail_Adapter();
      recyclerView.setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);

            retrofitService.getcropInfo(name).enqueue(new Callback<ResultModel_CropData>() {

                @Override
                public void onResponse(Call<ResultModel_CropData> call, Response<ResultModel_CropData> response) {

                    ResultModel_CropData result = response.body();
                    Crop_info_detail_Data data = new Crop_info_detail_Data();
                    Crop_info_detail_Data data2 = new Crop_info_detail_Data();
                    Crop_info_detail_Data data3 = new Crop_info_detail_Data();
                    Crop_info_detail_Data data4 = new Crop_info_detail_Data();
                    Crop_info_detail_Data data5 = new Crop_info_detail_Data();


    textView2.setText(result.getResult().get(0).getNAME2());
    if(result.getResult().get(0).getSUBTITLE1()!=""){
        data.setSubtitle(result.getResult().get(0).getSUBTITLE1());
        data.setSubContent(result.getResult().get(0).getSUBCONTENT1().replace("<br>", "\r\n"));
        adapter.addItem(data);

        if(result.getResult().get(0).getSUBTITLE2()!=""){
            data2.setSubtitle(result.getResult().get(0).getSUBTITLE2());
            data2.setSubContent(result.getResult().get(0).getSUBCONTENT2().replace("<br>", "\r\n"));
            adapter.addItem(data2);

            if(result.getResult().get(0).getSUBTITLE3()!=""){
                data3.setSubtitle(result.getResult().get(0).getSUBTITLE3());
                data3.setSubContent(result.getResult().get(0).getSUBCONTENT3().replace("<br>", "\r\n"));
                adapter.addItem(data3);

                if(result.getResult().get(0).getSUBTITLE4()!=""){
                    data4.setSubtitle(result.getResult().get(0).getSUBTITLE4());
                    data4.setSubContent(result.getResult().get(0).getSUBCONTENT4().replace("<br>", "\r\n"));
                    adapter.addItem(data4);

                    if(result.getResult().get(0).getSUBTITLE5()!="") {
                        data5.setSubtitle(result.getResult().get(0).getSUBTITLE5());
                        data5.setSubContent(result.getResult().get(0).getSUBCONTENT5().replace("<br>", "\r\n"));
                        adapter.addItem(data5);
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}


                @Override
                public void onFailure(Call<ResultModel_CropData> call, Throwable t) {

                }
            });






//            dropdownBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    textView2.setVisibility(View.VISIBLE);
//                }
//            });




    }


}
