package com.technohub.melifeapp.presenter;

import android.util.Log;

import com.technohub.melifeapp.Interfaces.IForgot;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.SignUpModel;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPwdPresenter implements IForgot.Presenter{
    private IForgot.View view;

    public ForgotPwdPresenter(IForgot.View view)
    {
        this.view = view;
    }

    @Override
    public void created() {
        view.init();
        view.initClicks();
    }

    @Override
    public void forgotButtonClick(String email) {
        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        SignUpModel user=new SignUpModel();
        user.setEmail(email);
        Call<LoginResponse> call = retrofitApi.forgot_password(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && response.body() != null)
                {
                    if(response.body().getMessage().equals("Please check your email to reset your password".trim()))
                    {
                        Log.e("ResponseForgot", loginResponse.getMessage());
                        view.ForgotToast(response.body().getMessage());
                        view.hideLoading();
                        view.goToLoginActivity();
                    }
                    else
                    {
                        Log.e("ResponseForgot", loginResponse.getMessage());
                        view.ForgotToast(response.body().getMessage());
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoading();
                view.ForgotToast("Something Went Wrong");

            }
        });
    }



}
