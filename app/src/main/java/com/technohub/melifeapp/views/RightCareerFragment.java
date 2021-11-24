package com.technohub.melifeapp.views;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.squareup.picasso.Picasso;
import com.technohub.melifeapp.Interfaces.IRightcareer;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Helper;
import com.technohub.melifeapp.classes.RightDetailedSuggested;
import com.technohub.melifeapp.classes.Right_Brief_Adapter;
import com.technohub.melifeapp.classes.Right_DescIntelligenceAdapter;
import com.technohub.melifeapp.classes.Right_TopCareerAdapter;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.RightCareerAptitude;
import com.technohub.melifeapp.models.RightCareerAptitudeData;
import com.technohub.melifeapp.models.RightCareerChart;
import com.technohub.melifeapp.models.RightCareerChartData;
import com.technohub.melifeapp.models.RightCareerIntelligence;
import com.technohub.melifeapp.models.RightCareerIntelligenceData;
import com.technohub.melifeapp.models.RightCareerInterest;
import com.technohub.melifeapp.models.RightCareerInterestData;
import com.technohub.melifeapp.models.RightCareerPersonality;
import com.technohub.melifeapp.models.RightCareerPersonalityData;
import com.technohub.melifeapp.models.RightRecord;
import com.technohub.melifeapp.models.RightUserDetail;
import com.technohub.melifeapp.models.SkillDescInterestAreas;
import com.technohub.melifeapp.models.SkillReportRequest;
import com.technohub.melifeapp.models.SkillReportResponse;
import com.technohub.melifeapp.presenter.RightCareerReportPresenter;
import com.technohub.melifeapp.services.ApiClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightCareerFragment extends Fragment implements IRightcareer.View {

View v;
TableLayout right_presonality_table,right_interest_table,right_aptitude_table,right_inteelligence_table,right_career_table;
TextView right_Txt_Reportname,right_Txt_dis,right_Txt_Name,right_Txt_age,right_Txt_education,right_Txt_testdate,right_Txt_mobile,right_Txt_email,right_Txt_description,right_Txt_seldesc,right_Txt_seldesc1,right_Txt_seldesc2;
TextView right_Txt_whatstitle,right_Txt_whatsdesc,right_Txt_interstareatitle,right_Txt_interestdesc,right_Txt_aptitudetitle,right_Txt_aptdesc,right_Txt_aptgraphtitle,right_Txt_aptdesctitle;
TextView right_Txt_personality,right_Txt_personalitydesc,right_Txt_titlepersonalitytype,right_Txt_yourdesc,right_Txt_perdesctitle,right_Txt_yourtype;
TextView right_Txt_careermot,right_Txt_careermotdesc,right_Txt_titlecareergraph,right_Txt_titledesccareermot,right_Txt_titlesuggestedcareerfield,right_Txt_brief;
TextView right_Txt_titledetailed,rightcareer_Txt_titlename,right_Txt_titlemultiple,right_Txt_multipledesc,right_Txt_testdesigndesc,right_Txt_titlemulgraph,right_Txt_titlemulscore,right_Txt_titlemuldesc,right_Txt_takingdesc;
RecyclerView right_list_careermot,right_list_brief,right_list_detailed,right_list_intelligence;
ImageView rightcareer_suggestedimg,right_consult,right_Img,suggeted_career_image;
BarChart aptbarchart,intelligencebarchart,personalitybarchart,careermotbarchart,interestbarchart;
RightCareerReportPresenter rightCareerReportPresenter;
int examd,testd;
ProgressDialog progressdialog;
SkillReportRequest skillReportRequest=new SkillReportRequest();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {   v= inflater.inflate(R.layout.right_career_report, container, false);
        v.setBackgroundColor(Color.WHITE);

        Bundle args = getArguments();

        if (args != null) {
            examd = getArguments().getInt("examid");
            testd = getArguments().getInt("testid");
            skillReportRequest.setUser_id(new LoginResponse().getSharedPreferences(getContext(), "userid"));
            skillReportRequest.setExam_id(examd+"");
            skillReportRequest.setTest_id(testd+"");
        }

        rightCareerReportPresenter=new RightCareerReportPresenter(this,skillReportRequest);
        progressdialog.show();

        rightCareerReportPresenter.interest();
        rightCareerReportPresenter.aptitude();
        rightCareerReportPresenter.personality();
        rightCareerReportPresenter.chartdata();
        rightCareerReportPresenter.intelligence();
        rightCareerReportPresenter.careerfield();
        rightCareerReportPresenter.loaduserdetail();


        return v;
    }
    void chrttt(ArrayList<RightCareerInterestData> rightCareerInterests)
    {
     int i=0;
        ArrayList Intestareaval = new ArrayList();
        ArrayList<String> Intestareatop = new ArrayList<String>();
        for (RightCareerInterestData s : rightCareerInterests) {
            Intestareaval.add(new BarEntry(i,Float.parseFloat(s.getSub_dimension_score())));
            Intestareatop.add(s.getSub_dimension());
            i=i+1;
        }
        for(String s:Intestareatop)
        {
            Log.e("labelssss",s);
        }
        BarDataSet dataSet = new BarDataSet(Intestareaval,"Interest Areas");
        BarData data = new BarData(dataSet);
        interestbarchart.setData(data);
        interestbarchart.animateXY(2000, 2000);
        interestbarchart.invalidate();
        data.setBarWidth(0.5f);
        dataSet.setColor(Color.parseColor("#7C01AF"));
        XAxis xAxis = interestbarchart.getXAxis();
        xAxis.setTextSize(2f);
        xAxis.setSpaceMax(5f);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Intestareatop.get((int) value);
            }

        });
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
        right_presonality_table=v.findViewById(R.id.right_table_personality);
        right_Txt_Reportname=v.findViewById(R.id.right_Txt_Reportname);
        right_Txt_dis=v.findViewById(R.id.right_Txt_testdis);
        suggeted_career_image=v.findViewById(R.id.suggeted_career_image);
        right_Txt_Name=v.findViewById(R.id.right_Txt_Name);
        right_Txt_age=v.findViewById(R.id.right_Txt_age);
        right_Txt_education=v.findViewById(R.id.right_Txt_education);
        right_inteelligence_table=v.findViewById(R.id.intelligence_score_table);
        right_aptitude_table=v.findViewById(R.id.right_table_aptitude);
        right_interest_table=v.findViewById(R.id.right_table_interst);
        aptbarchart = v. findViewById(R.id.aptbarchart);
        intelligencebarchart = v. findViewById(R.id.intelligencebarchart);
        personalitybarchart = v. findViewById(R.id.personalitybarchart);
        careermotbarchart = v. findViewById(R.id.careermotbarchart);
        interestbarchart = v. findViewById(R.id.interestbarchart);
        right_Txt_testdate = v. findViewById(R.id.right_Txt_testdate);
        right_Txt_mobile = v. findViewById(R.id.right_Txt_mobile);
        right_Txt_email = v. findViewById(R.id.right_Txt_email);
        right_Txt_description = v. findViewById(R.id.right_Txt_description);
        right_Txt_seldesc = v. findViewById(R.id.right_Txt_seldesc);
