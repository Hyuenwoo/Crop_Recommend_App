package com.example.hwhan.rrealfinal;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Recommend_crop extends AppCompatActivity {

    ViewPager viewPager;
    Recommend_crop_adapter adapter;
    List<Recommend_crop_model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView textView;
    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.recommend_crop);

            textView = (TextView) findViewById(R.id.cropLocate);

            Integer[] colors_temp = {
                    getResources().getColor(R.color.color1),
                    getResources().getColor(R.color.color2),
                    getResources().getColor(R.color.color3),
                    getResources().getColor(R.color.color4)


        };
        colors =  colors_temp;

        models = new ArrayList<>();
        models.add(new Recommend_crop_model(R.drawable.apple,"사과","별이 5개!"));
        models.add(new Recommend_crop_model(R.drawable.peach,"복숭아","별이 5개!"));
        models.add(new Recommend_crop_model(R.drawable.corn,"옥수수","별이 5개!"));
        models.add(new Recommend_crop_model(R.drawable.potato,"감자","별이 5개!"));

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
