package com.example.hwhan.rrealfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import java.util.ArrayList;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    Button ariticle1;
    Button ariticle2;
    Button ariticle3;
    Button ariticle4;

    Button category1;
    Button category2;
    Button category3;
    Button category4;

    TextView showDate1,showDate2,showDate3,showDate4;

    Document document;
    RetrofitService retrofitService;
    String url_piece_collect, testa, testb,testc,testd,teste,testf,testg,testh;
    String date_piece_collect;
    String url1, url2, url3, url4, url5, url6, url7, url8,url9, url10, url11, url12,url13, url14, url15, url16;
    String date1, date2, date3, date4;
    String check1,check2,check3,check4,check5;
    String title ;
    String[] array, array2;

    ImageButton weekInfo;
    ImageButton soilcheck;

    FlipAdapter flipadapter;
    AutoScrollViewPager autoViewPager;
    TextView viewtext;


    HomeFragment_WeekInfo homeFragment_weekInfo;
    HomeFragment_SoilCheck homeFragment_soilcheck;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        homeFragment_weekInfo = new HomeFragment_WeekInfo();
        homeFragment_soilcheck = new HomeFragment_SoilCheck();

        ariticle1= view.findViewById(R.id.showtext1);
        ariticle2= view.findViewById(R.id.showtext2);
        ariticle3= view.findViewById(R.id.showtext3);
        ariticle4= view.findViewById(R.id.showtext4);

        category1= view.findViewById(R.id.category1);
        category2= view.findViewById(R.id.category2);
        category3= view.findViewById(R.id.category3);
        category4= view.findViewById(R.id.category4);

        showDate1 = view.findViewById(R.id.showDate1);
        showDate2 = view.findViewById(R.id.showDate2);
        showDate3 = view.findViewById(R.id.showDate3);
        showDate4 = view.findViewById(R.id.showDate4);


        weekInfo = view.findViewById(R.id.weekinfo);
        soilcheck = view.findViewById(R.id.soilcheck);

        title = "test";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.gethomeinfo2(title).enqueue(new Callback<ResultModel_HomeInfo2>() {
            @Override
            public void onResponse(Call<ResultModel_HomeInfo2> call, Response<ResultModel_HomeInfo2> response) {
                ResultModel_HomeInfo2 result = response.body();

                check1 = result.getResult().get(0);
                check2 = result.getResult().get(1);
                check3 = result.getResult().get(2);
                check4 = result.getResult().get(3);

                check1 = check1.replaceAll("&lt;","<");
                check1 = check1.replaceAll("&gt;", ">");
                check1 = check1.replaceAll("&#039;","'");

                check2 = check2.replaceAll("&lt;","<");
                check2 = check2.replaceAll("&gt;", ">");
                check2 = check2.replaceAll("&#039;","'");

                check3 = check3.replaceAll("&lt;","<");
                check3 = check3.replaceAll("&gt;", ">");
                check3 = check3.replaceAll("&#039;","'");

                check4 = check4.replaceAll("&lt;","<");
                check4 = check4.replaceAll("&gt;", ">");
                check4 = check4.replaceAll("&#039;","'");


                ariticle1.setText(check1);
                ariticle2.setText(check2);
                ariticle3.setText(check3);
                ariticle4.setText(check4);
                url_piece_collect = result.getResult().get(4);
                date_piece_collect = result.getResult().get(5);
                Toast.makeText(getContext(),url_piece_collect,Toast.LENGTH_LONG).show();
                testa = url_piece_collect;
                testb = date_piece_collect;

                showDate1.setText(testb.substring(0, 10));
                showDate2.setText(testb.substring(10, 20));
                showDate3.setText(testb.substring(20, 30));
                showDate4.setText(testb.substring(30, 40));

            }

            @Override
            public void onFailure(Call<ResultModel_HomeInfo2> call, Throwable t) {

            }
        });

        if(testa!=null) {

            url1 = testa.substring(0, 4);
            url2 = testa.substring(4, 8);
            url3 = testa.substring(8, 12);
            url4 = testa.substring(12, 16);

            url1 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url1 + "bbsDetail.do";
            url2 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url2 + "bbsDetail.do";
            url3 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url3 + "bbsDetail.do";
            url4 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url4 + "bbsDetail.do";

            ariticle1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                    startActivity(intent);
                }
            });

            ariticle2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                    startActivity(intent);
                }
            });

            ariticle3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                    startActivity(intent);
                }
            });

            ariticle4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url4));
                    startActivity(intent);
                }
            });
        }

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.gethomeinfo2(title).enqueue(new Callback<ResultModel_HomeInfo2>() {
                    @Override
                    public void onResponse(Call<ResultModel_HomeInfo2> call, Response<ResultModel_HomeInfo2> response) {
                        ResultModel_HomeInfo2 result = response.body();

                        check1 = result.getResult().get(0);
                        check2 = result.getResult().get(1);
                        check3 = result.getResult().get(2);
                        check4 = result.getResult().get(3);

                        check1 = check1.replaceAll("&lt;","<");
                        check1 = check1.replaceAll("&gt;", ">");
                        check1 = check1.replaceAll("&#039;","'");

                        check2 = check2.replaceAll("&lt;","<");
                        check2 = check2.replaceAll("&gt;", ">");
                        check2 = check2.replaceAll("&#039;","'");

                        check3 = check3.replaceAll("&lt;","<");
                        check3 = check3.replaceAll("&gt;", ">");
                        check3 = check3.replaceAll("&#039;","'");

                        check4 = check4.replaceAll("&lt;","<");
                        check4 = check4.replaceAll("&gt;", ">");
                        check4 = check4.replaceAll("&#039;","'");


                        ariticle1.setText(check1);
                        ariticle2.setText(check2);
                        ariticle3.setText(check3);
                        ariticle4.setText(check4);
                        url_piece_collect = result.getResult().get(4);
                        date_piece_collect = result.getResult().get(5);
                        Toast.makeText(getContext(),url_piece_collect,Toast.LENGTH_LONG).show();
                        testa = url_piece_collect;
                        testb = date_piece_collect;

                        showDate1.setText(testb.substring(0, 10));
                        showDate2.setText(testb.substring(10, 20));
                        showDate3.setText(testb.substring(20, 30));
                        showDate4.setText(testb.substring(30, 40));

                    }

                    @Override
                    public void onFailure(Call<ResultModel_HomeInfo2> call, Throwable t) {

                    }
                });

                if(testa!=null) {

                    url1 = testa.substring(0, 4);
                    url2 = testa.substring(4, 8);
                    url3 = testa.substring(8, 12);
                    url4 = testa.substring(12, 16);

                    url1 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url1 + "bbsDetail.do";
                    url2 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url2 + "bbsDetail.do";
                    url3 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url3 + "bbsDetail.do";
                    url4 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000003/" + url4 + "bbsDetail.do";

                    ariticle1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                            startActivity(intent);
                        }
                    });

                    ariticle2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                            startActivity(intent);
                        }
                    });

                    ariticle3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                            startActivity(intent);
                        }
                    });

                    ariticle4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url4));
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.gethomeinfo1(title).enqueue(new Callback<ResultModel_HomeInfo1>() {
                    @Override
                    public void onResponse(Call<ResultModel_HomeInfo1> call, Response<ResultModel_HomeInfo1> response) {
                        ResultModel_HomeInfo1 result = response.body();

                        check1 = result.getResult().get(0);
                        check2 = result.getResult().get(1);
                        check3 = result.getResult().get(2);
                        check4 = result.getResult().get(3);

                        check1 = check1.replaceAll("&lt;","<");
                        check1 = check1.replaceAll("&gt;", ">");
                        check1 = check1.replaceAll("&#039;","'");

                        check2 = check2.replaceAll("&lt;","<");
                        check2 = check2.replaceAll("&gt;", ">");
                        check2 = check2.replaceAll("&#039;","'");

                        check3 = check3.replaceAll("&lt;","<");
                        check3 = check3.replaceAll("&gt;", ">");
                        check3 = check3.replaceAll("&#039;","'");

                        check4 = check4.replaceAll("&lt;","<");
                        check4 = check4.replaceAll("&gt;", ">");
                        check4 = check4.replaceAll("&#039;","'");


                        ariticle1.setText(check1);
                        ariticle2.setText(check2);
                        ariticle3.setText(check3);
                        ariticle4.setText(check4);
                        url_piece_collect = result.getResult().get(4);
                        date_piece_collect = result.getResult().get(5);
                        Toast.makeText(getContext(),url_piece_collect,Toast.LENGTH_LONG).show();
                        testc = url_piece_collect;
                        testd = date_piece_collect;

                        showDate1.setText(testd.substring(0, 10));
                        showDate2.setText(testd.substring(10, 20));
                        showDate3.setText(testd.substring(20, 30));
                        showDate4.setText(testd.substring(30, 40));

                    }

                    @Override
                    public void onFailure(Call<ResultModel_HomeInfo1> call, Throwable t) {

                    }
                });

                if(testc!=null) {

                    url5 = testc.substring(0, 4);
                    url6 = testc.substring(4, 8);
                    url7 = testc.substring(8, 12);
                    url8 = testc.substring(12, 16);

                    url5 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url5 + "bbsDetail.do";
                    url6 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url6 + "bbsDetail.do";
                    url7 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url7 + "bbsDetail.do";
                    url8 = "http://www.returnfarm.com/cmn/board/OLDBBSMSTR_000000006/" + url8+ "bbsDetail.do";

                    ariticle1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url5));
                            startActivity(intent);
                        }
                    });

                    ariticle2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url6));
                            startActivity(intent);
                        }
                    });

                    ariticle3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url7));
                            startActivity(intent);
                        }
                    });

                    ariticle4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url8));
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.gethomeinfo3(title).enqueue(new Callback<ResultModel_HomeInfo3>() {
                    @Override
                    public void onResponse(Call<ResultModel_HomeInfo3> call, Response<ResultModel_HomeInfo3> response) {
                        ResultModel_HomeInfo3 result = response.body();

                        check1 = result.getResult().get(0);
                        check2 = result.getResult().get(1);
                        check3 = result.getResult().get(2);
                        check4 = result.getResult().get(3);

                        check1 = check1.replaceAll("&lt;","<");
                        check1 = check1.replaceAll("&gt;", ">");
                        check1 = check1.replaceAll("&#039;","'");

                        check2 = check2.replaceAll("&lt;","<");
                        check2 = check2.replaceAll("&gt;", ">");
                        check2 = check2.replaceAll("&#039;","'");

                        check3 = check3.replaceAll("&lt;","<");
                        check3 = check3.replaceAll("&gt;", ">");
                        check3 = check3.replaceAll("&#039;","'");

                        check4 = check4.replaceAll("&lt;","<");
                        check4 = check4.replaceAll("&gt;", ">");
                        check4 = check4.replaceAll("&#039;","'");


                        ariticle1.setText(check1);
                        ariticle2.setText(check2);
                        ariticle3.setText(check3);
                        ariticle4.setText(check4);
                        url_piece_collect = result.getResult().get(4);
                        date_piece_collect = result.getResult().get(5);
                        Toast.makeText(getContext(),url_piece_collect,Toast.LENGTH_LONG).show();
                        teste = url_piece_collect;
                        testf = date_piece_collect;

                        showDate1.setText(testf.substring(0, 10));
                        showDate2.setText(testf.substring(10, 20));
                        showDate3.setText(testf.substring(20, 30));
                        showDate4.setText(testf.substring(30, 40));

                    }

                    @Override
                    public void onFailure(Call<ResultModel_HomeInfo3> call, Throwable t) {

                    }
                });

                if(teste!=null) {

                    array = new String[4];

                    array = teste.split(" ");


                    url9 = array[0].substring(1);
                    url10 = array[1];
                    url11 = array[2];
                    url12 = array[3];

                    ariticle1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url9));
                            startActivity(intent);
                        }
                    });

                    ariticle2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url10));
                            startActivity(intent);
                        }
                    });

                    ariticle3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url11));
                            startActivity(intent);
                        }
                    });

                    ariticle4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url12));
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.gethomeinfo4(title).enqueue(new Callback<ResultModel_HomeInfo4>() {
                    @Override
                    public void onResponse(Call<ResultModel_HomeInfo4> call, Response<ResultModel_HomeInfo4> response) {
                        ResultModel_HomeInfo4 result = response.body();

                        check1 = result.getResult().get(0);
                        check2 = result.getResult().get(1);
                        check3 = result.getResult().get(2);
                        check4 = result.getResult().get(3);

                        check1 = check1.replaceAll("&lt;","<");
                        check1 = check1.replaceAll("&gt;", ">");
                        check1 = check1.replaceAll("&#039;","'");

                        check2 = check2.replaceAll("&lt;","<");
                        check2 = check2.replaceAll("&gt;", ">");
                        check2 = check2.replaceAll("&#039;","'");

                        check3 = check3.replaceAll("&lt;","<");
                        check3 = check3.replaceAll("&gt;", ">");
                        check3 = check3.replaceAll("&#039;","'");

                        check4 = check4.replaceAll("&lt;","<");
                        check4 = check4.replaceAll("&gt;", ">");
                        check4 = check4.replaceAll("&#039;","'");


                        ariticle1.setText(check1);
                        ariticle2.setText(check2);
                        ariticle3.setText(check3);
                        ariticle4.setText(check4);
                        url_piece_collect = result.getResult().get(4);
                        date_piece_collect = result.getResult().get(5);
                        Toast.makeText(getContext(),url_piece_collect,Toast.LENGTH_LONG).show();
                        testg = url_piece_collect;
                        testh = date_piece_collect;

                        showDate1.setText(testh.substring(0, 10));
                        showDate2.setText(testh.substring(10, 20));
                        showDate3.setText(testh.substring(20, 30));
                        showDate4.setText(testh.substring(30, 40));

                    }

                    @Override
                    public void onFailure(Call<ResultModel_HomeInfo4> call, Throwable t) {

                    }
                });

                if(testg!=null) {

                    array2 = new String[4];
                    array2 = testg.split(" ");

                    //Toast.makeText(getContext(), array[0]+"//"+array[1]+"//"+array[2]+"//"+array[3],Toast.LENGTH_LONG).show();

                    url13 = array[0].substring(1);
                    url14 = array[1];
                    url15 = array[2];
                    url16 = array[3];

                    ariticle1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url13));
                            startActivity(intent);
                        }
                    });

                    ariticle2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url14));
                            startActivity(intent);
                        }
                    });

                    ariticle3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url15));
                            startActivity(intent);
                        }
                    });

                    ariticle4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url16));
                            startActivity(intent);
                        }
                    });
                }
            }
        });


        //버튼
        weekInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, homeFragment_weekInfo);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        soilcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, homeFragment_soilcheck);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        //오토뷰페이저

        final ArrayList<Integer> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        viewtext = view.findViewById(R.id.viewtext);
        autoViewPager = view.findViewById(R.id.autoViewPager);

        data.add(R.drawable.nongsa_img10);
        data.add(R.drawable.jin);
        data.add(R.drawable.re_agri4);

//        data.add(R.drawable.nongsa_img6);
//        data.add(R.drawable.re_agri3);
//        data.add(R.drawable.jin);
        Context context = view.getContext();
        flipadapter = new FlipAdapter(context, data);
        autoViewPager.setAdapter(flipadapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(4000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작
        autoViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position < data.size()) {
                    autoViewPager.setCurrentItem(position + data.size(), false);
                } else if (position >= data.size() * 2) {
                    autoViewPager.setCurrentItem(position - data.size(), false);
                }
                position = position % data.size();
                if (position == 0) {  // 첫 페이지
                    viewtext.setText("1/3");

                } else if (position == 1) {   //두번째 페이지
                    viewtext.setText("2/3");

                } else if (position == 2) {
                    viewtext.setText("3/3");
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }


}
