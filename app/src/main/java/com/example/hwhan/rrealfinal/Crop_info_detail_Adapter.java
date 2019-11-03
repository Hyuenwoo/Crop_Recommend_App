package com.example.hwhan.rrealfinal;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Crop_info_detail_Adapter extends RecyclerView.Adapter <Crop_info_detail_Adapter.ItemViewHolder>{
    private ArrayList<Crop_info_detail_Data> listData = new ArrayList<>();

    public ArrayList<Crop_info_detail_Data> getListData() {
        return listData;
    }

    @NonNull
    @Override
    public Crop_info_detail_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_info_detail_item, parent, false);
        return new Crop_info_detail_Adapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Crop_info_detail_Adapter.ItemViewHolder holder, int position) {



        holder.dropdownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.subcontent.getVisibility()==View.VISIBLE){
                    holder.subcontent.setVisibility(View.GONE);
                    holder.dropdownBtn.setBackgroundResource(R.drawable.dropdown);
                }else{
                    holder.subcontent.setVisibility(View.VISIBLE);
                    holder.dropdownBtn.setBackgroundResource(R.drawable.dropup);
                }


            }
        });

        holder.subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.subcontent.getVisibility()==View.VISIBLE){
                    holder.subcontent.setVisibility(View.GONE);
                    holder.dropdownBtn.setBackgroundResource(R.drawable.dropdown);
                }else{
                    holder.subcontent.setVisibility(View.VISIBLE);
                    holder.dropdownBtn.setBackgroundResource(R.drawable.dropup);
                }

            }
        });
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
    class ItemViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private TextView subtitle;
        private TextView subcontent;
        private ImageButton dropdownBtn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            subtitle = itemView.findViewById(R.id.subtitle);
            subcontent = itemView.findViewById(R.id.subcontent);
            dropdownBtn = itemView.findViewById(R.id.openBtn);
        }

        public void onBind(Crop_info_detail_Data data) {

            subtitle.setText(data.getSubtitle());
            subcontent.setText(data.getSubContent());
        }
    }
}
