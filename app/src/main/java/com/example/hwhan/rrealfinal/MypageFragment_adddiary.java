package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageFragment_adddiary extends Fragment implements MainActivity.OnBackPressedListener {

    RetrofitService retrofitService;
    private FloatingActionButton diaryadd;
    private TextView add_Title;
    private TextView add_Content;
    private TextView add_Date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment_diary_addcontent, container, false);
        super.onCreate(savedInstanceState);
        Date currentTime = Calendar.getInstance().getTime();
        final Bundle bundle = this.getArguments();
        final String data_text = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentTime);


        add_Date = view.findViewById(R.id.diary_add_date);
        add_Title = view.findViewById(R.id.diary_add_title);
        add_Content = view.findViewById(R.id.diary_add_content);
        diaryadd = view.findViewById(R.id.diary_enroll2);
        add_Date.setText(data_text);

        diaryadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id, datee, title, content;
                id = bundle.getString("ID");
                datee = add_Date.getText().toString();
                title = add_Title.getText().toString();
                content = add_Content.getText().toString();
                //DB통신부
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                java.sql.Date date =java.sql.Date.valueOf(datee);
                retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.add_diary(id, date, title, content).enqueue(new Callback<ResultModel>() {
                    @Override
                    public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                        ResultModel result = response.body();
                            Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                            onBack();
                        }
                    @Override
                    public void onFailure(Call<ResultModel> call, Throwable t) {
                    }
                });
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
