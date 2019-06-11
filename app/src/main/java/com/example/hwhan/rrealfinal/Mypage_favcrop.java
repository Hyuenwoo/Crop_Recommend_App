package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Mypage_favcrop extends AppCompatActivity {
    private Button backwardBtn;
    private TextView favcrop1;
    private RelativeLayout favcontainer;
    protected void onCreate(@Nullable Bundle savedInstanceState){
        setContentView(R.layout.mypage_favcrop);
        super.onCreate(savedInstanceState);
        backwardBtn = findViewById(R.id.backwardBtn);
        favcrop1 = findViewById(R.id.cropID);
        favcontainer = findViewById(R.id.favccontain);
        favcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Crop_info_detail.class);
                intent.putExtra("Cropname", favcrop1.getText());
                startActivity(intent);
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
