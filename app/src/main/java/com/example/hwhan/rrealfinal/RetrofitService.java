package com.example.hwhan.rrealfinal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface  RetrofitService {

    String URL = "http://cropmaster.cafe24.com/";

    @GET("test.php")
    Call<ResultModel> getInfo(
            @Query("name") String name
    );

    @FormUrlEncoded
    @POST("login_ok.php")
            Call<ResultModel> login_ok(
            @Field("id") String id, @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login_join.php")
    Call<ResultModel> login_join(
            @Field("id") String id, @Field("password") String password, @Field("email") String email, @Field("number") String number
    );

}