//        right_Txt_seldesc2 = v. findViewById(R.id.right_Txt_seldesc2);
//        right_Txt_seldesc1 = v. findViewById(R.id.right_Txt_seldesc1);
//        right_Txt_seldesc1 = v. findViewById(R.id.right_Txt_seldesc1);
        right_Txt_whatstitle = v. findViewById(R.id.right_Txt_whatsinteresttitle);
        right_Txt_whatsdesc = v. findViewById(R.id.right_Txt_whatsdesc);
        right_Txt_interstareatitle = v. findViewById(R.id.right_Txt_interstareatitle);

//        right_Txt_aptitudetitle = v. findViewById(R.id.right_Txt_aptitudetitle);
        right_Txt_aptdesc = v. findViewById(R.id.right_Txt_aptdesc);
//        right_Txt_aptgraphtitle = v. findViewById(R.id.right_Txt_aptgraphtitle);
        right_Txt_aptdesctitle = v. findViewById(R.id.right_Txt_aptdesctitle);
//        right_Txt_personality = v. findViewById(R.id.right_Txt_personality);
        right_Txt_personalitydesc = v. findViewById(R.id.right_Txt_personalitydesc);
//        right_Txt_titlepersonalitytype = v. findViewById(R.id.right_Txt_titlepersonalitytype);
//        right_Txt_yourtype = v. findViewById(R.id.right_Txt_yourtype);
//        right_Txt_yourdesc = v. findViewById(R.id.right_Txt_yourdesc);
//        right_Txt_perdesctitle = v. findViewById(R.id.right_Txt_perdesctitle);
//        right_Txt_careermot = v. findViewById(R.id.right_Txt_careermot);
        right_Txt_careermotdesc = v. findViewById(R.id.right_Txt_careermotdesc);
