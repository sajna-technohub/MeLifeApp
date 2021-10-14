package com.technohub.melifeapp.presenter;

import android.util.Log;

import com.technohub.melifeapp.Interfaces.ILogin;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILogin.Presenter {
    private ILogin.View view;

    public LoginPresenter(ILogin.View view) {
        this.view = view;
    }
    @Override
    public void created() {

        view.init();
        view.initClicks();
    }

    @Override
    public void loginButtonClick(String email, String password) {

        view.showLoading();
        view.clearErrors();

         IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
         User user=new User();
         user.setDeviceToken("jgskjgdsjk");
         user.setUser_email(email);
         user.setUser_Password(password);
         user.setDeviceType("1");
        Call<LoginResponse> call = retrofitApi.Login(user);
        call.enqueue(new Callback<LoginResponse>()
        {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    if(response.body().getMessage().equals("Success")) {
                        Log.e("LoginResponse", loginResponse.getMessage());
                        List<User> userdata = response.body().getData();
                        for (User user : userdata)
                        {
                            Log.e("id", user.getMelife_user_id() + "");
                            Log.e("complete status", user.getCompletion_status());
                            Log.e("Name", user.getName());
                            new LoginResponse().setSharedPreferences(view.getContext(), user.getName(), user.getMelife_user_id(), user.getCompletion_status(), user.getDeviceToken(),email);

                        }
                        view.hideLoading();
                        view.LoginSuccessful();
                        if( response.body().getData().get(0).getCompletion_status().equals("Y"))
                        {
                            view.goToMainActivity();
                         }
                        else
                            {
                            view.goToProfileEdit();
                            }

                    }
                    else
                    {
                        Log.e("LoginResponse", loginResponse.getMessage());
                        view.LoginFailed();
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LoginResponse","faileddd");
                view.hideLoading();
                view.LoginFailed();
            }
        });
    }


    @Override
    public void registerButtonClick() {

        view.goToRegisterActivity();
    }

    @Override
    public void forgotButtonClick() {

        view.goToForgotActivity();
    }
}
