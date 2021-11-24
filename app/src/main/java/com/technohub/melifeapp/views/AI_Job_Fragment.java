package com.technohub.melifeapp.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technohub.melifeapp.Interfaces.IAIjob;
import com.technohub.melifeapp.Interfaces.IEngineering;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.AI_Result_Adapter;
import com.technohub.melifeapp.classes.Helper;
import com.technohub.melifeapp.models.AIJobResponse;
import com.technohub.melifeapp.models.EnggResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.presenter.AI_JobPresenter;
import com.technohub.melifeapp.presenter.EngineeringPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AI_Job_Fragment extends Fragment implements IAIjob.View {

View v;
TextView aijob_Txt_Reportname,aijob_Txt_Name,aijob_Txt_age,aijob_Txt_edu,aijob_Txt_testdate,aijob_Txt_mobile,aijob_Txt_email,aijob_Txt_description,aijob_Txt_findtitle;
TextView aijob_Txt_finddesc,aijob_Txt_titlename,aijob_Txt_aisafesonetitle,aijob_Txt_designdesc,aijob_Txt_safedesc,aijob_Txt_titledominant,aijob_Txt_dominantdesc,aijob_Txt_takingdesc;
ImageView aijob_Img;
ListView ai_engg_list;
ProgressDialog progressdialog;
int examd,testd;
StreamFinderRequest skillReportRequest=new StreamFinderRequest();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_ai__job_, container, false);
        v.setBackgroundColor(Color.WHITE);

        Bundle args = getArguments();

        if (args != null) {

            examd = getArguments().getInt("examid");
            testd = getArguments().getInt("testid");
            skillReportRequest.setUser_id(new LoginResponse().getSharedPreferences(getContext(), "userid"));
            skillReportRequest.setExam_id(examd+"");
            skillReportRequest.setTest_id(testd+"");
        }
        AI_JobPresenter ai_jobPresenter=new AI_JobPresenter(this,skillReportRequest);
        ai_jobPresenter.created();
        progressdialog.show();
        return v;
    }
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static int calculateAge(String date) {

        int age = 0;
        try {
            Date date1 = dateFormat.parse(date);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(date1);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH);
            int month2 = dob.get(Calendar.MONTH);
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age ;
    }
    @Override
    public void init() {
//        aijob_Txt_Reportname=v.findViewById(R.id.aijob_Txt_Reportname);
        aijob_Txt_Name=v.findViewById(R.id.aijob_Txt_Name);
        aijob_Txt_testdate=v.findViewById(R.id.aijob_Txt_testdate);
        aijob_Txt_mobile=v.findViewById(R.id.aijob_Txt_mobile);
        aijob_Txt_email=v.findViewById(R.id.aijob_Txt_email);
        aijob_Txt_age=v.findViewById(R.id.aijob_Txt_Age);
        aijob_Txt_edu=v.findViewById(R.id.aijob_Txt_Education);
//        aijob_Img=v.findViewById(R.id.aijob_Img);
        aijob_Txt_description=v.findViewById(R.id.aijob_Txt_description);
        aijob_Txt_findtitle=v.findViewById(R.id.aijob_Txt_findtitle);
        aijob_Txt_finddesc=v.findViewById(R.id.aijob_Txt_finddesc);
        aijob_Txt_aisafesonetitle=v.findViewById(R.id.aijob_Txt_aisafesonetitle);
        ai_engg_list=v.findViewById(R.id.ai_engg_list);
//        aijob_Txt_titledominant=v.findViewById(R.id.aijob_Txt_titledominant);
        aijob_Txt_dominantdesc=v.findViewById(R.id.aijob_Txt_dominantdesc);
        aijob_Txt_takingdesc=v.findViewById(R.id.aijob_Txt_takingdesc);
        aijob_Txt_designdesc=v.findViewById(R.id.aijob_Txt_designdesc);
        aijob_Txt_safedesc=v.findViewById(R.id.aijob_Txt_safedesc);
//        aijob_Txt_titlename=v.findViewById(R.id.aijob_Txt_titlename);
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);
    }
    @Override
    public void LoadAIReportData(AIJobResponse aiJobResponse) {
//        aijob_Txt_Reportname.setText(aiJobResponse.getRecord().getTest_name());
        aijob_Txt_Name.setText(aiJobResponse.getRecord().getName());
        aijob_Txt_testdate.setText(aiJobResponse.getRecord().getCompletion_date());
        aijob_Txt_age.setText(calculateAge(aiJobResponse.getRecord().getDob())+"");
        aijob_Txt_edu.setText(aiJobResponse.getRecord().getQualification());
        aijob_Txt_mobile.setText(aiJobResponse.getRecord().getMobile_no());
        aijob_Txt_email.setText(aiJobResponse.getRecord().getEmail());
        aijob_Txt_description.setText(Html.fromHtml(aiJobResponse.getRecord().getAI_Job_Risk_Finder_Test_Report()));
        aijob_Txt_findtitle.setText(Html.fromHtml(aiJobResponse.getRecord().getResult().get(0).getTitle1()));
        aijob_Txt_finddesc.setText(Html.fromHtml(aiJobResponse.getRecord().getDescription3()));
//        Picasso.get().load("http://"+aiJobResponse.getRecord().getTest_icon()).into(aijob_Img);
//        aijob_Txt_titledominant.setText(Html.fromHtml(aiJobResponse.getRecord().getResult().get(0).getTitle3()));
        aijob_Txt_dominantdesc.setText(Html.fromHtml(aiJobResponse.getRecord().getResult().get(0).getSub_dimension_brief_description()));
        aijob_Txt_aisafesonetitle.setText(Html.fromHtml(aiJobResponse.getRecord().getResult().get(0).getTitle2()));

        AI_Result_Adapter ai_result_adapter=new AI_Result_Adapter(this,aiJobResponse.getRecord().getResult(),getContext());
        ai_engg_list.setAdapter(ai_result_adapter);
        Helper.getListViewSize(ai_engg_list);

        aijob_Txt_safedesc.setText(Html.fromHtml(aiJobResponse.getRecord().getResult().get(2).getSub_dimension_brief_description()));//not completed
        aijob_Txt_takingdesc.setText(Html.fromHtml(aiJobResponse.getRecord().getWhats_Next()));
        aijob_Txt_designdesc.setText(Html.fromHtml(aiJobResponse.getRecord().getTest_design()));
//        aijob_Txt_titlename.setText(new LoginResponse().getSharedPreferences(getContext(),"name"));
        progressdialog.dismiss();

    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
