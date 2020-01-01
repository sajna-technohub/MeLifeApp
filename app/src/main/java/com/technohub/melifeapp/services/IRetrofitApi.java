package com.technohub.melifeapp.services;

import com.technohub.melifeapp.classes.ApiToken;
import com.technohub.melifeapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitApi {
    /*for register on RegisterActivity*/
    @POST("signup")
    Call<ApiToken> Register(@Body User user);


    /*for Log in on LoginActivity*/
    @POST("auth")
    Call<ApiToken> Login(@Body User user);
}
