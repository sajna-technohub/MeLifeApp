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

                if (response.isSuccessful()) {

                        LoginResponse loginResponse = response.body();
                        Log.e("LoginResponse", loginResponse.getMessage());
                        List<User> userdata = response.body().getData();

                        view.hideLoading();
                        view.LoginSuccessful();
                        if( response.body().getData().get(0).getCompletion_status().equals("Y"))
                        {
                            for (User user : userdata)
                            {
                                Log.e("id", user.getMelife_user_id() + "");
                                Log.e("complete status", user.getCompletion_status());
                                Log.e("Name", user.getName());
                                new LoginResponse().setSharedPreferences(view.getContext(), user.getName(), user.getMelife_user_id(), user.getCompletion_status(), user.getDeviceToken(),user.getEmail());
                           Log.e("sharedpre","set");
                            }

                            view.goToMainActivity();
                         }
                        else
                            {
                            view.goToProfileEdit(loginResponse.getData().get(0).getMelife_user_id());
                            }

                    }
                    else
                    {

                        view.LoginFailed();
                        view.hideLoading();
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
