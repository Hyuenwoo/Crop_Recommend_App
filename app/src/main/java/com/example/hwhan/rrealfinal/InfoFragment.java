package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class InfoFragment extends Fragment {
    private  Crop_horizontal_scroll_Adapter adapter;
    private  Crop_list_Adapter adapter2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);


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





        recyclerView.addOnItemTouchListener(new android.support.v7.widget.RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(android.support.v7.widget.RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);


                if(e.getAction() ==  MotionEvent.ACTION_UP){
                    Log.i("MotionEvent : ", "interrupt");
                    switch (position){
                        case 0:
                            adapter2.getListData().clear();
                            adapter2.notifyDataSetChanged();
                            getdata2();
                            break;
                        case 1:
                            adapter2.getListData().clear();
                            adapter2.notifyDataSetChanged();
                            getdata3();
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
        return view;

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
    private void getdata2() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("사과", "배", "포도", "감귤", "참다래", "매실", "유자", "단감", "복숭아", "복분자");
        List<Integer> listResId = Arrays.asList(
                R.drawable.apple,
                R.drawable.bae,
                R.drawable.grape,
                R.drawable.citrus,
                R.drawable.kwee,
                R.drawable.maesil,
                R.drawable.uja,
                R.drawable.dangam,
                R.drawable.peach,
                R.drawable.bokbunja
        );

        for (int i = 0; i < listTitle.size(); i++) {
            Crop_horizontal_scroll_Data data = new Crop_horizontal_scroll_Data();
            data.setTitle(listTitle.get(i));
            data.setImage(listResId.get(i));

            adapter2.addItem(data);

        }

        adapter2.notifyDataSetChanged();
    }
    private void getdata3() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("수박", "참외", "딸기", "오이", "토마토", "메론", "호박", "가지", "고추", "피망");
        List<Integer> listResId = Arrays.asList(
                R.drawable.watermelon,
                R.drawable.chamwae,
                R.drawable.ddalgi,
                R.drawable.oe,
                R.drawable.tomato,
                R.drawable.melon,
                R.drawable.pumpkin,
                R.drawable.gaji,
                R.drawable.pepper,
                R.drawable.pimang
        );

        for (int i = 0; i < listTitle.size(); i++) {
            Crop_horizontal_scroll_Data data = new Crop_horizontal_scroll_Data();
            data.setTitle(listTitle.get(i));
            data.setImage(listResId.get(i));

            adapter2.addItem(data);
        }
        adapter2.notifyDataSetChanged();
    }
}