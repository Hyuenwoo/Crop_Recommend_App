package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class Recommend_view extends AppCompatActivity {
    private Button backwardBtn;
        private  Recommend_view_Adapter adapter; // 리사이클러뷰 어댑터 선언

        @Override
        protected void onCreate(Bundle saveInstanceState){
            backwardBtn = findViewById(R.id.navigationBtn);
            super.onCreate(saveInstanceState);
            setContentView(R.layout.recommend_view);
            android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.recommend_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            adapter = new Recommend_view_Adapter();
            recyclerView.setAdapter(adapter);
            getdata();

            backwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        }

    private void getdata() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("감자", "옥수수", "배추", "상추", "귤", "사과", "복숭아");
        List<Integer> listResId = Arrays.asList(
                R.drawable.potato,
                R.drawable.corn,
                R.drawable.bechu,
                R.drawable.bechu,
                R.drawable.citrus,
                R.drawable.apple,
                R.drawable.peach
        );
        List<Integer> listRank = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Recommend_Data data = new Recommend_Data();
            data.setTitle(listTitle.get(i));
            data.setImageID(listResId.get(i));
            data.setRank(listRank.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}
