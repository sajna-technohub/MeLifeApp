package com.technohub.melifeapp.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.technohub.melifeapp.Interfaces.Itestcategory;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.ExamRequest;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.presenter.TestCategoryPresenter;

import java.util.Random;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
   TestcategoryResponse testDataModel;
   View view1;
   Context context;
   ImageView testImageicon;
   ExamRequest examRequest;
    private Itestcategory.View view;
    public TestAdapter(TestcategoryResponse testDataModel,Itestcategory.View view,Context con) {

        this.testDataModel = testDataModel;
        this.view=view;
        this.context=con;
    }

    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {

        view1  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view1);

    }

    @Override
    public void onBindViewHolder(TestAdapter.ViewHolder Viewholder, int i) {

        examRequest=new ExamRequest();
        Viewholder.TestTitle.setText(testDataModel.getData().get(i).test_name);
        String test_id=testDataModel.getData().get(i).getTest_id()+"";
        Log.e("repoadapter_test_id",test_id);
        String examid=testDataModel.getData().get(i).getExam_id()+"";
        Log.e("adapter_exam_id",examid);
        String com=testDataModel.getData().get(i).getCmplts_status()+"";
        Log.e("adaptergetcompsttaus",com);
        String userid=testDataModel.getUserid()+"";
        Log.e("adapter_userid",userid);
        Viewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "click");
                examRequest.setTest_id(test_id);
                examRequest.setUser_id(userid);
                examRequest.setUser_email(testDataModel.getUseremail());
                examRequest.setDeviceToken("1");
                examRequest.setDeviceType("gkg");
                if (com!=null) {
                    if (testDataModel.getData().get(i).getCmplts_status().equals("Y")) {
                        Toast.makeText(context, "Test Already Attended", Toast.LENGTH_SHORT).show();
                    } else if(testDataModel.getData().get(i).getCmplts_status().equals("N")) {
                        view.showLoading();
                        new TestCategoryPresenter(examRequest, view).initiateExam();
                    }
                }
            }
        });

        if(test_id.equals("8"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.brain));
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
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.engineer));
        } if(test_id.equals("12"))
        {

        } if(test_id.equals("13"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.careerfinder));
        }
        if(test_id.equals("27"))
        {
            testImageicon.setImageDrawable(context.getResources().getDrawable(R.drawable.ai));
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
;
        }
    }
    }


