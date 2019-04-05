package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_start extends AppCompatActivity {

    Button btn_login;
    TextView join;
    EditText id;
    EditText password;
    String inputID, inputPW;

    RetrofitService retrofitService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_start);

        btn_login = (Button) findViewById(R.id.btn_login);
        join = (TextView) findViewById(R.id.join);
        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                inputID = id.getText().toString();
                inputPW = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.login_ok(inputID,inputPW).enqueue(new Callback<ResultModel>() {
                    @Override
                    public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                        ResultModel result = response.body();
                        if (result.getResult().equals("errorID")) {
                            Toast.makeText(getApplicationContext(), "아이디가 틀립니다.", Toast.LENGTH_LONG).show();

                        } else if(result.getResult().equals("errorPW")){
                            Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultModel> call, Throwable t) {

                    }
                });
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_join.class);
                startActivity(intent);
            }
        });
    }
}
