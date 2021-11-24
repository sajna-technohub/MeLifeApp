package com.technohub.melifeapp.views;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.axes.Linear;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.core.ui.ChartCredits;
import com.anychart.data.Mapping;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LabelsOverlapMode;
import com.anychart.enums.Orientation;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;
import com.technohub.melifeapp.Interfaces.IHumanity;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Helper;
import com.technohub.melifeapp.classes.Humanity_DescBrief_Adapter;
import com.technohub.melifeapp.classes.Humanity_Desc_Adapter;
import com.technohub.melifeapp.models.CommerceTableSorted;
import com.technohub.melifeapp.models.HumanityChartData;
import com.technohub.melifeapp.models.HumanityResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.models.StreamFinderResponse;
import com.technohub.melifeapp.presenter.CommercePresenter;
import com.technohub.melifeapp.presenter.HumanityPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class HumanityFragment extends Fragment implements IHumanity.View {

View v;
TextView humanity_Txt_Reportname,humanity_Txt_disclaimerdesc,humanity_Txt_Age,humanity_Txt_edu,humanity_Txt_Name,humanity_Txt_testdate,humanity_Txt_mobile,humanity_Txt_email,humanity_Txt_description,humanity_Txt_benefitsdesc;
TextView humanity_Txt_graphtitle,humanity_Txt_titlename,humanity_Txt_descinteresttitle,humanity_Txt_designdesc,humanity_Txt_whatdesc,humanity_Txt_courceopt,humanityTxthigh,humanityTxtavg,humanityTxtlow,humanity_Txt_careerfieldtitle;
ImageView humanity_Img,humanity_Img_courceoptions,humanity_Img_taking;
TableLayout humanity_table;
BarChart barchart;
RecyclerView humanitylistdesc,humanitylistdescdesc;
ProgressDialog progressdialog;
    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
    float defaultBarWidth = -1;
StreamFinderRequest skillReportRequest=new StreamFinderRequest();
int examd,testd;
    ArrayList<String> labels = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.humanity_report, container, false);
        v.setBackgroundColor(Color.WHITE);

        Bundle args = getArguments();

        if (args != null) {
            examd = getArguments().getInt("examid");
            testd = getArguments().getInt("testid");
            skillReportRequest.setUser_id(new LoginResponse().getSharedPreferences(getContext(), "userid"));
            skillReportRequest.setExam_id(examd+"");
            skillReportRequest.setTest_id(testd+"");
        }

        HumanityPresenter humanityPresenter=new HumanityPresenter(this,skillReportRequest);
        humanityPresenter.created();
        progressdialog.show();

        return v;
    }

    @Override
    public void init() {
        humanity_Txt_Reportname=v.findViewById(R.id.humanity_Txt_Reportname) ;
        humanity_Txt_disclaimerdesc=v.findViewById(R.id.humanity_Txt_disclaimerdesc) ;

        humanity_Txt_Age=v.findViewById(R.id.humanity_Txt_Age) ;
        humanity_Txt_edu=v.findViewById(R.id.humanity_Txt_Education) ;
        humanity_Txt_Name=v.findViewById(R.id.humanity_Txt_Name) ;
        barchart=v.findViewById(R.id.barchart) ;
        humanity_Txt_testdate=v.findViewById(R.id.humanity_Txt_testdate) ;
        humanity_Txt_mobile=v.findViewById(R.id.humanity_Txt_mobile) ;
        humanity_Txt_email=v.findViewById(R.id.humanity_Txt_email) ;
//        humanity_Img=v.findViewById(R.id.humanity_Img) ;
        humanity_Txt_description=v.findViewById(R.id.humanity_Txt_description) ;
        humanity_Txt_benefitsdesc=v.findViewById(R.id.humanity_Txt_benefitsdesc) ;
        humanity_Txt_graphtitle=v.findViewById(R.id.humanity_Txt_graphtitle) ;
//        humanity_Txt_descinteresttitle=v.findViewById(R.id.humanity_Txt_descinteresttitle) ;
        humanityTxthigh=v.findViewById(R.id.humanityTxthigh) ;
        humanityTxtavg=v.findViewById(R.id.humanityTxtavg) ;
        humanityTxtlow=v.findViewById(R.id.humanityTxtlow) ;
        humanity_table=v.findViewById(R.id.humanity_table) ;
        humanitylistdesc=v.findViewById(R.id.humanitylistdesc) ;
        humanitylistdescdesc=v.findViewById(R.id.humanitylistdescdesc) ;
        humanity_Txt_careerfieldtitle=v.findViewById(R.id.humanity_Txt_careerfieldtitle) ;
//        humanity_Txt_courceopt=v.findViewById(R.id.humanity_Txt_courceopt) ;
        humanity_Img_courceoptions=v.findViewById(R.id.humanity_Img_courceoptions) ;
        humanity_Txt_whatdesc=v.findViewById(R.id.humanity_Txt_whatdesc) ;
        humanity_Img_taking=v.findViewById(R.id.humanity_Img_taking) ;
//        humanity_Txt_titlename=v.findViewById(R.id.humanity_Txt_titlename) ;
        humanity_Txt_designdesc=v.findViewById(R.id.humanity_Txt_designdesc) ;
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);
    }
    @Override
    public void LoadHumanityReportData(HumanityResponse humanityResponse) {
//        humanity_Txt_Reportname.setText(humanityResponse.getRecord().getHumanities_course_data().getTest_name());
        humanity_Txt_Name.setText(humanityResponse.getRecord().getUser_details().getSt_name());
        humanity_Txt_disclaimerdesc.setText(humanityResponse.getRecord().getHumanities_course_data().getDisclaimer());
        humanity_Txt_testdate.setText(humanityResponse.getRecord().getUser_details().getC_date());
        humanity_Txt_Age.setText(calculateAge(humanityResponse.getRecord().getUser_details().getSt_dob())+"");
        humanity_Txt_edu.setText(humanityResponse.getRecord().getUser_details().getSt_qualification());
        humanity_Txt_mobile.setText(humanityResponse.getRecord().getUser_details().getSt_mobile());
        humanity_Txt_email.setText(humanityResponse.getRecord().getUser_details().getSt_email());
//        Picasso.get().load("http://"+humanityResponse.getRecord().getHumanities_course_data().getIcon()).into(humanity_Img);
        humanity_Txt_description.setText(Html.fromHtml(humanityResponse.getRecord().getHumanities_course_data().getHumanities_report_description()));
        humanity_Txt_benefitsdesc.setText(Html.fromHtml(humanityResponse.getRecord().getHumanities_course_data().getBenefits()));
        humanity_Txt_graphtitle.setText(humanityResponse.getRecord().getChart_data().get(0).getTitle1());
//        humanity_Txt_descinteresttitle.setText(humanityResponse.getRecord().getChart_data().get(0).getTitle2());
        humanityTxthigh.setText(humanityResponse.getRecord().getInterest_level_high());
        humanityTxtavg.setText(humanityResponse.getRecord().getInterest_level_average());
        humanityTxtlow.setText(humanityResponse.getRecord().getInterest_level_low());
        humanity_Txt_careerfieldtitle.setText(humanityResponse.getRecord().getChart_data().get(0).getTitile3());
//        humanity_Txt_courceopt.setText(humanityResponse.getRecord().getChart_data().get(0).getTitle4());
        humanity_Txt_whatdesc.setText(Html.fromHtml(humanityResponse.getRecord().getHumanities_course_data().getWhats_next()));
        humanity_Txt_designdesc.setText(Html.fromHtml(humanityResponse.getRecord().getHumanities_course_data().getTest_design()));

        LoadDescriptiontable(humanityResponse.getRecord().getChart_data());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Humanity_DescBrief_Adapter humanity_descBrief_adapter=new Humanity_DescBrief_Adapter(this,humanityResponse.getRecord().getChart_data(),getContext());
        humanitylistdesc.setAdapter(humanity_descBrief_adapter);
        humanitylistdesc.setLayoutManager(layoutManager);
        humanity_descBrief_adapter.notifyDataSetChanged();

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Humanity_Desc_Adapter humanity_desc_adapter=new Humanity_Desc_Adapter(this,humanityResponse.getRecord().getChart_data(),getContext());
        humanitylistdescdesc.setAdapter(humanity_desc_adapter);
        humanitylistdescdesc.setLayoutManager(layoutManager1);
        humanity_desc_adapter.notifyDataSetChanged();
        Picasso.get().load(humanityResponse.getRecord().getImg_path()).into(humanity_Img_courceoptions);
        progressdialog.dismiss();
    }
    void LoadDescriptiontable(ArrayList<HumanityChartData> humanityChartData) {

        int i=0;
        for (HumanityChartData s : humanityChartData) {//add list of items frm arraylist in json

            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.humanity_table_desc, null, false);
            TextView commerce_Txt_branch = (TextView) tableRow.findViewById(R.id.humanity_TxtBranch);
            TextView commerce_Txt_score = (TextView) tableRow.findViewById(R.id.humanityTxtscore);
            TextView commerce_Txt_inlevel = (TextView) tableRow.findViewById(R.id.humanityTxtinlevel);
            TextView commerce_Txt_career = (TextView) tableRow.findViewById(R.id.humanityTxtcareer);


            commerce_Txt_branch.setText(s.getSub_dimension());
            commerce_Txt_score.setText(s.getSub_dimension_score() + "");
            commerce_Txt_inlevel.setText(Html.fromHtml(s.getPriority_level()));
            commerce_Txt_career.setText(Html.fromHtml(s.getSub_dimension_career()));
            humanity_table.addView(tableRow);
//            labels.add(s.getSub_dimension());
//            scorelist.add(new BarEntry(i,s.getSub_dimension_score()));
//            i=i+10;
        }
