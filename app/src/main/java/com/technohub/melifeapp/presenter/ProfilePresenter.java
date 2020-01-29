package com.technohub.melifeapp.presenter;

import android.util.Log;

import com.technohub.melifeapp.Interfaces.IProfile;
import com.technohub.melifeapp.models.ProfileModel;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements IProfile.Presenter {

    IProfile.View view;
    User user;

    public ProfilePresenter(IProfile.View view, User user) {
            this.view=view;
            this.user=user;
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

                    if(response.body().getMessage().equals("Success"))

                    {
                        Log.e("Prof name",profileResponse.getData().size()+"");
              for(ProfileModel p:profileResponse.getData())
              {

                  Log.e("name",p.getName());
                  Log.e("email",p.getEmail());
                  Log.e("phone",p.getMobile_no());
                  Log.e("pin",p.getPincode());
                  Log.e("dob",p.getDob());
                  Log.e("quali",p.getQualification());

              }
                        view.setProfile(profileResponse);
                        view.hideLoading();

                    }
                    else

                    {
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t)
            {
                Log.e("Profile res","faileddd");
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
    public void UpdateButtonClick() {

    }
}
