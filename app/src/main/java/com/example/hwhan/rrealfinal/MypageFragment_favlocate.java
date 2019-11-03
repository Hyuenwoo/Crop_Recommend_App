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

public class MypageFragment_favlocate extends Fragment implements MainActivity.OnBackPressedListener {

    MapFragment mapFragment;
    MypageFragment mypageFragment;
    private Button backwardBtn;
    private TextView favloc1;
    private RelativeLayout feba;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment_favlocate, container, false);
         mypageFragment = new MypageFragment();
         mapFragment = new MapFragment();

        backwardBtn = view.findViewById(R.id.backwardBtn);
        favloc1 = view.findViewById(R.id.localID);
        feba = view.findViewById(R.id.FEBA);

        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

        favloc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                FragmentTransaction transaction= getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, mapFragment).commitAllowingStateLoss();
                bundle.putString("title", favloc1.getText().toString());
                mapFragment.setArguments(bundle);
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
