package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.SaveAnswerRequest;
import com.technohub.melifeapp.models.SaveAnswerResponse;


public interface IExam {

    interface View {

        void init();

        void initClicks();

        Context getContext();

        void showLoading();

        void hideLoading();

        void goToNextFragment();


        void showDialog(DialogFragment fragment);

        void  selectAnswer();

        void LoadFragment(Fragment f);

        void showNextQuestion(SaveAnswerResponse saveAnswerResponse);

        void goToDashboard();


    }

    interface Presenter {

        void created();

        void submitexam(String examid,String userid,String email);

        void saveAnswer(String qnorder,String exam_id,String test_id,String user_id ,String logid,String DeviceType,String DeviceToken,String quesid,String optid ,String totqns);

        void saveanswerbeforeidealtime(String qnorder,String exam_id,String test_id,String user_id ,String logid,String DeviceType,String DeviceToken,String quesid,String optid,String totqns );

    }
}
