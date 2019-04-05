package com.example.hwhan.rrealfinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Crop_horizontal_scroll_Adapter extends RecyclerView.Adapter<Crop_horizontal_scroll_Adapter.ItemViewHolder> {
    private ArrayList<Crop_horizontal_scroll_Data> listData = new ArrayList<>();
    private Context context;

    public int getPosition2() {
        return position2;
    }

    private int position2;

    public ArrayList<Crop_horizontal_scroll_Data> getListData() {
        return listData;
    }

    public Crop_horizontal_scroll_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Crop_horizontal_scroll_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_horizontal_scroll_item, parent, false);
        return new ItemViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Crop_horizontal_scroll_Adapter.ItemViewHolder holder, final int position) {


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

    class ItemViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cropimage);
            name = itemView.findViewById(R.id.croptext);
        }

        public void onBind(Crop_horizontal_scroll_Data data) {

            name.setText(data.getTitle());
            image.setImageResource(data.getImage());
        }
    }


}
