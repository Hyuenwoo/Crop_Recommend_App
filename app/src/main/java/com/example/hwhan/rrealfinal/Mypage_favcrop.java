package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Mypage_favcrop extends AppCompatActivity {
    private Button backwardBtn;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        setContentView(R.layout.mypage_favcrop);
        super.onCreate(savedInstanceState);
        backwardBtn = findViewById(R.id.backwardBtn);
        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
