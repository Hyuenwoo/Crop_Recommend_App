package com.example.hwhan.rrealfinal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Crop_list_Adapter extends RecyclerView.Adapter <Crop_list_Adapter.ItemViewHolder> {
    private ArrayList<Crop_info_detail_Data> listData = new ArrayList<>();
    public ArrayList<Crop_info_detail_Data> getListData() {
        return listData;
    }

    @NonNull
    @Override
    public Crop_list_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_info_item, parent, false);
        return new Crop_list_Adapter.ItemViewHolder(view);
    }

    @Override

                public void onBindViewHolder(@NonNull Crop_list_Adapter.ItemViewHolder holder, int position) {

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
        private TextView name;
        private TextView category;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.crop_text);
            category = itemView.findViewById(R.id.crop_text2);
        }

        public void onBind(Crop_info_detail_Data data) {

            name.setText(data.getSubtitle());
            category.setText(data.getSubContent());
        }
    }
}
