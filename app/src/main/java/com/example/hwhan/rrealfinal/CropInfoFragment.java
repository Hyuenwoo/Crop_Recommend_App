package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CropInfoFragment extends Fragment implements MainActivity.OnBackPressedListener {

    private TextView show_cropname;
    private TextView add_favcrop;
    private ImageView cropinfo_image;
    private TextView cropinfo_explain;
    String cropname;

    RetrofitService retrofitService;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.cropinfo_fragment, container, false);

        super.onCreate(savedInstanceState);

        show_cropname = view.findViewById(R.id.cropname);
        add_favcrop = view.findViewById(R.id.add_favcrop);

        cropinfo_image = view.findViewById(R.id.cropinfo_image);
        cropinfo_image = view.findViewById(R.id.cropinfo_explain);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.cropinfo_fragment(cropname).enqueue(new Callback<ResultModel>() {

            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {

                ResultModel result = response.body();




            }


            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

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
