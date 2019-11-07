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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommendFragment extends Fragment implements MainActivity.OnBackPressedListener {
    private TextView test;
    TextView reco1,reco2,reco3;

    TextView sum1,sum2,sum3, sum4, sum5;

    RetrofitService retrofitService;
    String[] locate_reco;

    CropInfoFragment cropInfoFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.recommend_fragment, container, false);

        super.onCreate(savedInstanceState);
        test = view.findViewById(R.id.test);
        reco1 = view.findViewById(R.id.reco1);
        reco2 = view.findViewById(R.id.reco2);
        reco3 = view.findViewById(R.id.reco3);

        sum1 = view.findViewById(R.id.sum1);
        sum2 = view.findViewById(R.id.sum2);
        sum3 = view.findViewById(R.id.sum3);
        sum4 = view.findViewById(R.id.sum4);
        sum5 = view.findViewById(R.id.sum5);



        final Bundle bundle = this.getArguments();
        final String locate = bundle.getString("locate");
        test.setText(locate);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getlocatereco(locate).enqueue(new Callback<ResultModel_LocateReco>() {

            @Override
            public void onResponse(Call<ResultModel_LocateReco> call, Response<ResultModel_LocateReco> response) {

                ResultModel_LocateReco result = response.body();

                locate_reco = new String[8];
                locate_reco[0] = result.getResult().get(0);
                locate_reco[1] = result.getResult().get(1);
                locate_reco[2] = result.getResult().get(2);

                locate_reco[3] = result.getResult().get(3);

                locate_reco[4] = result.getResult().get(4);
                locate_reco[5] = result.getResult().get(5);
               locate_reco[6] = result.getResult().get(6);
               locate_reco[7] = result.getResult().get(7);


                reco1.setText(locate_reco[0]);
                reco2.setText(locate_reco[1]);
                reco3.setText(locate_reco[2]);

                sum1.setText(locate_reco[3]);

                sum2.setText(locate_reco[4]);
                sum3.setText(locate_reco[5]);
                sum4.setText(locate_reco[6]);
                sum5.setText(locate_reco[7]);


            }
            @Override
            public void onFailure(Call<ResultModel_LocateReco> call, Throwable t) {
                test.setText("미지원지역");
            }
        });


        reco1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cropInfoFragment = new CropInfoFragment();

                Bundle bundle = new Bundle();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, cropInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                bundle.putString("cropname",locate_reco[0]);
                cropInfoFragment.setArguments(bundle);

            }
        });

        reco2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cropInfoFragment = new CropInfoFragment();

                Bundle bundle = new Bundle();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, cropInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                bundle.putString("cropname",locate_reco[1]);
                cropInfoFragment.setArguments(bundle);

            }
        });

        reco3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cropInfoFragment = new CropInfoFragment();

                Bundle bundle = new Bundle();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, cropInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                bundle.putString("cropname",locate_reco[2]);
                cropInfoFragment.setArguments(bundle);

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
