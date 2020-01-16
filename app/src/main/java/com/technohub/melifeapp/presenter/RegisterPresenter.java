package com.technohub.melifeapp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.technohub.melifeapp.Interfaces.IRegister;
import com.technohub.melifeapp.classes.ApiToken;
import com.technohub.melifeapp.models.JsonModel;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.User;
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
        User user = new User();
        user.setName(name);
        user.setUser_email(email);
        user.setMobile(mobile);
        user.setPincode(pincode);
        Call<LoginResponse> call = retrofitApi.Register(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Response",loginResponse.toString());
                    view.goToMainActivity();
                } else if (response.errorBody() != null) {

                    try {
                        Log.e("Response",response.toString());
                        view.hideLoading();
                        JsonModel jsonParseHelper = new JsonModel(response.errorBody().string());
                        view.showErrorMessages(jsonParseHelper.getErrorList());

                    } catch (Exception e) {}
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                view.hideLoading();
            }
        });
    }

    @Override
    public void backPressed() {

        view.goToLoginActivity();
    }
}
