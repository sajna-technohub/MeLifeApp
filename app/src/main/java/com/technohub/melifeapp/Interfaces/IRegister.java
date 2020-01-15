package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import com.technohub.melifeapp.models.ErrorModel;

import java.util.List;

public interface IRegister {

    interface View {

        void init();

        void initClicks();

        Context getContext();

        void showLoading();

        void hideLoading();

        void showErrorMessages(List<ErrorModel> errorList);

        void clearErrors();

        void goToMainActivity();

        void goToLoginActivity();

        void loginSuccessFully();

        void loginFail();

    }

    interface Presenter {

        void created();

        void registerButtonClick(String firstname,String lastname ,String email, String password);

        void backPressed();



    }
}