//        right_Txt_titlecareergraph = v. findViewById(R.id.right_Txt_titlecareergraph);
//        right_Txt_titledesccareermot = v. findViewById(R.id.right_Txt_titledesccareermot);
//        right_Txt_titlesuggestedcareerfield = v. findViewById(R.id.right_Txt_titlesuggestedcareerfield);
        right_list_careermot = v. findViewById(R.id.right_list_careermot);
        right_list_brief = v. findViewById(R.id.right_list_brief);
//        right_Txt_brief = v. findViewById(R.id.right_Txt_brief);
//        right_Txt_titledetailed = v. findViewById(R.id.right_Txt_titledetailed);
        right_list_detailed = v. findViewById(R.id.right_list_detailed);
//        right_Txt_titlemultiple = v. findViewById(R.id.right_Txt_titlemultiple);
        right_Txt_multipledesc = v. findViewById(R.id.right_Txt_multipledesc);
        right_Txt_titlemulgraph = v. findViewById(R.id.right_Txt_titlemulgraph);
//        right_Txt_titlemulscore = v. findViewById(R.id.right_Txt_titlemulscore);
//        right_Txt_titlemuldesc = v. findViewById(R.id.right_Txt_titlemuldesc);
        right_list_intelligence = v. findViewById(R.id.right_list_intelligence);
        right_Txt_takingdesc = v. findViewById(R.id.right_Txt_takingdesc);
//        right_consult = v. findViewById(R.id.right_consult);
//        rightcareer_Txt_titlename = v. findViewById(R.id.rightcareer_Txt_titlename);
        right_Txt_testdesigndesc = v. findViewById(R.id.right_Txt_testdesigndesc);
        rightcareer_suggestedimg = v. findViewById(R.id.rightcareer_suggestedimg);//Image suggested caer missing
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);



    }
    void loadtable(ArrayList<RightCareerPersonalityData> rightCareerPersonalityData)
    {
        ArrayList perval = new ArrayList();
        ArrayList<String> pertop = new ArrayList<String>();
        int i=0;
        for (RightCareerPersonalityData s:rightCareerPersonalityData){//add list of items frm arraylist in json

            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.right_table_layout,null,false);
            TextView tv_personality = (TextView) tableRow.findViewById(R.id.right_Txt_personalitytype);
            TextView tv_charecteristics = (TextView) tableRow.findViewById(R.id.right_Txt_charecteristics);
            TextView tv_career  = (TextView) tableRow.findViewById(R.id.right_Txt_career);


            tv_personality.setText(s.getName());
            tv_charecteristics.setText(Html.fromHtml(s.getSub_dimension_descr()));
            tv_career.setText(Html.fromHtml(s.getSub_dimension_career()));


            right_presonality_table.addView(tableRow);
            perval.add(new BarEntry(i,Float.parseFloat(s.getSub_dimension_score())));
            pertop.add(s.getName());
            i=i+1;
        }


        BarDataSet dataSet = new BarDataSet(perval,"Personality Types");
        BarData data = new BarData(dataSet);
        personalitybarchart.setData(data);
        personalitybarchart.animateXY(2000, 2000);
        personalitybarchart.invalidate();
        dataSet.setColor(R.color.barblue);
        XAxis xAxis = personalitybarchart.getXAxis();
        data.setBarWidth(0.5f);
        dataSet.setColor(Color.parseColor("#0583E2"));
        xAxis.setTextSize(2f);
        xAxis.setSpaceMax(5f);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return pertop.get((int) value);
            }
        });
    }
    void loadtableInterest(ArrayList<RightCareerInterestData> rightCareerInterests) {

        ArrayList Intestareaval = new ArrayList();
        ArrayList<String> Intestareatop = new ArrayList<String>();
        Intestareatop.clear();
        Intestareaval.clear();
        int i=0;
        for (RightCareerInterestData s : rightCareerInterests) {//add list of items frm arraylist in json

            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.right_interest_table, null, false);
            TextView right_Txt_interstarea = (TextView) tableRow.findViewById(R.id.right_Txt_interstarea);
            TextView right_Txt_code = (TextView) tableRow.findViewById(R.id.right_Txt_code);
            TextView right_Txt_interst = (TextView) tableRow.findViewById(R.id.right_Txt_interst);
            TextView right_Txt_value = (TextView) tableRow.findViewById(R.id.right_Txt_value);


            right_Txt_interstarea.setText(s.getName());
            right_Txt_code.setText(s.getName().substring(0,1));
            right_Txt_interst.setText(Html.fromHtml(s.getSub_dimension_brief()));
            right_Txt_value.setText(Html.fromHtml(s.getSub_dimension_descr()));


            right_interest_table.addView(tableRow);
            Intestareaval.add(new BarEntry(i,Float.parseFloat(s.getSub_dimension_score())));
            Intestareatop.add(s.getSub_dimension());
            i=i+1;
        }
        for(String s:Intestareatop)
        {
            Log.e("label",s);
        }

        chrttt(rightCareerInterests);
