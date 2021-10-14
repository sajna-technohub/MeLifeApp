package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.ramijemli.percentagechartview.PercentageChartView;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.SemiCircleProgressBarView;


public class ReportFragment extends Fragment {

    TextView skill_Txt_Reportname,skill_Txt_testdate,skill_Txt_mobile,skill_Txt_email,skill_Txt_description;
    CircularImageView skill_Img_profile_photo;
    ImageView skill_hexgraph;
   View v;
   TableLayout tableLayout;
   PercentageChartView view_id;
    SemiCircleProgressBarView semiCircleProgressBarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.skill_finder_report, container, false);
        v.setBackgroundColor(Color.WHITE);
        tableLayout=(TableLayout)v.findViewById(R.id.table);
//        view_id=(PercentageChartView)v.findViewById(R.id.view_id);
//        semiCircleProgressBarView=v.findViewById(R.id.progress);
//        for (int i=0;i<20;i++){//add list of items frm arraylist in json
//            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.table_layout,null,false);
//            TextView tv_no  = (TextView) tableRow.findViewById(R.id.tv_no);
//            TextView tv_date  = (TextView) tableRow.findViewById(R.id.tv_date);
//            TextView tv_orderid  = (TextView) tableRow.findViewById(R.id.tv_orderid);
//            TextView tv_quantity  = (TextView) tableRow.findViewById(R.id.tv_quantity);
//
//            tv_no.setText(""+(i+1));
//            tv_date.setText("2016-09-09");
//            tv_orderid.setText("A00"+(i+1));
//            tv_quantity.setText(""+(100+(i+1)));
//
//            tableLayout.addView(tableRow);
//        }
//        SemiCircleProgressBarView semiCircleProgressBarView = (SemiCircleProgressBarView)v.findViewById(R.id.progress);
//        semiCircleProgressBarView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        semiCircleProgressBarView.setClipping(70);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
