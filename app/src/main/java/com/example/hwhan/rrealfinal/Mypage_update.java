package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mypage_update extends AppCompatActivity {

    private Button backwardBtn;
    private Button updateBtn;
    private TextView current, update;

    private TextView updateemail;
    private TextView updatenumber;
    private ResultModel_userinfo_ITEM resultModel_userinfo_item;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.mypage_update);
        super.onCreate(savedInstanceState);
        backwardBtn = findViewById(R.id.backwardBtn);
        updateBtn = findViewById(R.id.updatebtn);
        updateemail = findViewById(R.id.updateemail);
        updatenumber = findViewById(R.id.updatenumber);
        current = findViewById(R.id.currentpassword);
        update = findViewById(R.id.updatepassword);


        Intent intent = getIntent();
        resultModel_userinfo_item = (ResultModel_userinfo_ITEM) intent.getSerializableExtra("UserInfo");
        updateemail.setText(resultModel_userinfo_item.getEmail());
        updatenumber.setText(resultModel_userinfo_item.getNumber());


        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(resultModel_userinfo_item.getPassword().equals(current.getText().toString())){

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitService.URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                    retrofitService.login_update(resultModel_userinfo_item.getId(), update.getText().toString(),  updateemail.getText().toString(), updatenumber.getText().toString()).enqueue(new Callback<ResultModel>() {
                        @Override
                        public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {

                            finish();
                        }
                        @Override
                        public void onFailure(Call<ResultModel> call, Throwable t) {
                        }
                    });

                }else{
                    Toast.makeText(Mypage_update.this, "비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }











            }
        });

        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
