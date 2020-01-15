package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.skydoves.elasticviews.ElasticButton;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Data;
import com.technohub.melifeapp.presenter.ExamPresenter;

import java.util.ArrayList;
import java.util.List;


public class ExamFragment extends Fragment implements IExam.View {

ElasticButton examBtnOption1,examBtnOption2,examBtnOption3,examBtnOption4,examBtnOption5;
TextView examTxtQuestion,examTxtQno;
SpinKitView spinExam;
    int flag=0;
    List<Data> data=new ArrayList<>();
    View v;

   ExamPresenter examPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_question_answer, container, false);
        v.setBackgroundColor(Color.WHITE);

        examPresenter = new ExamPresenter(this);
        examPresenter.created();
//        examTxtQuestion.setText(data.get(flag).getFirst_name());
//        examTxtQno.setText(data.get(flag).getId()+" ");

        return v;
    }

    @Override
    public void setQuestion() {
        examTxtQno.setText(data.get(flag).getId()+" ");
        examTxtQuestion.setText(data.get(flag).getFirst_name());
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
    public void initClicks() {
        examBtnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 examPresenter.buttonClick();
               selectAnswer();
            }
        });
        examBtnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 examPresenter.buttonClick();
                selectAnswer();
            }
        });
        examBtnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 examPresenter.buttonClick();
                selectAnswer();
            }
        });
        examBtnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 examPresenter.buttonClick();
                selectAnswer();
            }
        }); examBtnOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 examPresenter.buttonClick();
                selectAnswer();
            }
        });


    }


    void fun(Fragment f)
    {
        // Create new fragment and transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.qa_layout, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void ShowQuestionList(List<Data> data) {
        this.data=data;
       for(Data d:this.data)
       {
           Log.e("plss",d.getFirst_name());
           Log.e("plss",d.getLast_name());
           Log.e("plss",d.getEmail());
       }

    }

    @Override
    public void onResume() {
        super.onResume();

    }
    void selectAnswer()
    {
        flag++;
        Log.e("flag incre",flag+"");
        if(flag<data.size())
        {
            examTxtQno.setText(data.get(flag).getId()+" ");
            examTxtQuestion.setText(data.get(flag).getFirst_name());
            Log.e("flag",flag+"");
        }
        if(flag==data.size())


        {
            Fragment f=new SuccessFrag2();
            fun(f);
        }
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
