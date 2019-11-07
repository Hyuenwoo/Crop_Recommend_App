package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageFragment_diary extends Fragment implements MainActivity.OnBackPressedListener {

    private FloatingActionButton enroll;
    diary_list_Adapter diary_list_adapter;
    MypageFragment_adddiary mypageFragment_adddiary;
    MypageFragment_diary_content mypageFragment_diary_content;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment_diary, container, false);
        super.onCreate(savedInstanceState);
        final Bundle bundle = this.getArguments();
        enroll = view.findViewById(R.id.diary_enroll);
        final GestureDetector gestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }
        });

        mypageFragment_diary_content = new MypageFragment_diary_content();
        mypageFragment_adddiary = new MypageFragment_adddiary();
        android.support.v7.widget.RecyclerView diaryView = view.findViewById(R.id.diary_Recycler);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(diaryView.getContext(), LinearLayoutManager.VERTICAL);
        diaryView.addItemDecoration(dividerItemDecoration);
        diaryView.setHasFixedSize(true);
        diaryView.setLayoutManager(new LinearLayoutManager(getContext()));
        diaryView.setItemAnimator(new DefaultItemAnimator());
        diary_list_adapter = new diary_list_Adapter();
        diaryView.setAdapter(diary_list_adapter);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, mypageFragment_adddiary);
                transaction.addToBackStack(null);
                transaction.commit();
                mypageFragment_adddiary.setArguments(bundle);
            }
        });

        diaryView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);
                if(child!=null&&gestureDetector.onTouchEvent(e)){
                    FragmentManager fragmentManager = getFragmentManager();
                    String title= diary_list_Adapter.getListData().get(position).getSubtitle();
                    String content=diary_list_Adapter.getListData().get(position).getSubContent();
                    String date=diary_list_Adapter.getListData().get(position).getDate();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    Bundle bundle = new Bundle();
                    transaction.replace(R.id.frame_layout, mypageFragment_diary_content);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    Log.e("TCD_TEST:", title+content+date);
                    bundle.putString("title", title);
                    bundle.putString("content", content);
                    bundle.putString("date", date);
                    mypageFragment_diary_content.setArguments(bundle);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        diarySet(bundle.getString("ID"));
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

    private  void diarySet(String ID){
        diary_list_adapter.getListData().clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getdiary(ID).enqueue(new Callback<ResultModel_diary>() {
            @Override
            public void onResponse(Call<ResultModel_diary> call, Response<ResultModel_diary> response) {
                ResultModel_diary result = response.body();
                for(int i=0; i<result.getResult().size(); i++){
                    Crop_info_detail_Data data = new Crop_info_detail_Data();
                    data.setSubtitle(result.getResult().get(i).getTitle());
                    data.setSubContent(result.getResult().get(i).getContent());
                    data.setDate(result.getResult().get(i).getDate());
                    diary_list_adapter.addItem(data);
                }
                diary_list_adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ResultModel_diary> call, Throwable t) {

            }
        });

    }

}
