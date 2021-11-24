package com.technohub.melifeapp.views;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LabelsOverlapMode;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.technohub.melifeapp.Interfaces.ICommerce;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Commerce_Brief_Adapter;
import com.technohub.melifeapp.classes.Commerece_Desc_Career_Adapter;
import com.technohub.melifeapp.classes.MarkerViewClass;
import com.technohub.melifeapp.models.CommerceFinancial;
import com.technohub.melifeapp.models.CommerceRecord;
import com.technohub.melifeapp.models.CommerceResponse;
import com.technohub.melifeapp.models.CommerceTableSorted;
import com.technohub.melifeapp.models.HumanityChartData;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.presenter.CommercePresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



public class CommerceFragment extends Fragment implements ICommerce.View {

View v;
TextView commerce_Txt_Reportname,commerce_Txt_commcarrerdesc,commerce_Txt_age,commerce_Txt_edu,commerce_Txt_titlename,commerce_Txt_lowdesc,commerce_Txt_highdesc,commerce_Txt_avgdesc,commerce_Txt_Name,commerce_Txt_mobile,commerce_Txt_testdate,commerce_Txt_email,commerce_Txt_description,commerce_Txt_benefitsdesc;
TextView commerce_Txt_financialgraphtitle,commerce_Txt_nonfindesc,commerce_Txt_findesc,commerce_Txt_designdesc,commerce_Txt_howtointerprettitle,commerce_Txt_takingdesc,commerce_Txt_interpretdesc,commerce_Txt_titletable,commerce_Txt_descinteresttitle,commerce_Txt_high,commerce_Txt_avg,commerce_Txt_low;
ImageView commerce_Img_courceoptions,commerce_Img_taking;
    CircularImageView commerce_Img;
    RadarChart commerceradarChart;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    RadarData radarData,radarData1;
    RadarDataSet radarDataSet,radarDataSet1;
    ArrayList<String> labels = new ArrayList<String>();
    ArrayList<String> labels1 = new ArrayList<String>();
    ArrayList lineEntries;
RecyclerView commerce_list_brief,commerce_list_desccommerce;
    final Map<String, Float> axis = new LinkedHashMap<>(20);
TableLayout commerce_table;
    String[] mStringArray;
    MarkerViewClass mv;
    int examd,testd;
    ArrayList radarEntries,radarEntries1;
    ProgressDialog progressdialog;
    StreamFinderRequest skillReportRequest=new StreamFinderRequest();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.commerce_career_finder_report, container, false);
        v.setBackgroundColor(Color.WHITE);

        Bundle args = getArguments();
        mv = new MarkerViewClass (getContext(), R.layout.custom_marker_view_layout);
        if (args != null) {

            examd = getArguments().getInt("examid");
            testd = getArguments().getInt("testid");
            skillReportRequest.setUser_id(new LoginResponse().getSharedPreferences(getContext(), "userid"));
            skillReportRequest.setExam_id(examd+"");
            skillReportRequest.setTest_id(testd+"");
        }

        CommercePresenter commercePresenter=new CommercePresenter(this,skillReportRequest);
        commercePresenter.created();
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
//        commerce_Txt_Reportname=v.findViewById(R.id.commerce_Txt_Reportname);
        commerce_Txt_Name=v.findViewById(R.id.commerce_Txt_Name);
        commerce_Txt_highdesc=v.findViewById(R.id.commerce_Txt_highdesc);
        commerce_Txt_lowdesc=v.findViewById(R.id.commerce_Txt_lowdesc);
        commerce_Txt_avgdesc=v.findViewById(R.id.commerce_Txt_avgdesc);
        commerce_Txt_testdate=v.findViewById(R.id.commerce_Txt_testdate);
        commerce_Txt_mobile=v.findViewById(R.id.commerce_Txt_mobile);
        commerce_Txt_email=v.findViewById(R.id.commerce_Txt_email);
        commerce_Txt_edu=v.findViewById(R.id.commerce_Txt_education);
        commerce_Txt_age=v.findViewById(R.id.commerce_Txt_age);
        commerce_Img=v.findViewById(R.id.commerce_Img_profile_photo);
        commerce_Txt_description=v.findViewById(R.id.commerce_Txt_description);
        commerce_Txt_findesc=v.findViewById(R.id.commerce_Txt_findesc);
        commerce_Txt_nonfindesc=v.findViewById(R.id.commerce_Txt_nonfindesc);
        commerce_Txt_benefitsdesc=v.findViewById(R.id.commerce_Txt_benefitsdesc);
        commerce_Txt_commcarrerdesc=v.findViewById(R.id.commerce_Txt_commcarrerdesc);
