package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoFragment extends Fragment {
    private  Crop_horizontal_scroll_Adapter adapter;
    private  Crop_list_Adapter adapter2;
    private Button searchbtn;
    private TextView searchcontext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.info_fragment, container, false);
        searchbtn = view.findViewById(R.id.SearchBtn);
        searchcontext = view.findViewById(R.id.editTextQuery);

        //상단 스크롤
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        android.support.v7.widget.RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Crop_horizontal_scroll_Adapter(getContext());
        recyclerView.setAdapter(adapter);
        getdata();

        //작물 스크롤
        android.support.v7.widget.RecyclerView cropView = view.findViewById(R.id.CropRecyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(cropView.getContext(),LinearLayoutManager.VERTICAL);
        cropView.addItemDecoration(dividerItemDecoration);
        cropView.setHasFixedSize(true);
        cropView.setLayoutManager(new LinearLayoutManager(getContext()));
        cropView.setItemAnimator(new DefaultItemAnimator());
        adapter2 = new Crop_list_Adapter();
        cropView.setAdapter(adapter2);
        CategorySet("과수류");
        final GestureDetector gestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }
        });


        recyclerView.addOnItemTouchListener(new android.support.v7.widget.RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(android.support.v7.widget.RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);

                if(child!=null&&gestureDetector.onTouchEvent(e)){
                    Log.i("MotionEvent : ", "interrupt");
                    switch (position){
                        case 0:
                            CategorySet("과수류");
                            break;
                        case 1:
                            CategorySet("과채류");
                            break;
                        case 2:
                            CategorySet("경엽채류");
                            break;
                        case 3:
                            CategorySet("근채류");
                            break;
                        case 4:
                            CategorySet("인경채류");
                            break;
                        case 5:
                            CategorySet("곡류");
                            break;
                        case 6:
                            CategorySet("약초류");
                            break;






                    }

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
        cropView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);

                if(child!=null&&gestureDetector.onTouchEvent(e)){
                    String name = adapter2.getListData().get(position).getSubtitle();
                    Intent intent = new Intent(view.getContext(),Crop_info_detail.class);
                    intent.putExtra("Cropname", name);
                    startActivity(intent);
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

        searchcontext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    SearchData(searchcontext.getText().toString());
                }
                return false;
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SearchData(searchcontext.getText().toString());
            }
        });
        return view;
    }



    private  void CategorySet(String Category){
        adapter2.getListData().clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getcropname(Category).enqueue(new Callback<ResultModel_CropData>() {
            @Override
            public void onResponse(Call<ResultModel_CropData> call, Response<ResultModel_CropData> response) {

                ResultModel_CropData result = response.body();


               for(int i=0; i<result.getResult().size(); i++){
                   Crop_info_detail_Data data = new Crop_info_detail_Data();
                   data.setSubtitle(result.getResult().get(i).getNAME());
                   data.setSubContent(result.getResult().get(i).getNAME2());
                   adapter2.addItem(data);

               }
                adapter2.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<ResultModel_CropData> call, Throwable t) {

            }
        });

    }

    private  void SearchData(String name){
        adapter2.getListData().clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getcropInfo(name).enqueue(new Callback<ResultModel_CropData>() {
            @Override
            public void onResponse(Call<ResultModel_CropData> call, Response<ResultModel_CropData> response) {

                ResultModel_CropData result = response.body();


                for(int i=0; i<result.getResult().size(); i++){
                    Crop_info_detail_Data data = new Crop_info_detail_Data();
                    data.setSubtitle(result.getResult().get(i).getNAME());
                    data.setSubContent(result.getResult().get(i).getNAME2());
                    adapter2.addItem(data);

                }
                if(result.getResult()==null){
                    Toast.makeText(getActivity(), "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                adapter2.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<ResultModel_CropData> call, Throwable t) {

            }
        });


    }
    private void getdata() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("과일류", "과채류", "경엽채류", "근채류", "인경채류", "곡류", "약초류");
        List<Integer> listResId = Arrays.asList(
                R.drawable.fruit,
                R.drawable.gwa,
                R.drawable.sam,
                R.drawable.gun,
                R.drawable.gun,
                R.drawable.gok,
                R.drawable.yak,
                R.drawable.peach
        );

        for (int i = 0; i < listTitle.size(); i++) {
            Crop_horizontal_scroll_Data data = new Crop_horizontal_scroll_Data();
            data.setTitle(listTitle.get(i));
            data.setImage(listResId.get(i));

            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();
    }


}