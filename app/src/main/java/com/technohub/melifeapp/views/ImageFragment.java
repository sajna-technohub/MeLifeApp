//package com.technohub.melifeapp.views;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.Lifecycle;
//
//import android.os.Handler;
//import android.text.Html;
//import android.text.method.LinkMovementMethod;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.github.ybq.android.spinkit.SpinKitView;
//import com.technohub.melifeapp.Interfaces.IExam;
//import com.technohub.melifeapp.R;
//import com.technohub.melifeapp.classes.DataDATA;
//import com.technohub.melifeapp.classes.HtmlImgConverter;
//import com.technohub.melifeapp.models.LoadQuestionResponse;
//import com.technohub.melifeapp.models.LoginResponse;
//import com.technohub.melifeapp.models.SaveAnswerResponse;
//import com.technohub.melifeapp.presenter.ExamPresenter;
//import com.technohub.melifeapp.views.ui.home.HomeFragment;
//
//import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
//import org.sufficientlysecure.htmltextview.HtmlTextView;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//import io.github.kexanie.library.MathView;
//
//
//public class ImageFragment extends Fragment implements IExam.View {
//
//    MathView examBtnOption3,examBtnOption4,examBtnOption5;
//    MathView examBtnOption1,examBtnOption2;
//    MathView examTxtQuestion;
//    TextView examTxtQno;
//    HtmlTextView htmlTextView;
//    SpinKitView spinExam;
//    LinearLayout linearLayout;
//    int catid=0;
//    SaveAnswerResponse loadQuestionResponse;
//    int flag=0;
//    DialogFragment dialogFragment;
//    private static int TIME_OUT = 1000;
//    List<DataDATA> data=new ArrayList<>();
//    View v;
//    String userid,user_email;
//    String exam_id,test_id,logid;
//    String s,t;
//    //         String htmlText = "Choose the image from the figure and answer the question <img src='https://homepages.cae.wisc.edu/~ece533/images/airplane.png'>";
////         String urlimg="https://www.animatedimages.org/data/media/1574/animated-success-image-0013.gif";
//    ExamPresenter examPresenter;
//    Animation animation ;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        v=inflater.inflate(R.layout.fragment_exam, container, false);
//        v.setBackgroundColor(Color.WHITE);
//
//        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_animation);
//
//        userid = new LoginResponse().getSharedPreferences(getContext(), "userid");
//        user_email = new LoginResponse().getSharedPreferences(getContext(), "email");
//
//        Log.e("Sessions Profile", userid + "  " + user_email);
//
//        examPresenter=new ExamPresenter(this,user_email);
//        examPresenter.created();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//        Date today = Calendar.getInstance().getTime();
//        s= dateFormat.format(today);
//        Log.e("startdate",s);
//
//
//        Bundle args = getArguments();
//
//        if (args != null) {
//
//
//            loadQuestionResponse = (SaveAnswerResponse) args.getParcelable("examResponse");
//            exam_id = (String) args.getString("exam_id");
//
//            Log.e("examid in examfrag", exam_id);
//
//            examTxtQno.setText(loadQuestionResponse.getRecord().getQn_dts().getQuestion_order());
//            examTxtQno.setId(Integer.parseInt(loadQuestionResponse.getRecord().getQn_dts().getQuestion_id()));
//            //load exam
//
//            //                    htmlTextView.setText("<h2>Hello wold</h2><img src=\"https://homepages.cae.wisc.edu/~ece533/images/fruits.png\"/>",
//            //                 new HtmlHttpImageGetter(htmlTextView));
//            if (loadQuestionResponse.getRecord().getEqStatusQns().equals("1") || loadQuestionResponse.getRecord().getEqStatusOpts().equals("1"))
//            {
//
//                Log.e("imgstatus","found");
////                ImageFragment imageFragment=new ImageFragment();
////                Bundle bundle=new Bundle();
////                bundle.putParcelable("examResponse", loadQuestionResponse);
////                bundle.putString("exam_id",exam_id);
////                imageFragment.setArguments(bundle);
////                FragmentTransaction transaction = getFragmentManager().beginTransaction();
////                transaction.replace(R.id.qa_layout, imageFragment);
////                transaction.addToBackStack(null);
////                transaction.commit();
//
//                examTxtQuestion.setText(loadQuestionResponse.getRecord().getQn_dts().getQuestion());
//
//
//
////
////
////                if (loadQuestionResponse.getRecord().getOpt_count().equals("2")) {
////
////                    examBtnOption1.setText(loadQuestionResponse.getRecord().getOpt_dts().get(0).getOption_descp());
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setVisibility(View.GONE);
////                    examBtnOption4.setVisibility(View.GONE);
////                    examBtnOption5.setVisibility(View.GONE);
////                }
////                else if (loadQuestionResponse.getNo_of_options_current_questions().equals("3")) {
////                    examBtnOption1.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp(),new HtmlHttpImageGetter(examBtnOption3));
////                    examBtnOption3.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_id()));
////                    Log.e("opt3", examBtnOption3.getId() + "");
////                    examBtnOption4.setVisibility(View.GONE);
////                    examBtnOption5.setVisibility(View.GONE);
////                }
////                else if (loadQuestionResponse.getNo_of_options_current_questions().equals("4")) {
////                    examBtnOption1.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp(),new HtmlHttpImageGetter(examBtnOption3));
////                    examBtnOption3.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_id()));
////                    Log.e("opt3", examBtnOption3.getId() + "");
////
////                    examBtnOption4.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_descp(),new HtmlHttpImageGetter(examBtnOption4));
////                    examBtnOption4.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_id()));
////                    Log.e("opt4", examBtnOption4.getId() + "");
////
////                    examBtnOption5.setVisibility(View.GONE);
////                }
////                else if (loadQuestionResponse.getNo_of_options_current_questions().equals("5")) {
////                    examBtnOption1.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp(),new HtmlHttpImageGetter(examBtnOption3));
////                    examBtnOption3.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_id()));
////                    Log.e("opt3", examBtnOption3.getId() + "");
////
////                    examBtnOption4.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_descp(),new HtmlHttpImageGetter(examBtnOption4));
////                    examBtnOption4.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_id()));
////                    Log.e("opt4", examBtnOption4.getId() + "");
////
////                    examBtnOption5.setText("http://3.7.48.112/"+loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(4).getOption_descp(),new HtmlHttpImageGetter(examBtnOption5));
////                    examBtnOption5.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(4).getOption_id()));
////                    Log.e("opt5", examBtnOption5.getId() + "");
////                }
////            }
////            else  if (loadQuestionResponse.getImgStatusQns().equals("2") || loadQuestionResponse.getImgStatusOpts().equals("2"))
////            {
////
////            }
////            else {
////
////                examTxtQuestion.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getQuestion()));
////
////                Log.e("no_options", loadQuestionResponse.getNo_of_options_current_questions());
////
////                if (loadQuestionResponse.getNo_of_options_current_questions().equals("2")) {
////
////                    examBtnOption1.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp()));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp()));
////
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setVisibility(View.GONE);
////                    examBtnOption4.setVisibility(View.GONE);
////                    examBtnOption5.setVisibility(View.GONE);
////                } else if (loadQuestionResponse.getNo_of_options_current_questions().equals("3")) {
////
////                    examBtnOption1.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp()));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp()));
////
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp()));
////
////                    examBtnOption3.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_id()));
////                    Log.e("opt3", examBtnOption3.getId() + "");
////
////                    examBtnOption4.setVisibility(View.GONE);
////                    examBtnOption5.setVisibility(View.GONE);
////                } else if (loadQuestionResponse.getNo_of_options_current_questions().equals("4")) {
////
////                    examBtnOption1.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp()));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp()));
////
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp()));
////
////                    examBtnOption3.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_id()));
////                    Log.e("opt3", examBtnOption3.getId() + "");
////
////                    examBtnOption4.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_descp()));
////
////                    examBtnOption4.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_id()));
////                    Log.e("opt4", examBtnOption4.getId() + "");
////
////                    examBtnOption5.setVisibility(View.GONE);
////                } else {
////                    Log.e("imgstatus","img Not found");
////                    examBtnOption1.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp()));
////
////                    examBtnOption1.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_id()));
////                    Log.e("opt1", examBtnOption1.getId() + "");
////
////                    examBtnOption2.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp()));
////
////                    examBtnOption2.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_id()));
////                    Log.e("opt2", examBtnOption2.getId() + "");
////
////                    examBtnOption3.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp()));
////
////                    examBtnOption3.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_id()));
////                    Log.e("opt3", examBtnOption3.getId() + "");
////
////                    examBtnOption4.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_descp()));
////
////                    examBtnOption4.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_id()));
////                    Log.e("opt4", examBtnOption4.getId() + "");
////
////                    examBtnOption5.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(4).getOption_descp()));
////
////                    examBtnOption5.setId(Integer.parseInt(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(4).getOption_id()));
////                    Log.e("opt5", examBtnOption5.getId() + "");
//                }
//            }
//
//
////        }
//        return v;
//    }
//
//
//    @Override
//    public void init() {
//
//        examBtnOption1=v.findViewById(R.id.examBtnOption1);
//        examBtnOption2=v.findViewById(R.id.examBtnOption2);
//        examBtnOption3=v.findViewById(R.id.examBtnOption3);
//        examBtnOption4=v.findViewById(R.id.examBtnOption4);
//        examBtnOption5=v.findViewById(R.id.examBtnOption5);
////        htmlTextView=v.findViewById(R.id.examTxtQuestion);
//        examTxtQuestion=v.findViewById(R.id.examTxtQuestion);
//        examTxtQno=v.findViewById(R.id.examTxtQno);
//        spinExam=v.findViewById(R.id.examSpinKit);
//
//
//    }
//    boolean datedifference(String s,String t)
//    {
//        Log.e("start",s);
//        Log.e("end",t);
//        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//        Date d1 = null;
//        Date d2 = null;
//        try {
//            d1 = format.parse(s);
//            d2 = format.parse(t);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long diff = d2.getTime() - d1.getTime();
//        long diffSeconds = diff / 1000;
//        Log.e("Time in seconds: " , diffSeconds + " seconds.");
//        if(diffSeconds<5)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    void showAlert(String qid,String optid){
//
//        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
//                .setTitleText("Save Answer Alert")
//                .setContentText("Would you like to continue? ")
//                .setConfirmText("Ok!")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                             @Override
//                                             public void onClick(SweetAlertDialog sweetAlertDialog) {
////                                                 examPresenter.saveanswerbeforeidealtime(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",qid,optid);
////                                                 sweetAlertDialog.dismissWithAnimation();
//                                             }
//                                         }
//                )
//                .show();
//    }
//
//    String getClicktime()
//    {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//        Date today = Calendar.getInstance().getTime();
//        t= dateFormat.format(today);
//        Log.e("enddate",t);
//        return t;
//
//    }
//    @Override
//    public void initClicks() {
//
//        examBtnOption1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //I want to start animation here
//                t=getClicktime();
//
//                examBtnOption1.startAnimation(animation);
//                dialogFragment=new SuccessFrag2();
//                showDialog(dialogFragment);
//
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        dialogFragment.dismiss(); // when the task active then close the dialog
//                        Log.e("qid",examTxtQno.getId()+"");
//                        Log.e("optid",examBtnOption1.getId()+"");
//                        if(datedifference(s,t)==true)
//
//                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
//
//                        else
////                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
//                    }
//                }, TIME_OUT);
//            }
//        });
//
//        examBtnOption2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                t=getClicktime();
//
//                examBtnOption2.startAnimation(animation);
//                dialogFragment=new SuccessFrag3();
//                showDialog(dialogFragment);
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        dialogFragment.dismiss(); // when the task active then close the dialog
//                        Log.e("qid",examTxtQno.getId()+"");
//                        Log.e("optid",examBtnOption2.getId()+"");
//
//                        if(datedifference(s,t)==true)
//
//                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
//
////                        else
////                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
//                    }
//                }, TIME_OUT);
//            }
//        });
//        examBtnOption3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                t=getClicktime();
//
//                examBtnOption3.startAnimation(animation);
//                dialogFragment=new SuccessFrag2();
//                showDialog(dialogFragment);
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        dialogFragment.dismiss(); // when the task active then close the dialog
//                        Log.e("qid",examTxtQno.getId()+"");
//                        Log.e("optid",examBtnOption3.getId()+"");
//
//                        if(datedifference(s,t)==true)
//
//                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
//
////                        else
////                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
//
//                    }
//                }, TIME_OUT);
//            }
//        });
//        examBtnOption4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                t=getClicktime();
//
//                examBtnOption4.startAnimation(animation);
//                dialogFragment=new SuccessFrag2();
//                showDialog(dialogFragment);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        dialogFragment.dismiss(); // when the task active then close the dialog
//                        Log.e("qid",examTxtQno.getId()+"");
//                        Log.e("optid",examBtnOption4.getId()+"");
//
//                        if(datedifference(s,t)==true)
//                        {
//                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
//                        }
////                        else
////                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
//                    }
//                }, TIME_OUT);
//
//            }
//        });
//
//        examBtnOption5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                t=getClicktime();
//
//                examBtnOption5.startAnimation(animation);
//                dialogFragment=new SuccessFrag3();
//                showDialog(dialogFragment);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        dialogFragment.dismiss(); // when the task active then close the dialog
//                        Log.e("qid",examTxtQno.getId()+"");
//                        Log.e("optid",examBtnOption5.getId()+"");
//
//                        if(datedifference(s,t)==true)
//                        {
//                            showAlert(examTxtQno.getId()+"",examBtnOption1.getId()+"");
//                        }
////                        else
////                            examPresenter.saveAnswer(exam_id,loadQuestionResponse.getTest_id(),userid,"","1","ydtdt",examTxtQno.getId()+"",examBtnOption1.getId()+"");
//
//                    }
//                }, TIME_OUT);
//
//            }
//        });
//
//
//    }
//    @Override
//    public  void goToDashboard()
//    {
//        new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText("Alert")
//                .setContentText("Confirm Exam submit?")
//                .setConfirmText("Ok!")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        Intent intent = new Intent(getContext(), DashBoardActivity.class);
//                        startActivity(intent);
//                        getActivity().finish();
//                        sDialog.dismissWithAnimation();
//
//                    }
//                })
//                .show();
//
//    }
//    @Override
//    public void showNextQuestion(SaveAnswerResponse saveAnswerResponse) {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//        Date today = Calendar.getInstance().getTime();
//        s= dateFormat.format(today);
//        Log.e("save startdate",s);
//
//        examTxtQno.setText(Html.fromHtml(saveAnswerResponse.getRecord().getQn_dts().getQuestion_order()));
//
//        examTxtQno.setId(Integer.parseInt(saveAnswerResponse.getRecord().getQn_dts().getQuestion_id()));
//
//
//        if(saveAnswerResponse.getRecord().getImgStatusQns().equals("1") || saveAnswerResponse.getRecord().getImgStatusQns().equals("1"))
//        {
//
//            examTxtQuestion.setText(saveAnswerResponse.getRecord().getQn_dts().getQuestion(), new HtmlHttpImageGetter(examTxtQuestion));
//            if (saveAnswerResponse.getRecord().getOpt_count().equals("2")) {
//
//                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
//
//                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));
//                Log.e("opt1", examBtnOption1.getId() + "");
//
//                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
//                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");
//
//                examBtnOption3.setVisibility(View.GONE);
//                examBtnOption4.setVisibility(View.GONE);
//                examBtnOption5.setVisibility(View.GONE);
//            }
//            else if (saveAnswerResponse.getRecord().getOpt_count().equals("3")) {
//                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
//
//                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));                Log.e("opt1", examBtnOption1.getId() + "");
//
//                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
//                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");
//
//                examBtnOption3.setText(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp(),new HtmlHttpImageGetter(examBtnOption3));
//                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));
//
//                Log.e("opt3", examBtnOption3.getId() + "");
//                examBtnOption4.setVisibility(View.GONE);
//                examBtnOption5.setVisibility(View.GONE);
//            }
//            else if (saveAnswerResponse.getRecord().getOpt_count().equals("4")) {
//                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
//
//                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));                Log.e("opt1", examBtnOption1.getId() + "");
//
//                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
//                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");
//
//                examBtnOption3.setText(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp(),new HtmlHttpImageGetter(examBtnOption3));
//                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));                Log.e("opt3", examBtnOption3.getId() + "");
//
//                examBtnOption4.setText(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_descp(),new HtmlHttpImageGetter(examBtnOption4));
//                examBtnOption4.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_id()));                Log.e("opt4", examBtnOption4.getId() + "");
//
//                examBtnOption5.setVisibility(View.GONE);
//            }
////            else if (loadQuestionResponse.getNo_of_options_current_questions().equals("5")) {
////                examBtnOption1.setText(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp(),new HtmlHttpImageGetter(examBtnOption1));
////
////                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));                Log.e("opt1", examBtnOption1.getId() + "");
////
////                examBtnOption2.setText(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp(),new HtmlHttpImageGetter(examBtnOption2));
////                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));                Log.e("opt2", examBtnOption2.getId() + "");
////
////                examBtnOption3.setText(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp(),new HtmlHttpImageGetter(examBtnOption3));
////                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));                Log.e("opt3", examBtnOption3.getId() + "");
////
////                examBtnOption4.setText(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_descp(),new HtmlHttpImageGetter(examBtnOption4));
////                examBtnOption4.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_id()));                Log.e("opt4", examBtnOption4.getId() + "");
////
////                examBtnOption5.setText(saveAnswerResponse.getRecord().getOpt_dts().get(4).getOption_descp(),new HtmlHttpImageGetter(examBtnOption5));
////                examBtnOption5.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(4).getOption_id()));                Log.e("opt5", examBtnOption5.getId() + "");
////            }
////
////
////        }
////        else {
////            examTxtQuestion.setText(Html.fromHtml(saveAnswerResponse.getRecord().getQn_dts().getQuestion()));
////            if (saveAnswerResponse.getRecord().getOpt_count().equals("2")) {
////                examBtnOption1.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp()));
////
////                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));
////
////                examBtnOption2.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp()));
////
////                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));
////
////            } else if (saveAnswerResponse.getRecord().getOpt_count().equals("3")) {
////
////                examBtnOption1.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp()));
////
////                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));
////
////                examBtnOption2.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp()));
////
////                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));
////
////                examBtnOption3.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp()));
////
////                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));
////            } else if (saveAnswerResponse.getRecord().getOpt_count().equals("4")) {
////                examBtnOption1.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp()));
////                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));
////
////                examBtnOption2.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp()));
////                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));
////
////                examBtnOption3.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp()));
////                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));
////
////                examBtnOption4.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_descp()));
////                examBtnOption4.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_id()));
////
////            } else {
////                examBtnOption1.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp()));
////                examBtnOption1.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id()));
////
////                examBtnOption2.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_descp()));
////                examBtnOption2.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(1).getOption_id()));
////
////                examBtnOption3.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_descp()));
////                examBtnOption3.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(2).getOption_id()));
////
////                examBtnOption4.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_descp()));
////                examBtnOption4.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(3).getOption_id()));
////
////                examBtnOption5.setText(Html.fromHtml(saveAnswerResponse.getRecord().getOpt_dts().get(4).getOption_descp()));
////                examBtnOption5.setId(Integer.parseInt(saveAnswerResponse.getRecord().getOpt_dts().get(4).getOption_id()));
////            }
////        }
////
//    }
////
////    @Override
////    public  void LoadFragment(Fragment f)
////    {
////        // Create new fragment and transaction
////        FragmentTransaction transaction = getFragmentManager().beginTransaction();
////        transaction.replace(R.id.qa_layout, f);
////        transaction.addToBackStack(null);
////        transaction.commit();
////    }
////
////
////    @Override
////    public void onResume() {
////        super.onResume();
////
////    }
////    @Override
////    public void selectAnswer()
////    {
////        flag++;
////        Log.e("flag incre",flag+"");
////        if(flag<data.size())
////        {
////            showLoading();
////            examTxtQno.setText(data.get(flag).getId()+" ");
////            examTxtQuestion.setText(Html.fromHtml(data.get(flag).getFirst_name(),
////                    source -> {
////
////                        Toast.makeText(getContext(), source,
////                                Toast.LENGTH_LONG).show();
////                        Log.e("image",source);
////
////                        hideLoading();
////
////                        HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
////                                examTxtQuestion, data.get(flag).getFirst_name(),getContext());
////                        httpGetDrawableTask.execute(source);
////
////                        return null;
////                    }, null));
////
////            examTxtQuestion.setMovementMethod(LinkMovementMethod.getInstance());
////            examBtnOption1.setText(data.get(flag).getOption1());
////            examBtnOption2.setText(data.get(flag).getOption2());
////            examBtnOption3.setText(data.get(flag).getOption3());
////            examBtnOption4.setText(data.get(flag).getOption4());
////            Log.e("flag",flag+""+data.size());
////        }
////        if(data.size()==flag)
////        {
////            Log.e("size","Reaches size"+data.size());
////            Fragment f=new HomeFragment();
////            LoadFragment(f);
////        }
////    }
////
////    @Override
////    public void showDialog(DialogFragment dialogFragment)
////    {
////
////        FragmentTransaction ft = getFragmentManager().beginTransaction();
////        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
////        if (prev != null) {
////            ft.remove(prev);
////        }
////        ft.addToBackStack(null);
////        dialogFragment.show(ft, "dialog");
////
////    }
////
////    @Override
////    public void showLoading() {
////        spinExam.setVisibility(View.VISIBLE);
////    }
////
////    @Override
////    public void hideLoading() {
////        spinExam.setVisibility(View.GONE);
////    }
////
////    @Nullable
////    @Override
////    public Context getContext() {
////        return getActivity();
////    }
////
////    @Override
////    public void goToNextFragment() {
////
////    }
////
////
////    @Override
////    public void onCreate(Bundle savedInstanceState)
////    {
////        super.onCreate(savedInstanceState);
////
////    }
//
//}
