package com.technohub.melifeapp.presenter;

import android.util.Log;

import com.technohub.melifeapp.Interfaces.ILogin;
import com.technohub.melifeapp.classes.ApiToken;
import com.technohub.melifeapp.models.JsonModel;
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
        Call<LoginResponse> call = retrofitApi.Login(email,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("message",loginResponse.getMessage());
                    List<User> userdata=response.body().getData();
                    for (User user:userdata) {
                      Log.e("id",user.getMelife_user_id()+"");
                        Log.e("complete status",user.getCompletion_status());
                        Log.e("Email",user.getEmail());
                        Log.e("Name",user.getName());
                        new LoginResponse().setSharedPreferences(view.getContext(),user.getName(),user.getMelife_user_id(),user.getCompletion_status(),user.getToken());
                    }
//
                    view.goToMainActivity();
                } else if (response.errorBody() != null) {

                    try {
                        view.hideLoading();
                        Log.e("apitoken","failed");
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
    public void registerButtonClick() {

        view.goToRegisterActivity();
    }

//    @Override
//    public void githubLinkClick() {
//
//        view.goToWebBrowser(0);
//    }
//
//    @Override
//    public void privacyPolicyLinkClick() {
//
//        view.goToWebBrowser(1);
//    }
}
