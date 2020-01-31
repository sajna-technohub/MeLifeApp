package com.technohub.melifeapp.presenter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technohub.melifeapp.Interfaces.IProfile;
import com.technohub.melifeapp.models.ProfileModel;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ProfilePresenter implements IProfile.Presenter {

    IProfile.View view;
    User user;


    public ProfilePresenter(IProfile.View view, User user) {
        this.view = view;
        this.user = user;
        Log.e("Prof presenter", "constructor");
    }

    @Override
    public void getProfile() {

        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<ProfileResponse> call = retrofitApi.userdetails(user);
        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                ProfileResponse profileResponse = response.body();

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getMessage().equals("Success")) {
                        Log.e("Prof name", profileResponse.getData().size() + "");
                        for (ProfileModel p : profileResponse.getData())
                        {

                            Log.e("name", p.getName());
                            Log.e("email", p.getEmail());
                            Log.e("phone", p.getMobile_no());
                            Log.e("pin", p.getPincode());
                            Log.e("dob", p.getDob());
                            Log.e("quali", p.getQualification());

                        }
                        view.setProfile(profileResponse);
                        view.hideLoading();

                    } else {
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("Profile res", "faileddd");
                view.hideLoading();

            }
        });
    }

    @Override
    public void created() {

        view.init();
        view.initClicks();
        getProfile();
    }

    @Override
    public void UpdateButtonClick(User user) {
        view.showLoading();
        Log.e("Prof buttonclick", "update");
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<ProfileResponse> call = retrofitApi.userprofile(user);
        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                ProfileResponse profileResponse = response.body();

                if (response.isSuccessful() && response.body() != null)
                {
                    if (response.body().getMessage() != null) {
                        getProfile();
                        Log.e("ProfUpdate resss", profileResponse.getData() + "");
                        for (ProfileModel p : profileResponse.getData()) {
                            Log.e("prof up name", p.getName());
                            Log.e("email", p.getEmail());
                            Log.e("phone", p.getMobile_no());
                            Log.e("pin", p.getPincode());
                            Log.e("dob", p.getDob());
                            Log.e("quali", p.getQualification());

                        }
                        view.hideLoading();
                        view.UpdateMessage(response.body().getMessage());
                        view.goToDashboard();
                    }
                    else {
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("Profile up res", "faileddd");
                view.hideLoading();
            }
        });

    }

    public void uploadFile(File file, Uri fileUri, String userid, Context con) {

        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(con.getContentResolver().getType(fileUri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), userid);

//        //The gson builder
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();


        //creating retrofit object
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        //creating a call and calling the upload image method
        Call<User> call = retrofitApi.uploadImage(requestFile, descBody);

        //finally performing the call
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
//                if (!response.body().error) {
////                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
//                } else {
////                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
