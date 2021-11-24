package com.technohub.melifeapp.views;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;
import com.technohub.melifeapp.Interfaces.IStream;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Helper;
import com.technohub.melifeapp.classes.StreamDescriptionAdapter;
import com.technohub.melifeapp.classes.Stream_descMultiple_Adapter;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.StreamFinderAptitude;
import com.technohub.melifeapp.models.StreamFinderInterest;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.models.StreamFinderResponse;
import com.technohub.melifeapp.models.StreamPieChartResponse;
import com.technohub.melifeapp.models.StreamResult2;
import com.technohub.melifeapp.presenter.StreamReportPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamFragment extends Fragment implements IStream.View {
TextView stream_Txt_Reportname,stream_Txtdisclaimer,stream_Txt_age,stream_Txt_edu,stream_Txt_Name,stream_Txt_mobile,stream_Txt_email,stream_Txt_description,stream_Txt_identifydesc,stream_Txt_descriptionhowto;
TextView stream_Txt_streamdesc,stream_Txt_titlename,stream_Txtpublicdesc,stream_Txt_multipletitle,stream_Txt_multipledesc,stream_Txt_graphtitle,stream_Txt_whatnext,stream_Txt_scoretitle,stream_Txt_multipledesctitle;
View v;
ImageView streamwhatImg,streamlogo;
TableLayout table_stream_miltiplegraph;
RecyclerView streamlistview,streamlistdescmultiple;
BarChart barChart,streammultiplebarchart;
TextView stream_Txt_testdate;
ProgressDialog progressdialog;
int testd,examd;
StreamFinderRequest skillReportRequest=new StreamFinderRequest();
    private static final String TAG = MainActivity.class.getName();
    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
    float defaultBarWidth = -1;
    List<String> xAxisValues = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v= inflater.inflate(R.layout.stream_finder_report, container, false);
        v.setBackgroundColor(Color.WHITE);
        Bundle args = getArguments();

        if (args != null) {
            examd = getArguments().getInt("examid");
            testd = getArguments().getInt("testid");
            skillReportRequest.setUser_id(new LoginResponse().getSharedPreferences(getContext(), "userid"));
            skillReportRequest.setExam_id(examd+"");
            skillReportRequest.setTest_id(testd+"");
        }

        StreamReportPresenter  streamReportPresenter=new StreamReportPresenter(this,skillReportRequest);
        streamReportPresenter.created();
        streamReportPresenter.piechart(new LoginResponse().getSharedPreferences(getContext(), "userid"),testd+"",examd+"");
        progressdialog.show();
        return v;
    }

    @Override
    public void init() {

        stream_Txt_Reportname=v.findViewById(R.id.stream_Txt_Reportname);
        stream_Txtdisclaimer=v.findViewById(R.id.stream_Txtdisclaimer);
        stream_Txt_testdate=v.findViewById(R.id.stream_Txt_testdate);
        stream_Txt_Name=v.findViewById(R.id.stream_Txt_Name);
        stream_Txt_age=v.findViewById(R.id.stream_Txt_Age);
        stream_Txt_edu=v.findViewById(R.id.stream_Txt_Education);
        stream_Txt_mobile=v.findViewById(R.id.stream_Txt_mobile);
        stream_Txt_email=v.findViewById(R.id.stream_Txt_email);
        stream_Txt_description=v.findViewById(R.id.stream_Txt_description);
        stream_Txt_identifydesc=v.findViewById(R.id.stream_Txt_identifydesc);
        stream_Txt_descriptionhowto=v.findViewById(R.id.stream_Txt_descriptionhowto);
        barChart=v.findViewById(R.id.streambarchart);
        stream_Txt_streamdesc=v.findViewById(R.id.stream_Txt_streamdesc);
        streamlistview=v.findViewById(R.id.streamlistview);
        stream_Txt_multipletitle=v.findViewById(R.id.stream_Txt_multipletitle);
        stream_Txt_multipledesc=v.findViewById(R.id.stream_Txt_multipledesc);
//        stream_Txt_graphtitle=v.findViewById(R.id.stream_Txt_graphtitle);
//        streammultiplebarchart=v.findViewById(R.id.streammultiplebarchart);
//        stream_Txt_scoretitle=v.findViewById(R.id.stream_Txt_scoretitle);
//        table_stream_miltiplegraph=v.findViewById(R.id.table_stream_miltiplegraph);
//        stream_Txt_multipledesctitle=v.findViewById(R.id.stream_Txt_multipledesctitle);
//        streamlistdescmultiple=v.findViewById(R.id.streamlistdescmultiple);
        stream_Txt_whatnext=v.findViewById(R.id.stream_Txt_whatnext);
        streamwhatImg=v.findViewById(R.id.streamwhatImg);
        stream_Txtpublicdesc=v.findViewById(R.id.stream_Txtpublicdesc);
//        streamlogo=v.findViewById(R.id.streamlogo);
//        stream_Txt_titlename=v.findViewById(R.id.stream_Txt_titlename);
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);

    }

    @Override
    public void LoadPieChartData(StreamPieChartResponse streamFinderResponse) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        StreamDescriptionAdapter streamDescriptionAdapter=new StreamDescriptionAdapter(this,streamFinderResponse.getRecord(),getContext());
        streamlistview.setAdapter(streamDescriptionAdapter);
        streamlistview.setLayoutManager(layoutManager);
        streamDescriptionAdapter.notifyDataSetChanged();
    }

    private void setChart(int size, StreamFinderResponse streamFinderResponse) {

        List<BarEntry> incomeEntries = getIncomeEntries(size,streamFinderResponse);
        List<BarEntry> expenseEntries = getExpenseEntries(size,streamFinderResponse);
        dataSets = new ArrayList<>();
        BarDataSet set1, set2;

        set1 = new BarDataSet(incomeEntries, "Interest");
        set1.setColor(Color.rgb(126, 191, 54));
        set1.setValueTextColor(Color.rgb(55, 70, 73));
        set1.setValueTextSize(10f);

        set2 = new BarDataSet(expenseEntries, "Aptitude");
        set2.setColors(Color.rgb(132, 35, 156));
        set2.setValueTextColor(Color.rgb(55, 70, 73));
        set2.setValueTextSize(10f);

        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(10);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);


        Legend l = barChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setTextSize(14);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMaximum(size);//edited

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTypeface(Typeface.DEFAULT);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);

        setBarWidth(data, size);
        barChart.invalidate();

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
                barChart.getXAxis().setAxisMinimum(0);
                barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
                barChart.getXAxis().setCenterAxisLabels(true);
            } else {
                Toast.makeText(getContext(), "no of bar groups is " + groupCount, Toast.LENGTH_SHORT).show();
            }

            barChart.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping
            barChart.invalidate();
        }
    }

    private List<BarEntry> getExpenseEntries(int size,StreamFinderResponse streamFinderResponse) {
        ArrayList<BarEntry> expenseEntries = new ArrayList<>();
        int i=1;
        for(StreamFinderInterest s:streamFinderResponse.getRecord().getStream_finder_interest())
        {
            expenseEntries.add(new BarEntry(i,s.getSub_dimension_score()));
            i++;
        }
        return expenseEntries.subList(0, size);
    }

    private List<BarEntry> getIncomeEntries(int size,StreamFinderResponse streamFinderResponse) {
        ArrayList<BarEntry> incomeEntries = new ArrayList<>();

        int i=1;
        for(StreamFinderAptitude s:streamFinderResponse.getRecord().getStream_finder_aptitude())
        {
            incomeEntries.add(new BarEntry(i,s.getSub_dimension_score()));
            i++;
        }

        return incomeEntries.subList(0, size);
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void LoadStreamReportData(StreamFinderResponse streamFinderResponse) {
//        stream_Txt_Reportname.setText(streamFinderResponse.getRecord().getTest_details().getTest_name());
        stream_Txt_age.setText(streamFinderResponse.getRecord().getUser_details().getSt_dob());
        stream_Txtdisclaimer.setText(streamFinderResponse.getRecord().getTest_details().getDisclaimer());
        stream_Txt_edu.setText(streamFinderResponse.getRecord().getUser_details().getSt_qualification());
        stream_Txt_Name.setText(streamFinderResponse.getRecord().getUser_details().getSt_name());
        stream_Txt_testdate.setText(streamFinderResponse.getRecord().getUser_details().getC_date());
        stream_Txt_mobile.setText(streamFinderResponse.getRecord().getUser_details().getSt_mobile());
        stream_Txt_email.setText(streamFinderResponse.getRecord().getUser_details().getSt_email());
        stream_Txt_description.setText(Html.fromHtml(streamFinderResponse.getRecord().getTest_details().getStream_finder_test_report()));
        stream_Txt_identifydesc.setText(Html.fromHtml(streamFinderResponse.getRecord().getTest_details().getIdentify_your_right_stream()));
//        Picasso.get().load("http://"+streamFinderResponse.getRecord().getTest_details().getTest_icon()).into(streamlogo);
        stream_Txt_descriptionhowto.setText(Html.fromHtml(streamFinderResponse.getRecord().getHow_to_inteprete()));
        Log.e("log",streamFinderResponse.getRecord().getResult1().getDetail_description());
        stream_Txt_multipledesc.setText(Html.fromHtml(streamFinderResponse.getRecord().getResult1().getDetail_description()));
        for(StreamFinderInterest s:streamFinderResponse.getRecord().getStream_finder_interest()) {
            xAxisValues.add(s.getSub_dimension1());
        }
        setChart(streamFinderResponse.getRecord().getStream_finder_interest().size(),streamFinderResponse);

        stream_Txt_streamdesc.setText(streamFinderResponse.getRecord().getStream_finder_aptitude().get(0).getTitle2());
        stream_Txt_streamdesc.setText(streamFinderResponse.getRecord().getStream_finder_aptitude().get(0).getTitle2());




//        stream_Txt_multipletitle.setText(streamFinderResponse.getRecord().getResult2().get(0).getTitle1());
//
//        stream_Txt_graphtitle.setText(streamFinderResponse.getRecord().getResult2().get(0).getTitle2());

        ArrayList multipleval = new ArrayList();
        ArrayList<String> multipletop = new ArrayList<String>();
        int i=0;
        for(StreamResult2 s:streamFinderResponse.getRecord().getResult2())
        {
            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.stream_multiple_table,null,false);
            TextView streamintelli = (TextView) tableRow.findViewById(R.id.stream_Txt_tableintelli);
            TextView streamscore  = (TextView) tableRow.findViewById(R.id.stream_Txt_tablescore);
            TextView stream  = (TextView) tableRow.findViewById(R.id.stream_Txt_tablestream);
            TextView streamcareer  = (TextView) tableRow.findViewById(R.id.stream_Txt_tablecareer);


            streamintelli.setText(s.getSub_dimension1());
            streamscore.setText(s.getSub_dimension_score()+"");
            stream.setText(s.getStream());
            streamcareer.setText(Html.fromHtml(s.getSub_dimension_career()));//notcompleated

//            table_stream_miltiplegraph.addView(tableRow);

            multipleval.add(new BarEntry(i,s.getSub_dimension_score()));
            multipletop.add(s.getSub_dimension());
            i=i+50;
        }
        BarDataSet bardataset = new BarDataSet(multipleval, "Multiple Intelligence");
//        streammultiplebarchart.animateY(5000);

        BarData data = new BarData( bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        data.setBarWidth(10f);
//        streammultiplebarchart.setData(data);

//        stream_Txt_scoretitle.setText(streamFinderResponse.getRecord().getResult2().get(0).getTitle3());
//        stream_Txt_multipledesctitle.setText(streamFinderResponse.getRecord().getResult2().get(0).getTitle4());
        stream_Txt_whatnext.setText(Html.fromHtml(streamFinderResponse.getRecord().getTest_details().getWhats_next()));
        stream_Txtpublicdesc.setText(Html.fromHtml(streamFinderResponse.getRecord().getTest_details().getTest_design()));

//        Stream_descMultiple_Adapter stream_descMultiple_adapter=new Stream_descMultiple_Adapter(this,streamFinderResponse.getRecord().getResult2(),getContext());
//        streamlistdescmultiple.setAdapter(stream_descMultiple_adapter);
//        Helper.getListViewSize(streamlistdescmultiple);

        Picasso.get().load("http://3.7.48.112/ckfinder/userfiles/files/What's%20Next(5).png").into(streamwhatImg);
//        stream_Txt_titlename.setText(new LoginResponse().getSharedPreferences(getContext(),"name"));
        progressdialog.dismiss();
    }

}
