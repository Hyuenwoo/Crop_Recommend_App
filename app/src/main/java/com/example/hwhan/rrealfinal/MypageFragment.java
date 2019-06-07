package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageFragment extends Fragment {

    private String ID;
    RetrofitService retrofitService;
    ResultModel_userinfo_ITEM resultModel_userinfo_item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment, container, false);

        final Bundle bundle = this.getArguments();
        final TextView idview = view.findViewById(R.id.id);
        final TextView emailview = view.findViewById(R.id.email);
        TextView updateinfo = view.findViewById(R.id.updateinfo);
        TextView favlocate = view.findViewById(R.id.favlocate);
        TextView favcrop = view.findViewById(R.id.favcrop);

        ID = bundle.getString("ID");
        idview.setText(ID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getuserInfo(ID).enqueue(new Callback<ResultModel_userinfo>() {

            @Override
            public void onResponse(Call<ResultModel_userinfo> call, Response<ResultModel_userinfo> response) {
                ResultModel_userinfo result = response.body();
                resultModel_userinfo_item = result.getResult().get(0);
                emailview.setText(resultModel_userinfo_item.getEmail());
            }
            @Override
            public void onFailure(Call<ResultModel_userinfo> call, Throwable t) {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_LONG).show();
            }
        });




        favlocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_favlocate.class);

                startActivity(intent);
            }
        });
        favcrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_favcrop.class);
                startActivity(intent);
            }
        });
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.hwhan.rrealfinal.Mypage_update.class);
                intent.putExtra("UserInfo", resultModel_userinfo_item);
                startActivity(intent);
            }
        });






        return view;
    }
}