//        commerce_Txt_financialgraphtitle=v.findViewById(R.id.commerce_Txt_financialgraphtitle);
//        commerce_Txt_howtointerprettitle=v.findViewById(R.id.commerce_Txt_howtointerprettitle);
        commerce_Txt_interpretdesc=v.findViewById(R.id.commerce_Txt_interpretdesc);
        commerceradarChart=v.findViewById(R.id.RadarChart);
//        commerce_Txt_descinteresttitle=v.findViewById(R.id.commerce_Txt_descinteresttitle);
//        commerce_Txt_high=v.findViewById(R.id.commerce_Txt_high);
//        commerce_Txt_avg=v.findViewById(R.id.commerce_Txt_avg);
//        commerce_Txt_low=v.findViewById(R.id.commerce_Txt_low);
//        commerce_Txt_titletable=v.findViewById(R.id.commerce_Txt_titletable);
        commerce_table=v.findViewById(R.id.commerce_table);
        commerce_list_brief=v.findViewById(R.id.commerce_list_brief);
        commerce_Txt_commcarrerdesc=v.findViewById(R.id.commerce_Txt_commcarrerdesc);
        commerce_list_desccommerce=v.findViewById(R.id.commerce_list_desccommerce);
        commerce_Img_courceoptions=v.findViewById(R.id.commerce_Img_courceoptions);
        commerce_Txt_takingdesc=v.findViewById(R.id.commerce_Txt_takingdesc);
        commerce_Img_taking=v.findViewById(R.id.commerce_Img_taking);
        commerce_Txt_designdesc=v.findViewById(R.id.commerce_Txt_designdesc);
