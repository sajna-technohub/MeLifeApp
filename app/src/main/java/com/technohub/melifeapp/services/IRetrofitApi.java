package com.technohub.melifeapp.services;

import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ExamModel;
import com.technohub.melifeapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofitApi {
    /*for register on RegisterActivity*/
    @Headers({"Accept: application/json"})
    @POST("create")
    Call<User> Register(@Body User user);


    /*for Log in on LoginActivity*/

    @POST("signin")
    Call<LoginResponse> Login(@Query("user_email") String user_email, @Query("user_Password") String user_Password);

    /*for getting question in exam fragment*/
    @Headers({"Accept: application/json"})
    @GET("users?")
    Call<Root> getQuestions(@Query("page") String page);


}
