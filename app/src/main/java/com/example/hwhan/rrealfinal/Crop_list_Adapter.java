package com.example.hwhan.rrealfinal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Crop_list_Adapter extends RecyclerView.Adapter <Crop_list_Adapter.ItemViewHolder> {
    private ArrayList<Crop_horizontal_scroll_Data> listData = new ArrayList<>();

    public ArrayList<Crop_horizontal_scroll_Data> getListData() {
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

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(Crop_horizontal_scroll_Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.crop_image);
            name = itemView.findViewById(R.id.crop_text);
        }

        public void onBind(Crop_horizontal_scroll_Data data) {

            name.setText(data.getTitle());
            image.setImageResource(data.getImage());
        }
    }
}
