package com.technohub.melifeapp.Interfaces;

public interface IForgot {
    interface  View
    {
        void init();

        void initClicks();

        void ForgotToast(String s);

        void showLoading();

        void hideLoading();

        void goToLoginActivity();
    }
    interface Presenter
    {
        void created();

        void backPressed();

        void forgotButtonClick(String email);

    }
}
