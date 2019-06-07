package com.example.hwhan.rrealfinal;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recommend_crop extends AppCompatActivity {

    ViewPager viewPager;
    Recommend_crop_adapter adapter;
    List<Recommend_crop_model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    String locate;
    String recommend[];
    RetrofitService retrofitService;

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.recommend_crop);

            Intent intent = getIntent();
            locate = intent.getExtras().getString("locate");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getrecoinfo(locate).enqueue(new Callback<ResultModel_RecoInfo>() {
            @Override
            public void onResponse(Call<ResultModel_RecoInfo> call, Response<ResultModel_RecoInfo> response) {
                ResultModel_RecoInfo result = response.body();
                recommend[0] = result.getResult().get(0);
                recommend[1] = result.getResult().get(1);
                recommend[2] = result.getResult().get(2);
                recommend[3] = result.getResult().get(3); //작물
                recommend[4] = result.getResult().get(4);
                recommend[5] = result.getResult().get(5);
                recommend[6] = result.getResult().get(6);
                recommend[7] = result.getResult().get(7); // 이미지 url

            }

            @Override
            public void onFailure(Call<ResultModel_RecoInfo> call, Throwable t) {

            }
        });

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)


        };

        colors =  colors_temp;

        models = new ArrayList<>();
        models.add(new Recommend_crop_model(recommend[4],recommend[0],"별이 5개!",locate));
        models.add(new Recommend_crop_model(recommend[5],recommend[1],"별이 5개!",locate));
        models.add(new Recommend_crop_model(recommend[6],recommend[2],"별이 5개!",locate));
        models.add(new Recommend_crop_model(recommend[7],recommend[3],"별이 5개!",locate));

        adapter = new Recommend_crop_adapter(models,this);

        viewPager = findViewById(R.id.cropViewpager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    v,
                                    colors[position],
                                    colors[position + 1])
                    );
                }else {
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
