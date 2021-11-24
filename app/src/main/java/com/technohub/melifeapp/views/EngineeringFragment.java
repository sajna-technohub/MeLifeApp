package com.technohub.melifeapp.views;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;
import com.technohub.melifeapp.Interfaces.IEngineering;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.EnggDescInterestAdapter;
import com.technohub.melifeapp.classes.Helper;
import com.technohub.melifeapp.classes.MarkerViewClass;
import com.technohub.melifeapp.models.EnggListData;
import com.technohub.melifeapp.models.EnggResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.presenter.EngineeringPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EngineeringFragment extends Fragment implements IEngineering.View {
View v;
    TextView engg_Txt_Reportname,engg_Txt_Age,engg_Txt_edu,enggbranch_Txt_Name,enggbranch_Txt_testdate,enggbranch_Txt_mobile,enggbranch_Txt_email,enggbranch_Txt_description,enggbranch_Txt_benefitsdesc,enggbranch_Txt_engginterest;
    TextView enggbranch_Txt_branchfindergraph,enggbranch_Txt_designdesc,enggbranch_Txt_disclaimerdesc,enggbranch_Txt_interestdesc,enggbranch_Txt_titletable,enggbranch_Txt_descinterestgrptitle;
    ImageView enggbranch_Img,enggbranch_Img_taking,enggbranch_Img_engg1,enggbranch_Img_engg2,enggbranch_Img_engg3,enggbranch_Img_engg4,enggbranch_Img_engg5;
    TableLayout enggbranch_table;
    TextView enggbranch_Txt_takingdesc,engg_Txt_titlename;
    RecyclerView engg_list_descinterestgrp;
    PieChart enggbranch;
    int examd,testd;
    ProgressDialog progressdialog;
    StreamFinderRequest skillReportRequest=new StreamFinderRequest();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.enggbranch_finder_report, container, false);
        v.setBackgroundColor(Color.WHITE);
        Bundle args = getArguments();

        if (args != null) {
            examd = getArguments().getInt("examid");
            testd = getArguments().getInt("testid");
            skillReportRequest.setUser_id(new LoginResponse().getSharedPreferences(getContext(), "userid"));
            skillReportRequest.setExam_id(examd+"");
            skillReportRequest.setTest_id(testd+"");
        }

        EngineeringPresenter engineeringPresenter=new EngineeringPresenter(this,skillReportRequest);
        engineeringPresenter.created();
        progressdialog.show();
        return v;
    }
    @Override
    public void init() {
//        engg_Txt_Reportname=v.findViewById(R.id.enggbranch_Txt_Reportname);
        enggbranch_Txt_Name=v.findViewById(R.id.enggbranch_Txt_Name);
        enggbranch_Txt_testdate=v.findViewById(R.id.enggbranch_Txt_testdate);
        enggbranch_Txt_mobile=v.findViewById(R.id.enggbranch_Txt_mobile);
        enggbranch_Txt_email=v.findViewById(R.id.enggbranch_Txt_email);
//        enggbranch_Img=v.findViewById(R.id.enggbranch_Img);
        enggbranch_Txt_description=v.findViewById(R.id.enggbranch_Txt_description);
        enggbranch_Txt_benefitsdesc=v.findViewById(R.id.enggbranch_Txt_benefitsdesc);
        enggbranch_Txt_engginterest=v.findViewById(R.id.enggbranch_Txt_engginterest);
        enggbranch_Txt_branchfindergraph=v.findViewById(R.id.enggbranch_Txt_branchfindergraph);
        enggbranch_Txt_interestdesc=v.findViewById(R.id.enggbranch_Txt_interestdesc);
//        enggbranch_Txt_titletable=v.findViewById(R.id.enggbranch_Txt_titletable);
        enggbranch_table=v.findViewById(R.id.enggbranch_table);
//        enggbranch_Txt_descinterestgrptitle=v.findViewById(R.id.enggbranch_Txt_descinterestgrptitle);
        engg_list_descinterestgrp=v.findViewById(R.id.engg_list_descinterestgrp);
        enggbranch_Img_engg1=v.findViewById(R.id.enggbranch_Img_engg1);
        enggbranch_Img_engg2=v.findViewById(R.id.enggbranch_Img_engg2);
        enggbranch_Img_engg3=v.findViewById(R.id.enggbranch_Img_engg3);
        enggbranch_Img_engg4=v.findViewById(R.id.enggbranch_Img_engg4);
        enggbranch_Img_engg5=v.findViewById(R.id.enggbranch_Img_engg5);
        enggbranch_Txt_takingdesc=v.findViewById(R.id.enggbranch_Txt_takingdesc);
        enggbranch_Img_taking=v.findViewById(R.id.enggbranch_Img_taking);
        enggbranch_Txt_designdesc=v.findViewById(R.id.enggbranch_Txt_designdesc);
        enggbranch_Txt_disclaimerdesc=v.findViewById(R.id.enggbranch_Txt_disclaimerdesc);
        enggbranch=v.findViewById(R.id.enggbranch);
        engg_Txt_Age=v.findViewById(R.id.enggbranch_Txt_Age);
        engg_Txt_edu=v.findViewById(R.id.enggbranch_Txt_Education);
//        engg_Txt_titlename=v.findViewById(R.id.engg_Txt_titlename);
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);
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
    public void LoadEnggReportData(EnggResponse enggResponse) {
//        Picasso.get().load("http://"+enggResponse.getRecord().getTest_data().getIcon()).into(enggbranch_Img);

//        engg_Txt_Reportname.setText(enggResponse.getRecord().getTest_data().getTest_name());
        enggbranch_Txt_Name.setText(enggResponse.getRecord().getUser_details().getSt_name());
        engg_Txt_edu.setText(enggResponse.getRecord().getUser_details().getSt_qualification());
        enggbranch_Txt_disclaimerdesc.setText(enggResponse.getRecord().getTest_data().getDisclaimer());
        engg_Txt_Age.setText(calculateAge(enggResponse.getRecord().getUser_details().getSt_dob())+"");
        enggbranch_Txt_testdate.setText(enggResponse.getRecord().getUser_details().getC_date());
        enggbranch_Txt_mobile.setText(enggResponse.getRecord().getUser_details().getSt_mobile());
        enggbranch_Txt_email.setText(enggResponse.getRecord().getUser_details().getSt_email());
        enggbranch_Txt_description.setText(Html.fromHtml(enggResponse.getRecord().getTest_data().getEngineering_test_descr()));
        enggbranch_Txt_benefitsdesc.setText(Html.fromHtml(enggResponse.getRecord().getTest_data().getBenefits()));
        enggbranch_Txt_branchfindergraph.setText(Html.fromHtml(enggResponse.getRecord().getList_data().get(0).getTitle4()));
//        enggbranch_Txt_descinterestgrptitle.setText(Html.fromHtml(enggResponse.getRecord().getList_data().get(0).getTitle3()));
        enggbranch_Txt_interestdesc.setText(Html.fromHtml(enggResponse.getRecord().getList_data().get(0).getTitle1_descr()));
//        enggbranch_Txt_titletable.setText(Html.fromHtml(enggResponse.getRecord().getList_data().get(0).getTitle2()));


        LoadTableEnggBranches(enggResponse.getRecord().getList_data());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        engg_list_descinterestgrp.setLayoutManager(layoutManager);
        EnggDescInterestAdapter enggDescInterestAdapter=new EnggDescInterestAdapter(this,enggResponse.getRecord().getList_data(),getContext());
        engg_list_descinterestgrp.setAdapter(enggDescInterestAdapter);
        enggDescInterestAdapter.notifyDataSetChanged();

        Picasso.get().load(enggResponse.getRecord().getCareer_Paths_to_Become_a_Engineer().getImg1()).into(enggbranch_Img_engg1);
        Picasso.get().load(enggResponse.getRecord().getCareer_Paths_to_Become_a_Engineer().getImg2()).into(enggbranch_Img_engg2);
        Picasso.get().load(enggResponse.getRecord().getCareer_Paths_to_Become_a_Engineer().getImg3()).into(enggbranch_Img_engg3);
        Picasso.get().load(enggResponse.getRecord().getCareer_Paths_to_Become_a_Engineer().getImg4()).into(enggbranch_Img_engg4);
        Picasso.get().load(enggResponse.getRecord().getCareer_Paths_to_Become_a_Engineer().getImg5()).into(enggbranch_Img_engg5);


        ArrayList pieEntries = new ArrayList<>();
        PieData pieData;
        PieDataSet pieDataSet;
        for(EnggListData e:enggResponse.getRecord().getList_data())
        {
            pieEntries.add(new PieEntry(e.getSub_dimension_score(), e.getSub_dimension()));
        }

        pieDataSet = new PieDataSet(pieEntries, "PieChart");
        pieData = new PieData(pieDataSet);
        enggbranch.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        enggbranch.setEntryLabelColor(Color.BLACK);
        pieDataSet.setValueTextSize(5f);
        pieDataSet.setSliceSpace(1f);
        enggbranch.setDrawHoleEnabled(false);
        enggbranch.setHoleRadius(0.0f);
        MarkerViewClass markerViewClass=new MarkerViewClass(getContext(),R.layout.custom_marker_view_layout);
        enggbranch.setMarkerView(markerViewClass);
        enggbranch.setDrawEntryLabels(false);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        enggbranch_Txt_takingdesc.setText(Html.fromHtml(enggResponse.getRecord().getTest_data().getWhats_next()));
        enggbranch_Txt_designdesc.setText(Html.fromHtml(enggResponse.getRecord().getTest_data().getTest_design()));
        Picasso.get().load("http://3.7.48.112/ckfinder/userfiles/files/What's%20Next(5).png").into(enggbranch_Img_taking);
//        engg_Txt_titlename.setText(new LoginResponse().getSharedPreferences(getContext(),"name"));
   progressdialog.dismiss();
    }

    void LoadTableEnggBranches(ArrayList<EnggListData> enggListData)
    {
            for (EnggListData s:enggListData){//add list of items frm arraylist in json

                View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.enggbranch_table,null,false);
                TextView tv_area = (TextView) tableRow.findViewById(R.id.engg_Txt_interestgrp);
                TextView tv_code  = (TextView) tableRow.findViewById(R.id.engg_Txt_score);
                TextView tv_score  = (TextView) tableRow.findViewById(R.id.engg_Txt_branches);

                tv_area.setText(s.getSub_dimension());
                tv_code.setText(s.getSub_dimension_score()+"");
                tv_score.setText(Html.fromHtml(s.getSub_dimension_career()));
                enggbranch_table.addView(tableRow);
            }
    }
    @Override
    public void showLoading()
    {

    }

    @Override
    public void hideLoading() {

    }


}