//        BarDataSet bardataset = new BarDataSet(Intestareaval, "Interest Areas");
//        interestbarchart.animateY(5000);
//
//        BarData data = new BarData( bardataset);
//        bardataset.setColors(Color.parseColor("#ad13c3"));
//        data.setBarWidth(10f);
//        interestbarchart.setData(data);
//        interestbarchart.animateXY(2000, 2000);
//        interestbarchart.invalidate();
    }

    void loadtableAptitude(ArrayList<RightCareerAptitudeData> rightCareerAptitudeData) {
        ArrayList aptval = new ArrayList();
        int i=0;
        ArrayList<String> apttop = new ArrayList<String>();
        for (RightCareerAptitudeData s : rightCareerAptitudeData) {//add list of items frm arraylist in json

            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.right_apt_table, null, false);
            TextView right_Txt_aptitude = (TextView) tableRow.findViewById(R.id.right_Txt_aptitude);
            TextView right_Txt_description = (TextView) tableRow.findViewById(R.id.right_Txt_description);
            TextView right_Txt_score = (TextView) tableRow.findViewById(R.id.right_Txt_score);

            right_Txt_aptitude.setText(s.getName());
            right_Txt_description.setText(Html.fromHtml(s.getSub_dimension_brief()));
            right_Txt_score.setText(s.getSub_dimension_score());



            right_aptitude_table.addView(tableRow);
            aptval.add(new BarEntry(i,Float.parseFloat(s.getSub_dimension_score())));
            apttop.add(s.getName());
            i=i+1;
        }

        BarDataSet dataSet = new BarDataSet(aptval,"Graph-Aptitude");
        BarData data = new BarData(dataSet);
        aptbarchart.setData(data);
        aptbarchart.animateXY(2000, 2000);
        aptbarchart.invalidate();
        XAxis xAxis = aptbarchart.getXAxis();
        data.setBarWidth(0.5f);
        dataSet.setColor(Color.parseColor("#0583E2"));
        xAxis.setTextSize(2f);
        xAxis.setSpaceMax(5f);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return apttop.get((int) value);
            }

        });
    }
    void loadtableIntelligence(ArrayList<RightCareerIntelligenceData> rightCareerIntelligenceData) {
        ArrayList Intelligenceval = new ArrayList();
        ArrayList<String> Intelligencetop = new ArrayList<String>();
        int i=0;
        for (RightCareerIntelligenceData s : rightCareerIntelligenceData) {//add list of items frm arraylist in json

            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.right_intelli_table, null, false);
            TextView right_Txt_intelligence = (TextView) tableRow.findViewById(R.id.right_Txt_intelligence);
            TextView right_Txt_score = (TextView) tableRow.findViewById(R.id.right_Txt_score);

            right_Txt_intelligence.setText(s.getName());
            right_Txt_score.setText(s.getSub_dimension_score());
            right_inteelligence_table.addView(tableRow);
            Intelligenceval.add(new BarEntry(i,Float.parseFloat(s.getSub_dimension_score())));
            Intelligencetop.add(s.getName());
            i=i+1;
        }

        BarDataSet dataSet = new BarDataSet(Intelligenceval,"Graph-Multiple Intelligence");
        BarData data = new BarData(dataSet);
        intelligencebarchart.setData(data);
        intelligencebarchart.animateXY(2000, 2000);
        intelligencebarchart.invalidate();
        XAxis xAxis = intelligencebarchart.getXAxis();
        data.setBarWidth(0.5f);
        dataSet.setColor(Color.parseColor("#7C01AF"));
        xAxis.setTextSize(2f);
        xAxis.setSpaceMax(5f);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Intelligencetop.get((int) value);
            }

        });



    }

