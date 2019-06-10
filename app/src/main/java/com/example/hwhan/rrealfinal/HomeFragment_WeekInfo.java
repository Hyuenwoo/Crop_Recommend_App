package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class HomeFragment_WeekInfo extends Fragment {

    public WeekInfo_Adapter weekInfo_adapter;
    public ArrayList<WeekInfo_Item> weekInfo_items;


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

    WeekInfo_Item temp;
    TextView textView;
    String[] arr1,arr2,arr3;

    ArrayList<WeekInfo_Item> parse_data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_weekinfo, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        weekInfo_adapter = new WeekInfo_Adapter();


       // textView = (TextView) view.findViewById(R.id.check);
        //textView.setText("");

        temp = new WeekInfo_Item("1","2","3");
        parse_data = new ArrayList<>();

        StrictMode.enableDefaults();


        try{
            URL url = new URL("http://api.nongsaro.go.kr/service/weekFarmInfo/weekFarmInfoList?"
                    + "apiKey=20190513PIQHQURR8NU7UD0DNUHUXW"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("downUrl")){ //title 만나면 내용을 받을수 있게 하자
                            inDownUrl = true;
                        }
                        if(parser.getName().equals("regDt")){ //address 만나면 내용을 받을수 있게 하자
                            inDate = true;
                        }
                        if(parser.getName().equals("Subject")){ //mapx 만나면 내용을 받을수 있게 하자
                            inSubject = true;
                        }
                        if(parser.getName().equals("fileName")){ //mapx 만나면 내용을 받을수 있게 하자
                            inName = true;
                        }
                        if(parser.getName().equals("hitCT")){ //mapx 만나면 내용을 받을수 있게 하자
                            inHit = true;
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inDownUrl){ //isTitle이 true일 때 태그의 내용을 저장.
                            downUrl = parser.getText();
                            temp.setFile(downUrl);
                            inDownUrl = false;
                        }
                        if(inDate){ //isAddress이 true일 때 태그의 내용을 저장.
                            date = parser.getText();
                            temp.setDate(date);
                            inDate = false;
                        }
                        if(inSubject){ //isMapx이 true일 때 태그의 내용을 저장.
                            subject = parser.getText();
                            inSubject = false;
                        }
                        if(inName){ //isAddress이 true일 때 태그의 내용을 저장.
                            name = parser.getText();
                            temp.setSubject(name);
                            inName = false;
                        }
                        if(inHit){ //isMapx이 true일 때 태그의 내용을 저장.
                            hit = parser.getText();
                            inHit = false;
                        }
                        parse_data.add(temp);

                        weekInfo_adapter.addItem(temp);
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){


                            //textView.setText(textView.toString()+temp.getDate());

                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }

        } catch(Exception e){
            textView.setText("error");
        }

//        parse_data.get(1).setDate("check");
//        parse_data.get(1).setSubject("check");
//        parse_data.get(1).setFile("check");

//        weekInfo_adapter.listData = parse_data;




        weekInfo_adapter.notifyDataSetChanged();
        recyclerView.setAdapter(weekInfo_adapter);

      // textView.setText(parse_data.get(0).getDate()+parse_data.get(1).getDate()+parse_data.get(2).getDate());

        return view;
    }






}
