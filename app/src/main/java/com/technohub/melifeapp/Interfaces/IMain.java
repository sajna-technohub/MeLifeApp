package com.technohub.melifeapp.Interfaces;

import android.content.Context;

public interface IMain {


    interface View {

        Context getContext();

        void hideLoading();

        void showLoading();

        void init();


        void tokenError();

        void finishApp();


    }

    interface Presenter {

        void created();

        void getUserToken();


    }
}
