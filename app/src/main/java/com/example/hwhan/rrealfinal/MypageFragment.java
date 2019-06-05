package com.example.hwhan.rrealfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MypageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_fragment, container, false);


        TextView updateinfo = view.findViewById(R.id.updateinfo);
        TextView favlocate = view.findViewById(R.id.favlocate);
        TextView favcrop = view.findViewById(R.id.favcrop);



        favlocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_favlocate.class);
                startActivity(intent);
            }
        });
        favcrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_favcrop.class);
                startActivity(intent);
            }
        });
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.hwhan.rrealfinal.Mypage_update.class);
                startActivity(intent);
            }
        });






        return view;
    }
}
