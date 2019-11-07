package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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


    RetrofitService retrofitService;
    ResultModel_userinfo_ITEM resultModel_userinfo_item;
    MypageFragment_favlocate mypageFragment_favlocate;
    MypageFragment_favcrop mypageFragment_favcrop;
    MypageFragment_update mypageFragment_update;
    MypageFragment_diary mypageFragment_diary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment, container, false);


        mypageFragment_update = new MypageFragment_update();
        mypageFragment_favcrop = new MypageFragment_favcrop();
        mypageFragment_favlocate = new MypageFragment_favlocate();
        mypageFragment_diary = new MypageFragment_diary();
        final Bundle bundle = this.getArguments();
        final TextView idview = view.findViewById(R.id.id);
        final TextView emailview = view.findViewById(R.id.email);
        TextView diary = view.findViewById(R.id.diary);
        TextView updateinfo = view.findViewById(R.id.updateinfo);
        TextView favlocate = view.findViewById(R.id.favlocate);
        TextView favcrop = view.findViewById(R.id.favcrop);

        final String ID = bundle.getString("ID");
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
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, mypageFragment_favlocate);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        favcrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, mypageFragment_favcrop);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, mypageFragment_update);
                transaction.addToBackStack(null);
                transaction.commit();
                bundle.putSerializable("UserInfo", resultModel_userinfo_item);
                mypageFragment_update.setArguments(bundle);
            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, mypageFragment_diary);
                transaction.addToBackStack(null);
                transaction.commit();
                bundle.putString("ID", ID);
                mypageFragment_diary.setArguments(bundle);;

            }
        });




        return view;
    }

//    @Override
//    public void onBack() {
//        MainActivity activity = (MainActivity)getActivity();
//        activity.setOnBackPressedListener(null);
//        activity.onBackPressed();
//    }
//    @Override
//    public void onAttach(Activity context){
//        super.onAttach(context);
//        ((MainActivity)context).setOnBackPressedListener(this);
//    }
}
