package com.example.hwhan.rrealfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.DocumentType;

import java.util.ArrayList;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    Button ariticle1;
    Button ariticle2;
    Button ariticle3;
    Button ariticle4;
    Document document;
    RetrofitService retrofitService;
    String url_piece_collect, test;
    String url1, url2, url3, url4;
    String title ;

    ImageButton weekInfo;

    FlipAdapter flipadapter;
    AutoScrollViewPager autoViewPager;
    TextView viewtext;

    HomeFragment_WeekInfo homeFragment_weekInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        homeFragment_weekInfo = new HomeFragment_WeekInfo();

        ariticle1= view.findViewById(R.id.showtext1);
        ariticle2= view.findViewById(R.id.showtext2);
        ariticle3= view.findViewById(R.id.showtext3);
        ariticle4= view.findViewById(R.id.showtext4);
        weekInfo = view.findViewById(R.id.weekinfo);

        title = "test";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.gethomeinfo(title).enqueue(new Callback<ResultModel_HomeInfo>() {
            @Override
            public void onResponse(Call<ResultModel_HomeInfo> call, Response<ResultModel_HomeInfo> response) {
                ResultModel_HomeInfo result = response.body();
                ariticle1.setText(result.getResult().get(0));
                ariticle2.setText(result.getResult().get(1));
                ariticle3.setText(result.getResult().get(2));
                ariticle4.setText(result.getResult().get(3));
                url_piece_collect = result.getResult().get(4);
//                Toast.makeText(getContext(),url_piece_collect,Toast.LENGTH_LONG).show();
                test = url_piece_collect;

            }

            @Override
            public void onFailure(Call<ResultModel_HomeInfo> call, Throwable t) {

            }
        });

        if(test!=null) {
            url1 = test.substring(0, 3);
            url2 = test.substring(4, 7);
            url3 = test.substring(8, 11);
            url4 = test.substring(12, 15);

            url1 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url1 + "bbsDetail.do";
            url2 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url2 + "bbsDetail.do";
            url3 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url3 + "bbsDetail.do";
            url4 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url4 + "bbsDetail.do";

            ariticle1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                    startActivity(intent);
                }
            });

            ariticle2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                    startActivity(intent);
                }
            });

            ariticle3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                    startActivity(intent);
                }
            });

            ariticle4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url4));
                    startActivity(intent);
                }
            });
        }

        //버튼
        weekInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_layout, homeFragment_weekInfo);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        //오토뷰페이저

        final ArrayList<Integer> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        viewtext = view.findViewById(R.id.viewtext);
        autoViewPager = view.findViewById(R.id.autoViewPager);

        data.add(R.drawable.nongsa_img10);
        data.add(R.drawable.jin);
        data.add(R.drawable.re_agri4);

//        data.add(R.drawable.nongsa_img6);
//        data.add(R.drawable.re_agri3);
//        data.add(R.drawable.jin);
        Context context = view.getContext();
        flipadapter = new FlipAdapter(context, data);
        autoViewPager.setAdapter(flipadapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(4000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작
        autoViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position < data.size()) {
                    autoViewPager.setCurrentItem(position + data.size(), false);
                } else if (position >= data.size() * 2) {
                    autoViewPager.setCurrentItem(position - data.size(), false);
                }
                position = position % data.size();
                if (position == 0) {  // 첫 페이지
                    viewtext.setText("1/3");

                } else if (position == 1) {   //두번째 페이지
                    viewtext.setText("2/3");

                } else if (position == 2) {
                    viewtext.setText("3/3");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }
}
