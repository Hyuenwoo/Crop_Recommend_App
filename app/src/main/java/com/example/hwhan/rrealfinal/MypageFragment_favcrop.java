package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MypageFragment_favcrop extends Fragment implements MainActivity.OnBackPressedListener {
    private Button backwardBtn;
    private TextView favcrop1;
    private RelativeLayout favcontainer;
    InfoFragment_crop_info_detail infoFragment_crop_info_detail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_favcrop, container, false);

        infoFragment_crop_info_detail = new InfoFragment_crop_info_detail();
        super.onCreate(savedInstanceState);
        backwardBtn = view.findViewById(R.id.backwardBtn);
        favcrop1 = view.findViewById(R.id.cropID);
        favcontainer = view.findViewById(R.id.favccontain);

        favcrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                FragmentTransaction transaction= getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, infoFragment_crop_info_detail);
                transaction.addToBackStack(null);
                transaction.commit();
                bundle.putString("Cropname", favcrop1.getText().toString());
                infoFragment_crop_info_detail.setArguments(bundle);
            }
        });















        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        return view;
    }

    @Override
    public void onBack() {
        MainActivity activity = (MainActivity)getActivity();
        activity.setOnBackPressedListener(null);
        getActivity().getSupportFragmentManager().popBackStack();
    }
    @Override
    public void onAttach(Activity context){
        super.onAttach(context);
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}
