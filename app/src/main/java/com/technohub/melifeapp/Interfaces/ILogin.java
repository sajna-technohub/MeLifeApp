package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import com.technohub.melifeapp.models.ErrorModel;

import java.util.List;

public interface ILogin {
    interface View {

        void init();

        void initClicks();

        void goToRegisterActivity();

        Context getContext();

        void showLoading();

        void hideLoading();

        void showErrorMessages(List<ErrorModel> errorList);

        void clearErrors();

        void goToMainActivity();

        void LoginFailed();

        void LoginSuccessful();

        void  goToForgotActivity();


    }

    interface Presenter {

        void created();

        void loginButtonClick(String username, String password);

        void registerButtonClick();

        void forgotButtonClick();

    }
}
