package com.technohub.melifeapp.services;

import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ExamModel;
import com.technohub.melifeapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofitApi {
    /*for register on RegisterActivity*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("register")
    Call<LoginResponse> Register(@Body User user);

    /*for Log in on LoginActivity*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("signin")
    Call<LoginResponse> Login(@Body User user);


    /*for getting question in exam fragment*/
    @Headers({"Accept: application/json"})
    @GET("users?")
    Call<Root> getQuestions(@Query("page") String page);
}
