package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import com.technohub.melifeapp.models.ErrorModel;

import java.util.List;

public interface IExam {

    interface View {

        void init();

        void initClicks();

        Context getContext();

        void showLoading();

        void hideLoading();

        void goToNextFragment();


    }

    interface Presenter {

        void created();

        void getQuestionsFromServer();

    }
}
