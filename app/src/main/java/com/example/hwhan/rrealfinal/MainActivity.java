package com.example.hwhan.rrealfinal;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {






    final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 0; //위치권환용 final 상수

    private NavigationView navigationView;

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();

    // 4개의 메뉴에 들어갈 Fragment들
    private MapFragment mapFragment = new MapFragment();
    private HomeFragment homeFragment = new HomeFragment();
    private InfoFragment infoFragment = new InfoFragment();
    private MypageFragment mypageFragment = new MypageFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //바텀네비게이션뷰
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction.replace(R.id.frame_layout, mapFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frame_layout, infoFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu4: {
                        transaction.replace(R.id.frame_layout, mypageFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });


//위치 권한 부여
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);

    }


}
