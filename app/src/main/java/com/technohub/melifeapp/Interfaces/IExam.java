package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.technohub.melifeapp.classes.Data;
import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.models.ErrorModel;

import java.util.List;

public interface IExam {

    interface View {

        void init();

        void initClicks();

        Context getContext();

        void showLoading();

        void ShowQuestionList(List<Data> root);

        void hideLoading();

        void goToNextFragment();

        void setQuestion();

        void showDialog(DialogFragment fragment);

        void  selectAnswer();

         void LoadFragment(Fragment f);
    }

    interface Presenter {

        void created();

        void getQuestionsFromServer();

    }
}
