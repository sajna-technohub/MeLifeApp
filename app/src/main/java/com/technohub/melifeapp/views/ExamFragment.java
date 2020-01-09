package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.skydoves.elasticviews.ElasticButton;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.presenter.ExamPresenter;
import com.technohub.melifeapp.presenter.LoginPresenter;


public class ExamFragment extends Fragment implements IExam.View {

ElasticButton examBtnOption1,examBtnOption2,examBtnOption3,examBtnOption4,examBtnOption5;
TextView examTxtQuestion,examTxtQno;
SpinKitView spinExam;

    View v;
   ExamPresenter examPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_question_answer, container, false);
        v.setBackgroundColor(Color.WHITE);

        examPresenter = new ExamPresenter(this);
        examPresenter.getQuestionsFromServer();
        examPresenter.created();

        return v;
    }

    @Override
    public void init() {

        examBtnOption1=v.findViewById(R.id.examBtnOption1);
        examBtnOption2=v.findViewById(R.id.examBtnOption2);
        examBtnOption3=v.findViewById(R.id.examBtnOption3);
        examBtnOption4=v.findViewById(R.id.examBtnOption4);
        examBtnOption5=v.findViewById(R.id.examBtnOption5);

        examTxtQuestion=v.findViewById(R.id.examTxtQuestion);
        examTxtQno=v.findViewById(R.id.examTxtQno);
        spinExam=v.findViewById(R.id.examSpinKit);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void showLoading() {
        spinExam.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        spinExam.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void goToNextFragment() {

    }

    @Override
    public void initClicks() {

    }
    // TODO: Rename and change types and number of parameters
    public static ExamFragment newInstance(String param1, String param2) {
        ExamFragment fragment = new ExamFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

}
