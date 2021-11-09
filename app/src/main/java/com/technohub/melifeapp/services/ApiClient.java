package com.technohub.melifeapp.services;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://www.3gmelife.com:443/appmelife_alpha/webservice/Webservice/";
    public static final String IMG_URL = "https://www.3gmelife.com:443/";
    private static Retrofit retrofit;
    public static Retrofit getApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(new NullOnEmptyConverterFactory(new GsonBuilder().serializeNulls().create()))
                    .build();
        }
        return retrofit;
    }
}
