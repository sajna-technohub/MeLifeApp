package com.technohub.melifeapp.views;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.DataDATA;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.SaveAnswerResponse;
import com.technohub.melifeapp.presenter.ExamPresenter;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.github.kexanie.library.MathView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMathQns extends Fragment implements IExam.View {


    MathView examBtnOption3, examBtnOption4, examBtnOption5;
    MathView examBtnOption1, examBtnOption2;
    MathView examTxtQuestion;
    TextView examTxtQno;
    HtmlTextView htmlTextView;
    SpinKitView spinExam;
    LinearLayout linearLayout;
    int catid = 0;
    LoadQuestionResponse loadQuestionResponse;
    int flag = 0;
    DialogFragment dialogFragment;
    private static int TIME_OUT = 1000;
    List<DataDATA> data = new ArrayList<>();
    View v;
    String userid, user_email;
    String exam_id, test_id, logid;
    String s, t;
    ExamPresenter examPresenter;
    Animation animation;

    @Override
    public void goToDashboard() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_mathqns, container, false);
        v.setBackgroundColor(Color.WHITE);

        init();

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_animation);

        userid = new LoginResponse().getSharedPreferences(getContext(), "userid");
        user_email = new LoginResponse().getSharedPreferences(getContext(), "email");

        Log.e("mathSessions Profile", userid + "  " + user_email);

        examPresenter=new ExamPresenter(this,user_email,getContext());
        examPresenter.created();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        s= dateFormat.format(today);
        Log.e("mathstartdate",s);

        Bundle args = getArguments();

        if (args != null) {


            loadQuestionResponse = (LoadQuestionResponse) args.getParcelable("examResponse");
            exam_id = (String) args.getString("exam_id");

            examTxtQno.setText(loadQuestionResponse.getExamquestionData().get(flag).getQuestion_order());
            examTxtQno.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getQuestion_id()));
        }

        return v;
    }

    @Override
    public void init() {

        examBtnOption1=v.findViewById(R.id.examBtnOption1);
        examBtnOption2=v.findViewById(R.id.examBtnOption2);
        examBtnOption3=v.findViewById(R.id.examBtnOption3);
        examBtnOption4=v.findViewById(R.id.examBtnOption4);
        examBtnOption5=v.findViewById(R.id.examBtnOption5);
//        htmlTextView=v.findViewById(R.id.examTxtQuestion);
        examTxtQuestion=v.findViewById(R.id.examTxtQuestion);
        examTxtQno=v.findViewById(R.id.examTxtQno);
        spinExam=v.findViewById(R.id.examSpinKit);

    }
    String getClicktime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        t= dateFormat.format(today);
        Log.e("enddate",t);
        return t;

    }
    public void showDialog(DialogFragment dialogFragment)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, "dialog");

    }
    @Override
    public void initClicks() {

        examBtnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //I want to start animation here
                t=getClicktime();

                examBtnOption1.startAnimation(animation);
                dialogFragment=new SuccessFrag2();
                showDialog(dialogFragment);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        dialogFragment.dismiss(); // when the task active then close the dialog
                        Log.e("qid",examTxtQno.getId()+"");
                        Log.e("optid",examBtnOption1.getId()+"");
                        if(datedifference(s,t)==true)

                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");

                        else{

                        }
//                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
                    }
                }, TIME_OUT);
            }
        });

        examBtnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t=getClicktime();

                examBtnOption2.startAnimation(animation);
                dialogFragment=new SuccessFrag3();
                showDialog(dialogFragment);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        dialogFragment.dismiss(); // when the task active then close the dialog
                        Log.e("qid",examTxtQno.getId()+"");
                        Log.e("optid",examBtnOption2.getId()+"");

                        if(datedifference(s,t)==true)

                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");

                        else
                        {

                        }
//                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
                    }
                }, TIME_OUT);
            }
        });
        examBtnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t=getClicktime();

                examBtnOption3.startAnimation(animation);
                dialogFragment=new SuccessFrag2();
                showDialog(dialogFragment);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        dialogFragment.dismiss(); // when the task active then close the dialog
                        Log.e("qid",examTxtQno.getId()+"");
                        Log.e("optid",examBtnOption3.getId()+"");

                        if(datedifference(s,t)==true)

                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");

                        else
                        {

                        }
//                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");

                    }
                }, TIME_OUT);
            }
        });
        examBtnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t=getClicktime();

                examBtnOption4.startAnimation(animation);
                dialogFragment=new SuccessFrag2();
                showDialog(dialogFragment);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        dialogFragment.dismiss(); // when the task active then close the dialog
                        Log.e("qid",examTxtQno.getId()+"");
                        Log.e("optid",examBtnOption4.getId()+"");

                        if(datedifference(s,t)==true)
                        {
                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
                        }
                        else
                        {

                        }
