package com.example.hwhan.rrealfinal;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {


    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;




    final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 0; //위치권환용 final 상수

    private NavigationView navigationView;

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();

    // 4개의 메뉴에 들어갈 Fragment들
    private MapFragment mapFragment = new MapFragment();
    private HomeFragment homeFragment = new HomeFragment();
    private InfoFragment infoFragment = new InfoFragment();
    private MypageFragment mypageFragment = new MypageFragment();

    private  HomeFragment_WeekInfo homeFragment_weekInfo = new HomeFragment_WeekInfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");

        //바텀네비게이션뷰
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle bundle = new Bundle();
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
                        bundle.putString("ID", ID);
                        mypageFragment.setArguments(bundle);
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
    @Override
    public void onBackPressed(){
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }



    public void replaceFragment(int fragmentId){

        //화면에 보여지는 fragment를 추가하거나 바꿀 수 있는 객체를 만든다.

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        //다음으로 보여지는 fragment는 fragmentId로 설정한다.

        if( fragmentId == 1 ) {

            transaction.replace(R.id.container, homeFragment_weekInfo);

        }

        else if( fragmentId == 2 ) {

            transaction.replace(R.id.container, homeFragment_weekInfo);

        }

        else if( fragmentId == 3 ) {

            transaction.replace(R.id.container, homeFragment_weekInfo);

        }



        //Back 버튼 클릭 시 이전 프래그먼트로 이동시키도록 한다.

        transaction.addToBackStack(null);



        //fragment의 변경사항을 반영시킨다.

        transaction.commit();

    }



}
