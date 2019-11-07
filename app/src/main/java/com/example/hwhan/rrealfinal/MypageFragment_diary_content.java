package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MypageFragment_diary_content extends Fragment implements MainActivity.OnBackPressedListener {

    TextView Title, Content, Date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment_diary_content, container, false);
        Title = view.findViewById(R.id.diary_content_title);
        Content = view.findViewById(R.id.diary_content_content);
        Date = view.findViewById(R.id.diary_content_date);
        Bundle bundle = this.getArguments();
        Title.setText(bundle.getString("title"));
        Content.setText(bundle.getString("content"));
        Date.setText(bundle.getString("date"));

    return view;
    }
    @Override
    public void onBack() {

    }
}
