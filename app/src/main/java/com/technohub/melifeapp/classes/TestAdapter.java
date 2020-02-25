package com.technohub.melifeapp.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.TestcategoryResponse;

import java.util.Random;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
   TestcategoryResponse testDataModel;
   View view1;
   Context context;
   ImageView testImageicon;

    public TestAdapter(TestcategoryResponse testDataModel,Context con) {

        this.testDataModel = testDataModel;
        this.context=con;
    }

    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        view1  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view1);

    }

    @Override
    public void onBindViewHolder(TestAdapter.ViewHolder Viewholder, int i) {

        Viewholder.TestTitle.setText(testDataModel.getData().get(i).test_name);
        String test_id=testDataModel.getData().get(i).getTest_id()+"";
        Log.e("adapter",test_id);
        String examid=testDataModel.getData().get(i).getExam_id()+"";
        Log.e("adapter",examid);
        String com=testDataModel.getData().get(i).getCmplts_status()+"";
        Log.e("adapter",com);
        if(test_id.equals("8"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.skillfinder));
        }
        if(test_id.equals("9"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.stream));
        }
        if(test_id.equals("10"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.commerce));
        }
        if(test_id.equals("11"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.enggbranch));
        } if(test_id.equals("12"))
        {

        } if(test_id.equals("13"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.rightcareer));
        }
        if(test_id.equals("27"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.aljobrisk));
        }
    }

    @Override
    public int getItemCount() {

        return testDataModel.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView TestTitle;
        CardView cardView;
        public ViewHolder(View view) {

            super(view);

            TestTitle = (TextView)view.findViewById(R.id.textTesttitle);
             cardView=(CardView) view.findViewById(R.id.card_view);
             testImageicon=(ImageView)view.findViewById(R.id.testImageicon);
        }
    }
    }