void careergraph(RightCareerChart rightCareerChart)
{
    ArrayList careerval = new ArrayList();
    ArrayList<String> careertop = new ArrayList<String>();
    int i=0;
    for (RightCareerChartData s : rightCareerChart.getRecord()) {

        careerval.add(new BarEntry(i,Float.parseFloat(s.getSub_dimension_score())));
        careertop.add(s.getName());
        i=i+1;
    }

    BarDataSet dataSet = new BarDataSet(careerval,"Graph-CareerMotivators");
    BarData data = new BarData(dataSet);
    careermotbarchart.setData(data);
    careermotbarchart.animateXY(2000, 2000);
    careermotbarchart.invalidate();
    dataSet.setColor(R.color.color_humbarcolor);
    XAxis xAxis = careermotbarchart.getXAxis();
    data.setBarWidth(0.5f);
    dataSet.setColor(Color.parseColor("#7C01AF"));
    xAxis.setTextSize(2f);
    xAxis.setSpaceMax(5f);
    xAxis.setLabelRotationAngle(-45);
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setValueFormatter(new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return careertop.get((int) value);
        }

    });


//    rightcareer_Txt_titlename.setText(new LoginResponse().getSharedPreferences(getContext(),"name"));
}

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void LoadRightpersonality(RightCareerPersonality rightCareerPersonality) {

//        right_Txt_personality.setText(rightCareerPersonality.getRecord().get(0).getTitle1());
//        right_Txt_titlepersonalitytype.setText(rightCareerPersonality.getRecord().get(0).getTitle2());
        right_Txt_personalitydesc.setText(Html.fromHtml(rightCareerPersonality.getResult().getResult3().getInterest_des()));
//        right_Txt_perdesctitle.setText(Html.fromHtml(rightCareerPersonality.getRecord().get(0).getTitle4()));
//        right_Txt_yourtype.setText(rightCareerPersonality.getRecord().get(0).getTitle3());

        loadtable(rightCareerPersonality.getRecord());
    }



    @Override
    public void LoadRightAptitude(RightCareerAptitude rightCareerAptitude) {

//        right_Txt_aptitudetitle.setText(rightCareerAptitude.getRecord().get(0).getTitle1());
        right_Txt_aptdesc.setText(Html.fromHtml(rightCareerAptitude.getResult().getResult2().getInterest_des()));
//        right_Txt_aptgraphtitle.setText(rightCareerAptitude.getRecord().get(0).getTitle2());
//        right_Txt_aptdesctitle.setText(rightCareerAptitude.getRecord().get(0).getTitle3());

        loadtableAptitude(rightCareerAptitude.getRecord());
    }
    @Override
    public void LoadCareerField(RightCareerInterest rightCareerInterest) {

//        right_Txt_brief.setText(rightCareerInterest.getRecord().get(0).getTitle2());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Right_Brief_Adapter right_brief_adapter=new Right_Brief_Adapter(this, rightCareerInterest.getRecord(),getContext());
        right_list_brief.setAdapter(right_brief_adapter);
        right_list_brief.setLayoutManager(layoutManager1);
        right_brief_adapter.notifyDataSetChanged();


//        right_Txt_titledetailed.setText(rightCareerInterest.getRecord().get(0).getTitle3());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RightDetailedSuggested rightDetailedSuggested=new RightDetailedSuggested(this, rightCareerInterest.getRecord(),getContext());
        right_list_detailed.setAdapter(rightDetailedSuggested);
        right_list_detailed.setLayoutManager(layoutManager);
        rightDetailedSuggested.notifyDataSetChanged();


        Picasso.get().load(rightCareerInterest.getSuggeted_career_image()).into(suggeted_career_image);


    }

    @Override
    public void LoadRightIntelligence(RightCareerIntelligence rightCareerIntelligence) {

//        right_Txt_titlemultiple.setText(rightCareerIntelligence.getRecord().get(0).getTitle1());
        right_Txt_titlemulgraph.setText(rightCareerIntelligence.getRecord().get(0).getTitle2());
//        right_Txt_titlemulscore.setText(rightCareerIntelligence.getRecord().get(0).getTitle3());
//        right_Txt_titlemuldesc.setText(rightCareerIntelligence.getRecord().get(0).getTitle4());
        right_Txt_multipledesc.setText(Html.fromHtml(rightCareerIntelligence.getResult().getResult5().getIntelligence_res().get(0).getInterest_des()));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Right_DescIntelligenceAdapter right_descIntelligenceAdapter=new Right_DescIntelligenceAdapter(this, rightCareerIntelligence.getRecord(),getContext());
        right_list_intelligence.setAdapter(right_descIntelligenceAdapter);
        right_list_intelligence.setLayoutManager(layoutManager2);
        right_descIntelligenceAdapter.notifyDataSetChanged();


        loadtableIntelligence(rightCareerIntelligence.getRecord());

    }

    @Override
    public void LoadRightCareer(RightCareerChart rightCareerChart) {

//        right_Txt_careermot.setText(rightCareerChart.getRecord().get(0).getTitle1());
        right_Txt_careermotdesc.setText(Html.fromHtml(rightCareerChart.getResult().getResult4().getInterest_des()));
//        right_Txt_titlecareergraph.setText(rightCareerChart.getRecord().get(0).getTitle2());
//        right_Txt_titledesccareermot.setText(rightCareerChart.getRecord().get(0).getTitle3());
//        right_Txt_titlesuggestedcareerfield.setText(rightCareerChart.getRecord().get(0).getTitle4());

        careergraph(rightCareerChart);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Right_TopCareerAdapter right_topCareerAdapter=new Right_TopCareerAdapter(this, rightCareerChart.getRecord(),getContext());
        right_list_careermot.setAdapter(right_topCareerAdapter);
        right_list_careermot.setLayoutManager(layoutManager3);
        right_topCareerAdapter.notifyDataSetChanged();


    }
    @Override
    public void LoadUserdata(RightUserDetail rightRecord) {
//        right_Txt_Reportname.setText(rightRecord.getRecord().getRight_course_data().getTest_name());
        right_Txt_Name.setText(rightRecord.getRecord().getUser_details().getSt_name());
        right_Txt_dis.setText(rightRecord.getRecord().getRight_course_data().getDisclaimer());
        right_Txt_age.setText(calculateAge(rightRecord.getRecord().getUser_details().getSt_dob())+"");
        right_Txt_education.setText(rightRecord.getRecord().getUser_details().getSt_qualification());
        right_Txt_testdate.setText(rightRecord.getRecord().getUser_details().getC_date());
        right_Txt_mobile.setText(rightRecord.getRecord().getUser_details().getSt_mobile());
        right_Txt_email.setText(rightRecord.getRecord().getUser_details().getSt_email());
        right_Txt_description.setText(Html.fromHtml(rightRecord.getRecord().getRight_course_data().getRight_career_finder_test_report()));
        right_Txt_seldesc.setText(Html.fromHtml(rightRecord.getRecord().getRight_course_data().getDescription3()));
//        right_Txt_seldesc1.setText(Html.fromHtml(rightRecord.getRecord().getRight_course_data().getDescription4()));
//        right_Txt_seldesc2.setText(Html.fromHtml(rightRecord.getRecord().getRight_course_data().getDescription()));
        right_Txt_takingdesc.setText(Html.fromHtml(rightRecord.getRecord().getRight_course_data().getWhats_next()));
        right_Txt_testdesigndesc.setText(Html.fromHtml(rightRecord.getRecord().getRight_course_data().getTest_design()));
//        Picasso.get().load(ApiClient.IMG_URL+"userfiles/files/What's%20Next(5).png").into(right_consult);
//        Picasso.get().load("http://"+rightRecord.getRecord().getRight_course_data().getTest_icon()).into(right_Img);
        progressdialog.dismiss();
    }
    @Override
    public void LoadRightInterest(RightCareerInterest rightCareerInterest) {
        right_Txt_whatstitle.setText(rightCareerInterest.getRecord().get(0).getTitle1());
        right_Txt_whatsdesc.setText(Html.fromHtml(rightCareerInterest.getResult().getResult1().getInterest_des()));
        right_Txt_interstareatitle.setText(rightCareerInterest.getRecord().get(0).getTitle2());
//        right_Txt_interestdesc.setText(rightCareerInterest.getRecord().get(0).getTitle3());
        loadtableInterest(rightCareerInterest.getRecord());
    }
}
