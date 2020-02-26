package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.skydoves.elasticviews.ElasticButton;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Data;
import com.technohub.melifeapp.classes.HtmlImgConverter;
import com.technohub.melifeapp.presenter.ExamPresenter;
import com.technohub.melifeapp.views.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


public class ExamFragment extends Fragment implements IExam.View {

ElasticButton examBtnOption1,examBtnOption2,examBtnOption3,examBtnOption4,examBtnOption5;
TextView examTxtQuestion,examTxtQno;
SpinKitView spinExam;
     int flag=0;
    DialogFragment dialogFragment;
    private static int TIME_OUT = 1000;
    List<Data> data=new ArrayList<>();
    View v;
    String htmlText = "<h2>What is Android?</h2>\n" +
            "<p>Android is an open source and Linux-based <b>Operating System</b> for mobile devices such as smartphones and tablet computers.Android was developed by the <i>Open Handset Alliance</i>, led by Google, and other companies.</p>\n" +
            "<p>Android offers a unified approach to application development for mobile devices which means developers need onlydevelop for Android, and their applications should be able to run on different devices powered by Android.</p>\n" +
            "<p>The first beta version of the Android Software Development Kit (SDK) was released by Google in 2007 whereasthe first commercial version, Android 1.0, was released in September 2008.</p><img src='https://homepages.cae.wisc.edu/~ece533/images/airplane.png'>";
String urlimg="https://www.animatedimages.org/data/media/1574/animated-success-image-0013.gif";
   ExamPresenter examPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_question_answer, container, false);
        v.setBackgroundColor(Color.WHITE);

        examPresenter = new ExamPresenter(this,htmlText);
        examPresenter.created();

//        examTxtQuestion.setText(data.get(flag).getFirst_name());
//        examTxtQno.setText(data.get(flag).getId()+" ");

        return v;
    }

    @Override
    public void setQuestion()
    {

        examTxtQno.setText(data.get(flag).getId()+" ");
//        examTxtQuestion.setText(data.get(flag).getFirst_name());
        examTxtQuestion.setText(Html.fromHtml(data.get(flag).getFirst_name(),
                new Html.ImageGetter() {

                    @Override
                    public Drawable getDrawable(String source) {

                        Toast.makeText(getContext(), source,
                                Toast.LENGTH_LONG).show();
                                Log.e("image",source);
                        HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
                                examTxtQuestion, data.get(flag).getFirst_name(),getContext());
                        httpGetDrawableTask.execute(source);

                        return null;
                    }

                }, null));

        examTxtQuestion.setMovementMethod(LinkMovementMethod.getInstance());
        examBtnOption1.setText(data.get(flag).getOption1());
        examBtnOption2.setText(data.get(flag).getOption2());
        examBtnOption3.setText(data.get(flag).getOption3());
        examBtnOption4.setText(data.get(flag).getOption4());
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
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_animation);
                //I want to start animation here
                examBtnOption1.startAnimation(animation);
                dialog();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        dialogFragment.dismiss(); // when the task active then close the dialog
                        selectAnswer();
                    }
                }, TIME_OUT);
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
           Log.e("plss",""+d.getId());

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
            spinExam.setVisibility(View.VISIBLE);
            examTxtQno.setText(data.get(flag).getId()+" ");
            examTxtQuestion.setText(Html.fromHtml(data.get(flag).getFirst_name(),
                    new Html.ImageGetter() {

                        @Override
                        public Drawable getDrawable(String source) {

                            Toast.makeText(getContext(), source,
                                    Toast.LENGTH_LONG).show();
                            Log.e("image",source);
                            HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
                                    examTxtQuestion, data.get(flag).getFirst_name(),getContext());
                            httpGetDrawableTask.execute(source);

                            return null;
                        }

                    }, null));

            examTxtQuestion.setMovementMethod(LinkMovementMethod.getInstance());
            spinExam.setVisibility(View.GONE);
            examBtnOption1.setText(data.get(flag).getOption1());
            examBtnOption2.setText(data.get(flag).getOption2());
            examBtnOption3.setText(data.get(flag).getOption3());
            examBtnOption4.setText(data.get(flag).getOption4());
            Log.e("flag",flag+""+data.size());
        }
        if(data.size()==flag)
        {
            Log.e("size","Reaches size"+data.size());
            Fragment f=new HomeFragment();
            fun(f);
        }
    }
    void dialog()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment = new SuccessFrag2();
        dialogFragment.show(ft, "dialog");
    }
    void dialog2()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment = new SuccessFrag1();
        dialogFragment.show(ft, "dialog");
    }
    void dialog3()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment = new SuccessFrag2();
        dialogFragment.show(ft, "dialog");
    }
    void dialog4()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment = new SuccessFrag4();
        dialogFragment.show(ft, "dialog");
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
