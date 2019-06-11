package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mypage_favlocate extends AppCompatActivity {
    private Button backwardBtn;
    private TextView favloc1;
    protected void onCreate(@Nullable Bundle savedInstanceState){
        setContentView(R.layout.mypage_favlocate);
        super.onCreate(savedInstanceState);
        backwardBtn = findViewById(R.id.backwardBtn);
        favloc1 = findViewById(R.id.localID);



        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

}
