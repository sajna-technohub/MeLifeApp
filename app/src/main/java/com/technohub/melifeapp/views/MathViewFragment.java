package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

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
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.skydoves.elasticviews.ElasticButton;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Data;
import com.technohub.melifeapp.presenter.ExamPresenter;
import com.technohub.melifeapp.views.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import io.github.kexanie.library.MathView;

public class MathViewFragment extends Fragment  implements IExam.View  {

    ElasticButton mathBtnOption1,mathBtnOption2,mathBtnOption3,mathBtnOption4,mathBtnOption5;

    MathView mathTxtQuestion;

    TextView mathTxtQno;

    SpinKitView spinMath;

    int flag=0;

    DialogFragment dialogFragment;

    private static int TIME_OUT = 1000;

    ExamPresenter examPresenter;

    Animation animation ;

    View v;

    List<Data> data=new ArrayList<>();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_math_view, container, false);
        v.setBackgroundColor(Color.WHITE);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_animation);

        String s="\\(x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}\\)";

        examPresenter = new ExamPresenter(this,s);
        examPresenter.created();

//        examTxtQuestion.setText(data.get(flag).getFirst_name());
//        examTxtQno.setText(data.get(flag).getId()+" ");

        return v;
    }

    @Override
    public void init() {
        mathBtnOption1=v.findViewById(R.id.mathBtnOption1);
        mathBtnOption2=v.findViewById(R.id.mathBtnOption2);
        mathBtnOption3=v.findViewById(R.id.mathBtnOption3);
        mathBtnOption4=v.findViewById(R.id.mathBtnOption4);
        mathBtnOption5=v.findViewById(R.id.mathBtnOption5);
        mathTxtQuestion=(MathView) v.findViewById(R.id.mathQuestionTxt);
        mathTxtQno=v.findViewById(R.id.mathTxtQno);
        spinMath=v.findViewById(R.id.mathSpinKit);
    }

    @Override
    public void initClicks() {

        mathBtnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathBtnOption1.startAnimation(animation);
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
        mathBtnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathBtnOption2.startAnimation(animation);
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
        mathBtnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathBtnOption3.startAnimation(animation);
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
        mathBtnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathBtnOption4.startAnimation(animation);
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
        mathBtnOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathBtnOption5.startAnimation(animation);
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
    }

    @Override
    public void goToNextFragment() {

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
    public void selectAnswer() {
        flag++;
        Log.e("flag incre",flag+"");
        if(flag<data.size()) {
            mathTxtQno.setText(data.get(flag).getId() + " ");
            mathTxtQuestion.setText(data.get(flag).getFirst_name() + " ");
            mathBtnOption1.setText(data.get(flag).getOption1());
            mathBtnOption2.setText(data.get(flag).getOption2());
            mathBtnOption3.setText(data.get(flag).getOption3());
            mathBtnOption4.setText(data.get(flag).getOption4());
        }
        if(data.size()==flag)
        {
            Log.e("size","Reaches size"+data.size());
            Fragment f=new HomeFragment();
            LoadFragment(f);
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
        spinMath.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        spinMath.setVisibility(View.GONE);
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
    public void setQuestion()
    {
        Log.e("in set","hsgskg");
showLoading();
        String v="<p>the mass of water produced from <span class=\"math-tex\">\\(445 \\, \\mathrm{g}\\)</span> of <span class=\"math-tex\">\\(\\mathrm{C}_{57} \\mathrm{H}_{110} \\mathrm{O}_{6}\\)</span> is:</p>";
        mathTxtQno.setText(data.get(flag).getId()+" ");
        mathTxtQuestion.setText(data.get(flag).getFirst_name());
        hideLoading();
        Log.e("showload","loadingcomplete");

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
}
