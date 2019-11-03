package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageFragment_update extends Fragment implements MainActivity.OnBackPressedListener {

    private Button backwardBtn;
    private Button updateBtn;
    private TextView current, update;

    private TextView updateemail;
    private TextView updatenumber;
    private ResultModel_userinfo_ITEM resultModel_userinfo_item;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_update, container, false);

        backwardBtn = view.findViewById(R.id.backwardBtn);
        updateBtn = view.findViewById(R.id.updatebtn);
        updateemail = view.findViewById(R.id.updateemail);
        updatenumber = view.findViewById(R.id.updatenumber);
        current = view.findViewById(R.id.currentpassword);
        update = view.findViewById(R.id.updatepassword);


        Bundle bundle = this.getArguments();

        resultModel_userinfo_item = (ResultModel_userinfo_ITEM) bundle.getSerializable("UserInfo");
        updateemail.setText(resultModel_userinfo_item.getEmail());
        updatenumber.setText(resultModel_userinfo_item.getNumber());


        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(resultModel_userinfo_item.getPassword().equals(current.getText().toString())){

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitService.URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                    retrofitService.login_update(resultModel_userinfo_item.getId(), update.getText().toString(),  updateemail.getText().toString(), updatenumber.getText().toString()).enqueue(new Callback<ResultModel>() {
                        @Override
                        public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                            onBack();
                            Toast.makeText(getContext(), "변경되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<ResultModel> call, Throwable t) {
                        }
                    });

                }else{
                    Toast.makeText(getContext(), "비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }











            }
        });

        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });


        return view;
    }


    @Override
    public void onBack() {
        MainActivity activity = (MainActivity)getActivity();
        activity.setOnBackPressedListener(null);
        getActivity().getSupportFragmentManager().popBackStack();
    }
    @Override
    public void onAttach(Activity context){
        super.onAttach(context);
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}
