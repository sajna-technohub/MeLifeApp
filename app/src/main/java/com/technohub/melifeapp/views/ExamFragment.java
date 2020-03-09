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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.skydoves.elasticviews.ElasticButton;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.DataDATA;
import com.technohub.melifeapp.classes.HtmlImgConverter;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.presenter.ExamPresenter;
import com.technohub.melifeapp.views.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


public class ExamFragment extends Fragment implements IExam.View {

TextView examBtnOption3,examBtnOption4,examBtnOption5;
TextView examBtnOption1,examBtnOption2;
TextView examTxtQuestion,examTxtQno;
SpinKitView spinExam;
LinearLayout linearLayout;
int catid=0;
    LoadQuestionResponse loadQuestionResponse;
     int flag=0;
    DialogFragment dialogFragment;
    private static int TIME_OUT = 1000;
    List<DataDATA> data=new ArrayList<>();
    View v;
    String userid,user_email;
    String exam_id,test_id,logid;
         String htmlText = "Choose the image from the figure and answer the question <img src='https://homepages.cae.wisc.edu/~ece533/images/airplane.png'>";
         String urlimg="https://www.animatedimages.org/data/media/1574/animated-success-image-0013.gif";
         ExamPresenter examPresenter;
         Animation animation ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_exam, container, false);
        v.setBackgroundColor(Color.WHITE);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_animation);


        examPresenter=new ExamPresenter(this);
        examPresenter.created();

        Bundle args = getArguments();
        if (args != null)
        {
//             exam_id=args.getParcelable("examResponse");
             loadQuestionResponse = (LoadQuestionResponse) args.getParcelable("examResponse");
                  String c= loadQuestionResponse.getExamquestionData().get(flag).getQuestion_id();
                  Log.e("obj",c);
            examTxtQno.setText(c);
            examTxtQuestion.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getQuestion()));
            examBtnOption1.setText(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(0).getOption_descp());
            examBtnOption2.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(1).getOption_descp()));
            examBtnOption3.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(2).getOption_descp()));
            examBtnOption4.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(3).getOption_descp()));
            examBtnOption5.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(flag).getOptions().get(4).getOption_descp()));
        }
        userid=new LoginResponse().getSharedPreferences(getContext(),"userid");
        user_email=new LoginResponse().getSharedPreferences(getContext(),"email");

        Log.e("Sessions Profile",userid+"  "+user_email);


        return v;
    }

    @Override
    public void setQuestion(LoadQuestionResponse loadQuestionResponse)
    {





//        examTxtQuestion.setText(Html.fromHtml(data.get(flag).getFirst_name(),
//                source -> {
//
//                    Toast.makeText(getContext(), source,
//                            Toast.LENGTH_LONG).show();
//                            Log.e("image",source);
//                    HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
//                            examTxtQuestion, data.get(flag).getFirst_name(),getContext());
//                    httpGetDrawableTask.execute(source);
//                    return null;
//                }, null));
//
//        examTxtQuestion.setMovementMethod(LinkMovementMethod.getInstance());





//        Log.e("img",data.get(flag).getOption1());
//        String img=data.get(flag).getOption1();
//        boolean isFound = img.contains("img");
//        Log.e("imgg",img);
//        if (isFound)
//        {
//
//            Log.e("img","found");
//            Log.e("img",img);
//        }
//       if(img.indexOf("img") !=-1? true: false) //true
//        {
//            Log.e("img","it contains img");
//            examBtnOption1.setText(Html.fromHtml(data.get(flag).getOption1(),
//                    new Html.ImageGetter() {
//
//                        @Override
//                        public Drawable getDrawable(String source) {
//
//                            Toast.makeText(getContext(), source,
//                                    Toast.LENGTH_LONG).show();
//                            Log.e("image",source);
//                            HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
//                                    examBtnOption1, data.get(flag).getOption1(),getContext());
//                            httpGetDrawableTask.execute(source);
//
//                            return null;
//                        }
//
//                    }, null));
//
//            examBtnOption1.setMovementMethod(LinkMovementMethod.getInstance());
//
//            examBtnOption2.setText(Html.fromHtml(data.get(flag).getOption2(),
//                    new Html.ImageGetter() {
//
//                        @Override
//                        public Drawable getDrawable(String source) {
//
//                            Toast.makeText(getContext(), source,
//                                    Toast.LENGTH_LONG).show();
//                            Log.e("image",source);
//                            HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
//                                    examBtnOption2, data.get(flag).getOption2(),getContext());
//                            httpGetDrawableTask.execute(source);
//
//                            return null;
//                        }
//
//                    }, null));
//
//            examBtnOption2.setMovementMethod(LinkMovementMethod.getInstance());
//            examBtnOption3.setText(Html.fromHtml(data.get(flag).getOption3(),
//                    new Html.ImageGetter() {
//
//                        @Override
//                        public Drawable getDrawable(String source) {
//
//                            Toast.makeText(getContext(), source,
//                                    Toast.LENGTH_LONG).show();
//                            Log.e("image",source);
//                            HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
//                                    examBtnOption3, data.get(flag).getOption3(),getContext());
//                            httpGetDrawableTask.execute(source);
//
//                            return null;
//                        }
//
//                    }, null));
//
//            examBtnOption3.setMovementMethod(LinkMovementMethod.getInstance());
//        }
//
//
//
//
//        else {
//            Log.e("img","No img");
//            examBtnOption1.setText(data.get(flag).getOption1());
//            examBtnOption2.setText(data.get(flag).getOption2());
//            examBtnOption3.setText(data.get(flag).getOption3());
//            examBtnOption4.setText(data.get(flag).getOption4());
//        }
//

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
//         linearLayout=v.findViewById(R.id.check);
//         TextView textView=new TextView(getActivity());
//         textView.setBackgroundColor(Color.CYAN);
//         textView.setText("check check check");
//         textView.setTypeface(Typeface.SERIF,Typeface.BOLD);
//         textView.setPadding(10,40,0,0);
//         textView.setTextColor(Color.BLUE);
//         textView.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
//         textView.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//         linearLayout.addView(textView);

    }

    @Override
    public void initClicks() {
        examBtnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //I want to start animation here
                examBtnOption1.startAnimation(animation);
                dialogFragment=new SuccessFrag2();
                showDialog(dialogFragment);


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
                examBtnOption2.startAnimation(animation);
                dialogFragment=new SuccessFrag3();
                showDialog(dialogFragment);

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
        examBtnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examBtnOption3.startAnimation(animation);
                dialogFragment=new SuccessFrag2();
                showDialog(dialogFragment);

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
        examBtnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examBtnOption4.startAnimation(animation);
                dialogFragment=new SuccessFrag2();
                showDialog(dialogFragment);

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

        examBtnOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examBtnOption5.startAnimation(animation);
                dialogFragment=new SuccessFrag3();
                showDialog(dialogFragment);

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
    public void onResume() {
        super.onResume();

    }
    @Override
    public void selectAnswer()
    {
        flag++;
        Log.e("flag incre",flag+"");
        if(flag<data.size())
        {
            showLoading();
            examTxtQno.setText(data.get(flag).getId()+" ");
            examTxtQuestion.setText(Html.fromHtml(data.get(flag).getFirst_name(),
                    source -> {

                        Toast.makeText(getContext(), source,
                                Toast.LENGTH_LONG).show();
                        Log.e("image",source);

                        hideLoading();

                        HtmlImgConverter httpGetDrawableTask = new HtmlImgConverter(
                                examTxtQuestion, data.get(flag).getFirst_name(),getContext());
                        httpGetDrawableTask.execute(source);

                        return null;
                    }, null));

            examTxtQuestion.setMovementMethod(LinkMovementMethod.getInstance());
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
            LoadFragment(f);
        }
    }

    @Override
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

}