//        setChart(humanityChartData.size(),humanityChartData);
        chrttt(humanityChartData);
        setChart(humanityChartData);
//        BarDataSet bardataset = new BarDataSet(scorelist, "Humanity Career field");
//        BarData data = new BarData( bardataset);
//        barchart.animateY(5000);
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        data.setBarWidth(3f);
//        barchart.setData(data); // set the data and list of labels into chart
    }
    int i=0;

    void chrttt(ArrayList<HumanityChartData> humanityChartData)
    {
        ArrayList scorelist=new ArrayList();
        for (HumanityChartData s : humanityChartData) {
            labels.add(s.getSub_dimension());
            scorelist.add(new BarEntry(i,s.getSub_dimension_score()));
            i++;
        }
        BarDataSet dataSet = new BarDataSet(scorelist,"");
        dataSet.setColor(getResources().getColor(R.color.color_humbarcolor));

        BarData data = new BarData(dataSet);
        data.setBarWidth(1f);
        barchart.setData(data);
        barchart.animateXY(2000, 2000);
        barchart.invalidate();
        XAxis xAxis = barchart.getXAxis();
        xAxis.setTextSize(2f);
        xAxis.setLabelRotationAngle(-45);
        barchart.setHorizontalScrollBarEnabled(true);
//        xAxis.setAxisMinimum(data.getXMin()-.5f);
//        xAxis.setAxisMaximum(data.getXMax()+.5f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int) value);
            }

        });
    }
    @Override
    public void showLoading() {

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
    private void setChart( ArrayList<HumanityChartData> humanityChartData) {

        AnyChartView anyChartView = v.findViewById(R.id.any_chart_view);


        Cartesian barChart = AnyChart.bar();

        barChart.animation(true);

        barChart.padding(10d, 20d, 5d, 20d);

        barChart.yScale().stackMode(ScaleStackMode.VALUE);
//
        barChart.yAxis(0).labels().format(
                "function() {\n" +
                        "    return Math.abs(this.value);\n" +
                        "  }");
        barChart.xAxis(0d).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        barChart.interactivity().hoverMode(HoverMode.BY_X);

        barChart.tooltip()
                .title(false)
                .separator(false)
                .displayMode(TooltipDisplayMode.SEPARATED)
                .positionMode(TooltipPositionMode.POINT)
                .useHtml(true)
                .fontSize(12d)
                .offsetX(5d)
                .offsetY(0d)
                .format(
                        "function() {\n" +
                                "      return '<span style=\"color: #000\"></span>' + Math.abs(this.value);\n" +
                                "    }");

        List<DataEntry> seriesData = new ArrayList<>();

        for (HumanityChartData s : humanityChartData) {
            labels.add(s.getSub_dimension());
            seriesData.add(new CustomDataEntry(s.getSub_dimension(), s.getSub_dimension_score(),s.getSub_dimension_score()));
        }


        Bar series1 = barChart.bar(seriesData);
        series1.color("#7C01AF").name("Graph Personality");
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER);

        barChart.legend().enabled(true);
        barChart.legend().inverted(false);
        barChart.legend().fontSize(13d);
        barChart.legend().padding(0d, 0d, 20d, 0d);
        anyChartView.setChart(barChart);

    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
//            setValue("value2", value2);
        }
    }
    private void setBarWidth(BarData barData, int size) {
        if (dataSets.size() > 1) {
            float barSpace = 0.02f;
            float groupSpace = 0.3f;
            defaultBarWidth = (1 - groupSpace) / dataSets.size() - barSpace;
            if (defaultBarWidth >= 0) {
                barData.setBarWidth(defaultBarWidth);
            } else {
                Toast.makeText(getContext(), "Default Barwdith " + defaultBarWidth, Toast.LENGTH_SHORT).show();
            }
            int groupCount = size;//edited
            if (groupCount != -1) {
                barchart.getXAxis().setAxisMinimum(0);
                barchart.getXAxis().setAxisMaximum(0 + barchart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
                barchart.getXAxis().setCenterAxisLabels(true);
            } else {
                Toast.makeText(getContext(), "no of bar groups is " + groupCount, Toast.LENGTH_SHORT).show();
            }

            barchart.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping
            barchart.invalidate();
        }
    }
    @Override
    public void hideLoading() {

    }


}
