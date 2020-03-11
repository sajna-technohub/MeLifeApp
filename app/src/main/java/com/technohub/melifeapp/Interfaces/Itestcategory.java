package com.technohub.melifeapp.Interfaces;

import android.content.Context;

import com.technohub.melifeapp.models.ExamRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.models.Tests;
import com.technohub.melifeapp.models.User;

import java.util.List;

public interface Itestcategory {
    interface View
    {

        void init();

        void initClicks();

        void showLoading();

        void hideLoading();

        void loadTestList(TestcategoryResponse testlist);

        void loadNoQnsFragment();

        void loadReportFragment();

        void loadExamFragment(LoadQuestionResponse loadQuestionResponse,String exam_id);


    }
    interface Presenter {

        void created();

        void load(User user);

        void initiateExam();
    }

    }
