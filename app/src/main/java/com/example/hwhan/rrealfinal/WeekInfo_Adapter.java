package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class WeekInfo_Adapter extends RecyclerView.Adapter<WeekInfo_Adapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    public ArrayList<WeekInfo_Item> listData = new ArrayList<>();
    Context context;

    WeekInfo_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WeekInfo_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekinfo_item, parent, false);
        return new WeekInfo_Adapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        WeekInfo_Adapter.ItemViewHolder itemViewHolder = (WeekInfo_Adapter.ItemViewHolder) holder;
        itemViewHolder.subject.setText(listData.get(position).subject);
        itemViewHolder.date.setText(listData.get(position).date);
        itemViewHolder.file_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listData.get(position).file));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(WeekInfo_Item data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

        TextView subject;
        TextView date;
        ImageButton file_url;

        ItemViewHolder(View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.subject);
            date = (TextView) itemView.findViewById(R.id.date);
            file_url = (ImageButton) itemView.findViewById(R.id.file_url);
        }


        void onBind(WeekInfo_Item data) {
            subject.setText(data.getSubject());
            date.setText(data.getDate());
            //file_url.setText(data.getFile());
            file_url = (ImageButton) itemView.findViewById(R.id.file_url);
        }
    }
}
