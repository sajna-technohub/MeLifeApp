package com.technohub.melifeapp.views;


import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.RadarMarkVew;
import com.technohub.melifeapp.models.CommerceRecord;
import com.technohub.melifeapp.models.CommerceTableSorted;
import com.technohub.melifeapp.models.LoadQuestionResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadarFragment extends Fragment {


View v;
CommerceRecord record;
    private RadarChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        v.setBackgroundColor(Color.WHITE);
        Bundle args=getArguments();
        if(args!=null)
        {
            record = (CommerceRecord) args.getParcelable("radardata");
        }
        chart = v.findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkVew(getContext(), R.layout.custom_marker_view_layout);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        setData(record);
//        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private final String[] mActivities = new String[]{"Burger", "Steak", "Salad", "Pasta", "Pizza"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);

        return v;
    }
void setData(CommerceRecord record)
{
    float mul = 80;
    float min = 20;
    int cnt = 5;

    ArrayList<RadarEntry> entries1 = new ArrayList<>();
    ArrayList<RadarEntry> entries2 = new ArrayList<>();

    for(CommerceTableSorted c:record.getRadar_chart_financial())
    {
        entries1.add(new RadarEntry(c.getY()));
    }
    for(CommerceTableSorted c:record.getRadar_chart_non_financial())
    {
        entries2.add(new RadarEntry(c.getY()));
    }

    // NOTE: The order of the entries when being added to the entries array determines their position around the center of
    // the chart.
//    for (int i = 0; i < cnt; i++) {
//        float val1 = (float) (Math.random() * mul) + min;
//        entries1.add(new RadarEntry(val1));
//
//        float val2 = (float) (Math.random() * mul) + min;
//        entries2.add(new RadarEntry(val2));
//    }

    RadarDataSet set1 = new RadarDataSet(entries1, "Financial careers");
    set1.setFillColor(getResources().getColor(R.color.color_greygradient));
    set1.setDrawFilled(true);
    set1.setFillAlpha(180);
    set1.setLineWidth(2f);
    set1.setDrawHighlightCircleEnabled(true);
    set1.setDrawHighlightIndicators(false);

    RadarDataSet set2 = new RadarDataSet(entries2, "Non-Financial Careers");
    set2.setFillColor(getResources().getColor(R.color.color_redgradient));
    set2.setDrawFilled(true);
    set2.setFillAlpha(180);
    set2.setLineWidth(2f);
    set2.setDrawHighlightCircleEnabled(true);
    set2.setDrawHighlightIndicators(false);

    ArrayList<IRadarDataSet> sets = new ArrayList<>();
    sets.add(set1);
    sets.add(set2);

    RadarData data = new RadarData(sets);
    data.setValueTextSize(8f);
    data.setDrawValues(false);
    data.setValueTextColor(Color.WHITE);

    chart.setData(data);
    chart.invalidate();
}

}