//        commerce_Txt_titlename=v.findViewById(R.id.commerce_Txt_titlename);
        lineEntries = new ArrayList<>();
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);
    }

    @Override
    public void LoadCommerceReportData(CommerceResponse commerceResponse) {
//        commerce_Txt_Reportname.setText(commerceResponse.getRecord().getTest_details().getTest_name());
        commerce_Txt_testdate.setText(commerceResponse.getRecord().getUser_details().getC_date());
        commerce_Txt_Name.setText(commerceResponse.getRecord().getUser_details().getSt_name());
        commerce_Txt_age.setText(calculateAge(commerceResponse.getRecord().getUser_details().getSt_dob())+"");
        commerce_Txt_edu.setText(commerceResponse.getRecord().getUser_details().getSt_qualification());
        commerce_Txt_mobile.setText(commerceResponse.getRecord().getUser_details().getSt_mobile());
        commerce_Txt_email.setText(commerceResponse.getRecord().getUser_details().getSt_email());
        commerce_Txt_description.setText(Html.fromHtml(commerceResponse.getRecord().getTest_details().getCommerce_finder_test_report()));
        commerce_Txt_benefitsdesc.setText(Html.fromHtml(commerceResponse.getRecord().getTest_details().getBenefits_of_commerce_finder_test()));
//        commerce_Txt_commcarrerdesc.setText(Html.fromHtml(commerceResponse.getRecord().getResult1().getCOMMERCE_AS_YOUR_CAREER()));
//        commerce_Txt_financialgraphtitle.setText(Html.fromHtml(commerceResponse.getRecord().getGraph_data1().getTitle()));
//        commerce_Txt_howtointerprettitle.setText(Html.fromHtml(commerceResponse.getRecord().getGraph_data1().getHeading()));
        String text=commerceResponse.getRecord().getFinancial_careers().getBrief_description();
        int end = text.indexOf("Financial Careers ");
//    String s=img.substring(start, end);
        String s = text.substring(0, end);
        Log.e("comerceas",s);
        commerce_Txt_commcarrerdesc.setText(Html.fromHtml(s));
        commerce_Txt_interpretdesc.setText(Html.fromHtml(commerceResponse.getRecord().getGraph_data1().getDescription()));
        commerce_Txt_findesc.setText(Html.fromHtml(text.substring(end)));
        commerce_Txt_nonfindesc.setText(Html.fromHtml(commerceResponse.getRecord().getNon_financial_careers().getBrief_description()));

//        commerce_Txt_descinteresttitle.setText(Html.fromHtml(commerceResponse.getRecord().getPriority_description().get(0).getTitle()));
//        commerce_Txt_high.setText(Html.fromHtml(commerceResponse.getRecord().getPriority_description().get(0).getHigh()));
//        commerce_Txt_avg.setText(Html.fromHtml(commerceResponse.getRecord().getPriority_description().get(0).getAverage()));
//        commerce_Txt_low.setText(Html.fromHtml(commerceResponse.getRecord().getPriority_description().get(0).getLow()));
//        Picasso.get().load("http://"+commerceResponse.getRecord().getTest_details().getIcon()).into(commerce_Img);
//        commerce_Txt_titletable.setText(Html.fromHtml(commerceResponse.getRecord().getTabledata_sorted().get(0).getTitle_1()));

        LoadDescriptiontable(commerceResponse.getRecord());

        commerce_Txt_highdesc.setText(commerceResponse.getRecord().getPriority_description().get(0).getHigh());
        commerce_Txt_avgdesc.setText(commerceResponse.getRecord().getPriority_description().get(0).getAverage());
        commerce_Txt_lowdesc.setText(commerceResponse.getRecord().getPriority_description().get(0).getLow());

       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
       Commerce_Brief_Adapter commerce_brief_adapter=new Commerce_Brief_Adapter(this,commerceResponse.getRecord().getTabledata_sorted(),getContext());
        commerce_list_brief.setLayoutManager(layoutManager);
        commerce_list_brief.setAdapter(commerce_brief_adapter);
        commerce_brief_adapter.notifyDataSetChanged();


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Commerece_Desc_Career_Adapter commerece_desc_career_adapter=new Commerece_Desc_Career_Adapter(this,commerceResponse.getRecord().getTabledata_sorted(),getContext());
        commerce_list_desccommerce.setLayoutManager(layoutManager1);
        commerce_list_desccommerce.setAdapter(commerece_desc_career_adapter);
        commerece_desc_career_adapter.notifyDataSetChanged();


        Log.e("commerce img",commerceResponse.getRecord().getCourse_Options_in_Commerce());
        Picasso.get().load(commerceResponse.getRecord().getCourse_Options_in_Commerce()).into(commerce_Img_courceoptions);
        int imgend=commerceResponse.getRecord().getTest_details().getWhats_next().indexOf("<img ");
        commerce_Txt_takingdesc.setText(Html.fromHtml(commerceResponse.getRecord().getTest_details().getWhats_next().substring(0,imgend)));
        commerce_Txt_designdesc.setText(Html.fromHtml(commerceResponse.getRecord().getTest_details().getTest_design()));
//        Picasso.get().load("http://3.7.48.112/ckfinder/userfiles/files/What's%20Next(5).png").into(commerce_Img_taking);
//        commerce_Txt_titlename.setText(new LoginResponse().getSharedPreferences(getContext(),"name"));
            progressdialog.dismiss();
    }
    private ArrayList<IBarDataSet> getDataSet(CommerceRecord record) {
        ArrayList<IBarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        int i=0;
        for(CommerceTableSorted f:record.getRadar_chart_financial())
        {
            valueSet1.add(new BarEntry(f.getY(),i));
            i++;
        }
        for(CommerceTableSorted f:record.getRadar_chart_non_financial())
        {
            valueSet2.add(new BarEntry(f.getY(),i));
            i++;
        }
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Financial Careers");
        barDataSet1.setColor(getResources().getColor(R.color.color_greygradient));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Non-Financial Careers");
        barDataSet1.setColor(getResources().getColor(R.color.color_redgradient));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues(CommerceRecord record) {

      for(CommerceTableSorted t:record.getRadar_chart_financial())
      {
          labels.add(t.getSub_dimension());
      }
        for(CommerceTableSorted t:record.getRadar_chart_non_financial())
        {
            labels.add(t.getSub_dimension());
        }
        return labels;
    }
    void setvalues_hori_chart(CommerceRecord record)
    {
        HorizontalBarChart mChart = v.findViewById(R.id.barchart_hori);

//        BarData data = new BarData(getDataSet(record));
//        mChart.setData(data);
//        mChart.animateXY(2000, 2000);
//        mChart.invalidate();

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        int i=0;
        for (CommerceTableSorted s:record.getTabledata_sorted()){
            yVals1.add(new BarEntry(i, Float.parseFloat(s.getSub_dimension_score())));
            labels.add(s.getSub_dimension()); //Dynamic x-axis labels
            i++;
        }

        for(String s:labels)
        {
            Log.e("labelss",s);
        }
//
        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
//        CategoryBarChartXaxisFormatter xaxisFormatter = new CategoryBarChartXaxisFormatter(labels);
        xl.setValueFormatter((value, axis) -> labels.get((int) value));
        xl.setGranularity(1);

        YAxis yl = mChart.getAxisLeft();
        yl.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setAxisMinimum(0f);

        YAxis yr = mChart.getAxisRight();
        yr.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);


        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "");
        set1.setColor(Color.RED);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(.2f);
        mChart.setData(data);
        mChart.getLegend().setEnabled(false);
        mChart.notifyDataSetChanged();
        mChart.invalidate();

    }

    void LoadDescriptiontable(CommerceRecord record)
    {
        float i=0;
        radarEntries = new ArrayList<>();
        radarEntries1 = new ArrayList<>();

        for (CommerceTableSorted s:record.getTabledata_sorted()){//add list of items frm arraylist in json

            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.commerce_table_scoretable,null,false);
            TextView commerce_Txt_branch = (TextView) tableRow.findViewById(R.id.commerce_Txt_branch);
            TextView commerce_Txt_score  = (TextView) tableRow.findViewById(R.id.commerce_Txt_score);
            TextView commerce_Txt_inlevel  = (TextView) tableRow.findViewById(R.id.commerce_Txt_inlevel);
            TextView commerce_Txt_career  = (TextView) tableRow.findViewById(R.id.commerce_Txt_career);


            commerce_Txt_branch.setText(s.getSub_dimension());
            commerce_Txt_score.setText(s.getSub_dimension_score()+"");
            commerce_Txt_inlevel.setText(Html.fromHtml(s.getPriority_level()));
            commerce_Txt_career.setText(Html.fromHtml(s.getSub_dimension_career()));
            commerce_table.addView(tableRow);

//            axis.put(s.getSub_dimension(), i);
//            radarEntries.add(new RadarEntry(i,s.getSub_dimension() ));
//            labels.add(s.getSub_dimension()); //Dynamic x-axis labels
//            i=i+1;
        }

        for(CommerceTableSorted t:record.getRadar_chart_non_financial())
        {
            radarEntries1.add(new RadarEntry(t.getY(),t.getName()));
            labels1.add(t.getName());
            Log.e("nonfin",t.getName()+" "+t.getY());
        }
        for(CommerceTableSorted t:record.getRadar_chart_financial())
        {
            radarEntries.add(new RadarEntry(t.getY(),t.getName()));
            labels.add(t.getName());
            Log.e("fin",t.getName()+" "+t.getY());
        }
        radarDataSet1 = new RadarDataSet(radarEntries1, "Non-Financial Careers");
        radarDataSet1.setColor(R.color.color_redgradient);
        radarDataSet1.setLabel("Non-Financial Careers");
        radarDataSet1.setValueTextColor(Color.rgb(55, 70, 73));
        radarDataSet1.setValueTextSize(10f);
