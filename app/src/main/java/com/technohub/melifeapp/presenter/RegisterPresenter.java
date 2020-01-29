package com.technohub.melifeapp.presenter;

import android.util.Log;



import com.technohub.melifeapp.Interfaces.IRegister;
import com.technohub.melifeapp.models.RegisterResponse;
import com.technohub.melifeapp.models.SignUpModel;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements IRegister.Presenter {
    IRegister.View view;

    public RegisterPresenter(IRegister.View view) {
        this.view = view;
    }
    @Override
    public void created() {
        view.init();
        view.initClicks();
    }

    @Override
    public void registerButtonClick(String name,String email ,String mobile, String pincode) {
        view.showLoading();
        view.clearErrors();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        SignUpModel user = new SignUpModel();
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPincode(pincode);
        user.setDeviceToken("rhrhryrty");
        user.setDeviceType("1");

        Call<RegisterResponse> call = retrofitApi.Register(user);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Response",registerResponse.getMessage());
                    if(response.body().getMessage().equals("Please check your mail to continue registration process".trim()))
                    {
                        view.RegisterSuccessFully();
                    }
                    else
                    {
                        Log.e("Response",registerResponse.getMessage());
                        view.RegisterExists();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Log.e("Response","failed");
                view.hideLoading();
                view.RegisterFail();
            }
        });
    }

    @Override
    public void backPressed() {

        view.goToLoginActivity();
    }
}
