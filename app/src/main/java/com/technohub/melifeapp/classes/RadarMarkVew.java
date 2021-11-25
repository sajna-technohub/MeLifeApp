package com.technohub.melifeapp.classes;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.technohub.melifeapp.R;

import java.text.DecimalFormat;

public class RadarMarkVew extends MarkerView {


        private final TextView tvContent;
        private final DecimalFormat format = new DecimalFormat("##0");

        public RadarMarkVew(Context context, int layoutResource) {
            super(context, layoutResource);

            tvContent = findViewById(R.id.tvContent);
//            tvContent.setTypeface(Typeface.createFromAsset(context.getAssets(), "goodtimesrg.ttf"));
        }

        // runs every time the MarkerView is redrawn, can be used to update the
        // content (user-interface)
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            tvContent.setText(String.format("%s %%", format.format(e.getY())));

            super.refreshContent(e, highlight);
        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
        }
    }