//        radarDataSet1.setDrawFilled(true);
//        Drawable drawable1 = ContextCompat.getDrawable(getContext(), R.drawable.radargrey);
//        radarDataSet1.setFillDrawable(drawable1);

        radarDataSet = new RadarDataSet(radarEntries, "Financial Careers");
        radarDataSet.setColor(R.color.color_greygradient);
        radarDataSet.setValueTextColor(Color.rgb(55, 70, 73));
        radarDataSet.setValueTextSize(10f);
        radarDataSet.setLabel("Financial Careers");
//        radarDataSet.setDrawFilled(true);
//        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.radarred);
//        radarDataSet.setFillDrawable(drawable);



        ArrayList dataSets = new ArrayList<>();
        dataSets.add(radarDataSet);
        dataSets.add(radarDataSet1);
        radarData = new RadarData(dataSets);


        commerceradarChart.setData(radarData);

        commerceradarChart.notifyDataSetChanged();
        commerceradarChart.invalidate();
//        commerceradarChart.setMarker(mv);
//        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        radarDataSet.setValueTextColor(Color.BLACK);
//        radarDataSet.setValueTextSize(10f);
        commerceradarChart.setTouchEnabled(true);
//        commerceradarChart.setAxis(axis);
//        commerceradarChart.setAxisMax(2855.681F);         // set max value for the chart
//        commerceradarChart.addOrReplace("WI", 2855.681F); // add new axis
//        commerceradarChart.addOrReplace("OH", 281.59F);   // change the existing value
//        commerceradarChart.setAutoSize(true);             // auto balance the chart
//        commerceradarChart.setCirclesOnly(true);
//        commerceradarChart.setTextSize(10);
        setvalues_hori_chart(record);
//        setanychart_bar(record);


         }

    void setanychart_bar(CommerceRecord record)
        {
            AnyChartView anyChartView = v.findViewById(R.id.hori_barchart_any);

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

            for(CommerceTableSorted f:record.getRadar_chart_financial())
            {
                Log.e("hai",f.getName());
                seriesData.add(new CustomDataEntry(f.getSub_dimension(), Integer.parseInt(f.getSub_dimension_score()),Integer.parseInt(f.getSub_dimension_score())));
            }
            for(CommerceTableSorted f:record.getRadar_chart_non_financial())
            {
                seriesData.add(new CustomDataEntry(f.getSub_dimension(), Integer.parseInt(f.getSub_dimension_score()),Integer.parseInt(f.getSub_dimension_score())));
            }


            Bar series1 = barChart.bar(seriesData);
            series1.color("#FF0000");
            series1.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER);

            barChart.legend().enabled(true);
            barChart.legend().maxHeight(1f);
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
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void LoadReportsList(TestcategoryResponse testcategoryResponse) {

    }

    @Override
    public void loadfragment(int testid,int examid) {

    }
}
