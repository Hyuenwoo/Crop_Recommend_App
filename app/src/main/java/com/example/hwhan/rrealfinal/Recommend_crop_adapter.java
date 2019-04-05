package com.example.hwhan.rrealfinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Recommend_crop_adapter extends PagerAdapter {

    private List<Recommend_crop_model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Recommend_crop_adapter(List<Recommend_crop_model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recommend_crop_item,container,false);

        ImageView imageView;
        TextView cropTitle, cropContent;

        imageView = view.findViewById(R.id.cropImage);
        cropTitle = view.findViewById(R.id.cropTitle);
        cropContent = view.findViewById(R.id.cropContent);

        imageView.setImageResource(models.get(position).getCropImage());
        cropTitle.setText(models.get(position).getCropTitle());
        cropContent.setText(models.get(position).getCropContent());

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
