package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_join extends AppCompatActivity {


    Button btn_join;
    EditText joinID,joinPW,joinEmail,joinNumber;
    String inputID, inputPW,inputEmail,inputNumber;

    RetrofitService retrofitService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_join);

        btn_join = (Button) findViewById(R.id.btn_join);
        joinID = (EditText) findViewById(R.id.joinId);
        joinPW = (EditText) findViewById(R.id.joinPassword);
        joinEmail = (EditText) findViewById(R.id.joinEmail);
        joinNumber = (EditText) findViewById(R.id.joinNumber);



        btn_join.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                inputID = joinID.getText().toString();
                inputPW = joinPW.getText().toString();
                inputEmail = joinEmail.getText().toString();
                inputNumber = joinNumber.getText().toString();

                if (inputID.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_LONG).show();
                } else if (inputPW.equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                } else if (inputEmail.equals("")) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_LONG).show();
                } else if (inputNumber.equals("")) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력하세요.", Toast.LENGTH_LONG).show();
                } else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitService.URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    retrofitService = retrofit.create(RetrofitService.class);
                    retrofitService.login_join(inputID, inputPW, inputEmail, inputNumber).enqueue(new Callback<ResultModel>() {
                        @Override
                        public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                            ResultModel result = response.body();
                            if (result.getResult().equals("checkID")) {
                                Toast.makeText(getApplicationContext(), "아이디가 존재합니다.", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Login_start.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultModel> call, Throwable t) {

                        }
                    });
                }
            }

        });

    }
}
