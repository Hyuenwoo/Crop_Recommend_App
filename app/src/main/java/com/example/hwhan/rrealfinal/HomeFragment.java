package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.DocumentType;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ariticle1= view.findViewById(R.id.showtext1);
        ariticle2= view.findViewById(R.id.showtext2);
        ariticle3= view.findViewById(R.id.showtext3);
        ariticle4= view.findViewById(R.id.showtext4);

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

        return view;
    }
}