//                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
                    }
                }, TIME_OUT);

            }
        });

        examBtnOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t=getClicktime();

                examBtnOption5.startAnimation(animation);
                dialogFragment=new SuccessFrag3();
                showDialog(dialogFragment);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        dialogFragment.dismiss(); // when the task active then close the dialog
                        Log.e("qid",examTxtQno.getId()+"");
                        Log.e("optid",examBtnOption5.getId()+"");

                        if(datedifference(s,t)==true)
                        {
                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
                        }
                        else
                        {

                        }
//                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");

                    }
                }, TIME_OUT);

            }
        });


    }
    @Override
    public void selectAnswer()
    {
        
    }
    boolean datedifference(String s,String t)
    {
        Log.e("start",s);
        Log.e("end",t);
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(s);
            d2 = format.parse(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000;
        Log.e("Time in seconds: " , diffSeconds + " seconds.");
        if(diffSeconds<5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void showAlert(String qid,String optid){

        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Save Answer Alert")
                .setContentText("Would you like to continue? ")
                .setConfirmText("Ok!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                             @Override
                                             public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                                 examPresenter.saveanswerbeforeidealtime(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",qid,optid);
                                                 sweetAlertDialog.dismissWithAnimation();
                                             }
                                         }
                )
                .show();
    }
    @Override
    public void showNextQuestion(SaveAnswerResponse saveAnswerResponse) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        s= dateFormat.format(today);
        Log.e("save startdate",s);

        examTxtQno.setText(Html.fromHtml(saveAnswerResponse.getRecord().getQn_dts().getQuestion_order()));

        examTxtQno.setId(Integer.parseInt(saveAnswerResponse.getRecord().getQn_dts().getQuestion_id()));

        if(saveAnswerResponse.getRecord().getEqStatusQns().equals("1") || saveAnswerResponse.getRecord().getEqStatusOpts().equals("1"))
        {
            Log.e("image","has image");
//            ImageFragment imageFragment=new ImageFragment();
//            Bundle bundle = new Bundle();
//            FragmentTransaction transaction;
//            bundle.putParcelable("examResponse", loadQuestionResponse);
//            bundle.putString("exam_id",exam_id);
//            imageFragment.setArguments(bundle);
//            transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.testcatlayout, imageFragment);
        }
        if(saveAnswerResponse.getRecord().getImgStatusQns().equals("1") || saveAnswerResponse.getRecord().getImgStatusOpts().equals("1"))
        {

            examTxtQuestion.setText(saveAnswerResponse.getRecord().getQn_dts().getQuestion());
            if (saveAnswerResponse.getRecord().getOpt_count().equals("2")) {

                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());

                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));
                Log.e("opt1", examBtnOption1.getId() + "");

                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp());
                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");

                examBtnOption3.setVisibility(View.GONE);
                examBtnOption4.setVisibility(View.GONE);
                examBtnOption5.setVisibility(View.GONE);
            }
            else if (saveAnswerResponse.getRecord().getOpt_count().equals("3")) {
                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());

                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));                Log.e("opt1", examBtnOption1.getId() + "");

                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp());
                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");

                examBtnOption3.setText(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp());
                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));

                Log.e("opt3", examBtnOption3.getId() + "");
                examBtnOption4.setVisibility(View.GONE);
                examBtnOption5.setVisibility(View.GONE);
            }
            else if (saveAnswerResponse.getRecord().getOpt_count().equals("4")) {
                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());

                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));                Log.e("opt1", examBtnOption1.getId() + "");

                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp());
                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");

                examBtnOption3.setText(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp());
                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));                Log.e("opt3", examBtnOption3.getId() + "");

                examBtnOption4.setText(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_descp());
                examBtnOption4.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_id()));                Log.e("opt4", examBtnOption4.getId() + "");

                examBtnOption5.setVisibility(View.GONE);
            }
            else if (loadQuestionResponse.getNo_of_options_current_questions().equals("5")) {
                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());

                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));                Log.e("opt1", examBtnOption1.getId() + "");

                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp());
                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");

                examBtnOption3.setText(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp());
                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));                Log.e("opt3", examBtnOption3.getId() + "");

                examBtnOption4.setText(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_descp());
                examBtnOption4.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_id()));                Log.e("opt4", examBtnOption4.getId() + "");

                examBtnOption5.setText(saveAnswerResponse.getRecord().getOpt_dts().get(4).getOption_descp());
                examBtnOption5.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(4).getOption_id()));                Log.e("opt5", examBtnOption5.getId() + "");
            }


        }



    }
    @Override
    public  void LoadFragment(Fragment f)
    {
        // Create new fragment and transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.qa_layout, f);
        transaction.addToBackStack(null);
        transaction.commit();
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
}
