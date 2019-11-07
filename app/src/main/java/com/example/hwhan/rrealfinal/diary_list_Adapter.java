package com.example.hwhan.rrealfinal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class diary_list_Adapter extends RecyclerView.Adapter <diary_list_Adapter.ItemViewHolder> {
    private static ArrayList<Crop_info_detail_Data> listData = new ArrayList<>();
    public static ArrayList<Crop_info_detail_Data> getListData() {
        return listData;
    }
    @NonNull
    @Override
    public diary_list_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypage_fragment_diary_item, parent, false);
        return new diary_list_Adapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diary_list_Adapter.ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(Crop_info_detail_Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView TITLE;
        private TextView CONTENT;
        private TextView DATE;

        public ItemViewHolder(View itemView) {
            super(itemView);
            TITLE = itemView.findViewById(R.id.diary_title);
            CONTENT = itemView.findViewById(R.id.diary_content);
            DATE = itemView.findViewById(R.id.diary_date);
        }

        public void onBind(Crop_info_detail_Data data) {
            DATE.setText(data.getDate());
            TITLE.setText(data.getSubtitle());
            CONTENT.setText(data.getSubContent());
        }
    }

}
