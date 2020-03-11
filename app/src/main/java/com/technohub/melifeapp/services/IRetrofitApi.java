package com.technohub.melifeapp.services;

import android.database.Observable;

import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.models.ExamRequest;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.models.InstructionRequest;
import com.technohub.melifeapp.models.InstructionResponse;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.RegisterResponse;
import com.technohub.melifeapp.models.SignUpModel;
import com.technohub.melifeapp.models.TestCategoriesModel;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IRetrofitApi {
    /*for register on RegisterActivity*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("register")
    Call<RegisterResponse> Register(@Body SignUpModel user);

    /*for Log in on LoginActivity*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("signin")
    Call<LoginResponse> Login(@Body User user);


    //    forgotpassord
      @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
      @POST("forgot_password")
      Call<LoginResponse> forgot_password(@Body SignUpModel user);

    /*for  TestCategory*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("dashboard")
    Call<TestcategoryResponse> dashboard(@Body TestCategoriesModel testCategoriesModel);


    /*for  Get userprofile*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("userdetails")
    Call<ProfileResponse> userdetails(@Body User profileRequest);

    /*for   userprofileUpdate*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("userprofile")
    Call<ProfileResponse> userprofile(@Body User user);

    /*for  upload image*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @Multipart
    @POST("uploadimage")
    Call<User> uploadImage(@Part("profile") RequestBody file, @Part("userid") RequestBody userid);

    /*for  Get examinitiate*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("displayquestions")
    Call<ExamResponse> displayquestions(@Body ExamRequest examRequest);

    /*for  loading questions*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadquestions")
    Call<LoadQuestionResponse> loadquestions(@Body LoadQuestionRequest loadQuestionRequest);

    /*for  instruction pop up*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("saveinstructionpopupstatus")
    Call<InstructionResponse> saveinstructionpopupstatus(@Body InstructionRequest instructionRequest);

}

