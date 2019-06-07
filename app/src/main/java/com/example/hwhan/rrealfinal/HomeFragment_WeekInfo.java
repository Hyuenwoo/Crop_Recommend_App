package com.example.hwhan.rrealfinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class HomeFragment_WeekInfo extends Fragment {

    boolean inDownUrl = false;
    boolean inDate = false;
    boolean inSubject = false;
    boolean initem = false;
    String downUrl, date, subject;
    TextView weekInfo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_weekinfo, container, false);

        weekInfo = view.findViewById(R.id.weekinfo);

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
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inDownUrl){ //isTitle이 true일 때 태그의 내용을 저장.
                            downUrl = parser.getText();
                            inDownUrl = false;
                        }
                        if(inDate){ //isAddress이 true일 때 태그의 내용을 저장.
                            date = parser.getText();
                            inDate = false;
                        }
                        if(inSubject){ //isMapx이 true일 때 태그의 내용을 저장.
                            subject = parser.getText();
                            inSubject = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            weekInfo.setText(weekInfo.getText()+"날짜 : "+ date +"\n 제목: "+ subject +"\n 다운경로 : " + downUrl
                                    +"\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            weekInfo.setText("error");
        }


        return view;
    }
}
