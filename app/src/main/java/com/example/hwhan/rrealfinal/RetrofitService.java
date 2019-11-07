package com.example.hwhan.rrealfinal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface  RetrofitService {

    String URL = "http://cropmaster.cafe24.com/";

    @GET("get_userinfo.php")
    Call<ResultModel_userinfo> getuserInfo(
            @Query("ID") String id
    );


    @GET("get_cropdata.php")
    Call<ResultModel_CropData> getcropInfo(
            @Query("NAME") String name
    );

    @GET("get_category.php")
    Call<ResultModel_CropData> getcropname(
            @Query("CATEGORY") String category
    );

    @FormUrlEncoded
    @POST("login_ok.php")
            Call<ResultModel> login_ok(
            @Field("id") String id, @Field("password") String password
    );


    @FormUrlEncoded
    @POST("cropinfo_fragment.php")
    Call<ResultModel> cropinfo_fragment(
            @Field("cropname") String cropname
    );

    @FormUrlEncoded
    @POST("login_update.php")
    Call<ResultModel> login_update(
            @Field("id") String id, @Field("password") String password, @Field("email") String email, @Field("number") String number
    );

    @FormUrlEncoded
    @POST("login_join.php")
    Call<ResultModel> login_join(
            @Field("id") String id, @Field("password") String password, @Field("email") String email, @Field("number") String number
    );

    @GET("home_info.php")
    Call<ResultModel_HomeInfo> gethomeinfo(
            @Query("title") String title
    );

    @GET("home_info_tab1.php")
    Call<ResultModel_HomeInfo1> gethomeinfo1(
            @Query("title") String title
    );

    @GET("home_info_tab2.php")
    Call<ResultModel_HomeInfo2> gethomeinfo2(
            @Query("title") String title
    );

    @GET("home_info_tab3.php")
    Call<ResultModel_HomeInfo3> gethomeinfo3(
            @Query("title") String title
    );

    @GET("home_info_tab4.php")
    Call<ResultModel_HomeInfo4> gethomeinfo4(
            @Query("title") String title
    );

    @GET("recommend_info.php")
    Call<ResultModel_RecoInfo>  getrecoinfo(
            @Query("locate") String locate
    );

    @GET("locate_reco_fragment.php")
    Call<ResultModel_LocateReco>  getlocatereco(
            @Query("locate") String locate
    );

    @GET("crop_summary.php")
    Call<ResultModel_CropSummary>  getcropsum(
            @Query("crop_name") String crop_name
    );



}
