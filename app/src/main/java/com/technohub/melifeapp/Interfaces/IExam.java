package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.technohub.melifeapp.models.LoadQuestionResponse;


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


    }

    interface Presenter {

        void created();

        void saveAnswer(String exam_id,String test_id,String user_id,String user_email,String logid,String DeviceType,String DeviceToken,String record );

        void saveanswerbeforeidealtime();

    }
}
