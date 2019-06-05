package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mypage_update extends AppCompatActivity {

    private Button backwardBtn;
    private Button updateBtn;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.mypage_update);
        super.onCreate(savedInstanceState);
        backwardBtn = findViewById(R.id.backwardBtn);
        updateBtn = findViewById(R.id.updatebtn);

        updateBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(Mypage_update.this, "현재 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
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
