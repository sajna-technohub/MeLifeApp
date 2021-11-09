package com.technohub.melifeapp.presenter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.technohub.melifeapp.Interfaces.IProfile;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ProfileModel;
import com.technohub.melifeapp.models.ProfileNew;
import com.technohub.melifeapp.models.ProfileRes;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfilePresenter implements IProfile.Presenter {

    IProfile.View view;
    User user;

//
//    public ProfilePresenter(IProfile.View view, User user) {
//        this.view = view;
//        this.user = user;
//        Log.e("Prof presenter", "constructor");
//    }
 public ProfilePresenter(IProfile.View view) {
        this.view = view;
        Log.e("Prof presenter", "de constructor");
    }

    @Override
    public void getProfile(String userid)
    {
        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        User user=new User();
        user.setUser_id(userid);
        user.setDeviceType("1");
        user.setDeviceToken("uhg");
        Call<ProfileResponse> call = retrofitApi.userdetails(user);
        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {



                if (response.isSuccessful() ) {

                    ProfileResponse profileResponse = response.body();


                        for (ProfileModel p : profileResponse.getData())
                        {

                            Log.e("name", p.getName());
                            Log.e("email", p.getEmail());
                            Log.e("phone", p.getMobile_no());
                            Log.e("pin", p.getPincode());
//                            Log.e("dob", p.getDob());
//                            Log.e("quali", p.getQualification());
//                            Log.e("state", p.getState());

                        }
                    view.setProfile(profileResponse);
                    view.hideLoading();
                }

                    else {
                        view.hideLoading();
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

    }

//    public void UpdateButtonClick(User user) {
//        view.showLoading();
//        Log.e("Prof buttonclick", "update");
//        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
//        Call<ProfileResponse> call = retrofitApi.userprofile(user);
//        call.enqueue(new Callback<ProfileResponse>() {
//
//            @Override
//            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
//
//                ProfileResponse profileResponse = response.body();
//
//                if (response.isSuccessful() && response.body() != null)
//                {
//                    if (response.body().getMessage() != null) {
//                        Log.e("ProfUpdate resss", profileResponse.getData() + "");
////                        for (ProfileModel p : profileResponse.get)
////                        {
////                            Log.e("prof up name", p.getName());
////                            Log.e("email", p.getEmail());
////                            Log.e("phone", p.getMobile_no());
////                            Log.e("pin", p.getPincode());
////                            Log.e("dob", p.getDob());
////                            Log.e("quali", p.getQualification());
////                            new LoginResponse().setQualiSharedPreferences(view.getContext(), p.getQualification());
////
////                        }
//
//                        view.setProfile(profileResponse);
//
//                        view.UpdateMessage(response.body().getMessage());
//                        view.hideLoading();
//                        view.goToDashboard();
//                    }
//                    else {
//                        view.hideLoading();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProfileResponse> call, Throwable t) {
//                Log.e("Profile up res", "faileddd");
//                view.hideLoading();
//            }
//        });
//
//    }

    public void uploadFile(File file,User user) {

        Log.e("inpresenter","gjhg6k");
        Log.e("profile",user.getName());
        Log.e("profile",user.getUser_id());
        Log.e("profile",user.getEmail());
        Log.e("profile",user.getCountry());
        Log.e("profile",user.getMobno());


//        RequestBody requestFile = RequestBody.create(MediaType.parse(con.getContentResolver().getType(fileUri)), file);
//        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), userid);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/*"), file);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("profile_icon", file.getName(), requestFile);
Log.e("name and mob",file.getName()+user.getMobno());
// add another part within the multipart request
        RequestBody name =
                RequestBody.create(MediaType.parse("text/plain"), user.getName());
        RequestBody useridd =   RequestBody.create(MediaType.parse("text/plain"), user.getUser_id());
        RequestBody mob =  RequestBody.create(MediaType.parse("text/plain"), user.getMobno());
        RequestBody dob =  RequestBody.create(MediaType.parse("text/plain"), user.getDate());
        RequestBody pin =  RequestBody.create(MediaType.parse("text/plain"), user.getPincode());
        RequestBody quali =  RequestBody.create(MediaType.parse("text/plain"), user.getQualification());
        RequestBody country =  RequestBody.create(MediaType.parse("text/plain"), user.getCountry());
        RequestBody state =  RequestBody.create(MediaType.parse("text/plain"), user.getState());
        RequestBody dtype =  RequestBody.create(MediaType.parse("text/plain"), user.getDeviceType());
        RequestBody dtoken =  RequestBody.create(MediaType.parse("text/plain"), user.getDeviceToken());
        //creating retrofit object
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        //creating a call and calling the upload image method
        Call<ProfileNew> call = retrofitApi.userprofile(body, useridd,name,mob,dob,pin,state,quali,country,dtype,dtoken);

        //finally performing the call
        call.enqueue(new Callback<ProfileNew>() {
            @Override
            public void onResponse(Call<ProfileNew> call, Response<ProfileNew> response) {
                ProfileNew user=response.body();
                if (response.isSuccessful()) {
                    Log.e("profileres",user.getMessage());
                }
////                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
//                } else {
////                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<ProfileNew> call, Throwable t) {
             Log.e("profile","failed");
            }
        });
    }
    public void UpdateProfile(User user) {
        view.showLoading();
        Log.e("Prof buttonclick", "update");
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<ProfileRes> call = retrofitApi.userprofile(user);
        call.enqueue(new Callback<ProfileRes>() {

            @Override
            public void onResponse(Call<ProfileRes> call, Response<ProfileRes> response) {

                if (response.isSuccessful()) {
                    ProfileRes profileResponse = response.body();
//                        getProfile(user.getUserid());
                        Log.e("ProfUpdate resss", profileResponse.getData() + "");
                        Log.e("ProfUpdate name", profileResponse.getData().getUserdetails().get(0).getName()+ "");
                        view.hideLoading();
                        view.alert();

                    } else {
                        view.hideLoading();
                    }
                }


            @Override
            public void onFailure(Call<ProfileRes> call, Throwable t) {
                Log.e("Profile up res", "faileddd");
                Log.e("Profile up res", t.getMessage());
                view.hideLoading();
            }
        });


    }
}
