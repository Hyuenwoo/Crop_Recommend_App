package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class HomeFragment_SoilCheck extends Fragment implements MainActivity.OnBackPressedListener{

    public SoilCheck_Adapter soilcheck_adapter;
    private HomeFragment homeFragment = new HomeFragment();
    boolean inDownUrl = false;
    boolean inDate = false;
    boolean inSubject = false;
    boolean initem = false;
    boolean inHit = false;
    boolean inName = false;
    String downUrl = "";
    String date = "";
    String subject = "";
    String hit = "";
    String name = "";

    SoilCheck_Item temp;

    ArrayList<SoilCheck_Item> parse_data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        homeFragment = new HomeFragment();
        View view = inflater.inflate(R.layout.home_fragment_soilcheck, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        soilcheck_adapter = new SoilCheck_Adapter(getContext());
        recyclerView.setAdapter(soilcheck_adapter);

        StrictMode.enableDefaults();

        try {
            URL url = new URL("http://api.nongsaro.go.kr/service/dbyhsCccrrncInfo/dbyhsCccrrncInfoList?"
                    + "apiKey=20191104QHOTHFXSUWRBFXX5LZAQ"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("downFile")) { //title 만나면 내용을 받을수 있게 하자
                            inDownUrl = true;
                        } else if (parser.getName().equals("cntntsSj")) { //mapx 만나면 내용을 받을수 있게 하자
                            inName = true;
                        } else if (parser.getName().equals("cntntsNo")) { //mapx 만나면 내용을 받을수 있게 하자
                            inHit = true;
                        } else if (parser.getName().equals("registDt")) { //address 만나면 내용을 받을수 있게 하자
                            inDate = true;
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inName) { //isAddress이 true일 때 태그의 내용을 저장.
                            temp = new SoilCheck_Item();
                            name = parser.getText();
                            temp.setSubject(name);
                            inName = false;
                        } else if (inDownUrl) { //isTitle이 true일 때 태그의 내용을 저장.
                        downUrl = parser.getText();
                        temp.setFile(downUrl);
                        inDownUrl = false;
                        } else if (inHit) { //isMapx이 true일 때 태그의 내용을 저장.
                            hit = parser.getText();
                            inHit = false;
                        } else if (inDate) { //isAddress이 true일 때 태그의 내용을 저장.
                            date = parser.getText();
                            temp.setDate(date);
                            inDate = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            soilcheck_adapter.addItem(temp);
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
            soilcheck_adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }



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
