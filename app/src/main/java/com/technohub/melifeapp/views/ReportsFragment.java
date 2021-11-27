package com.technohub.melifeapp.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.Interfaces.ICommerce;
import com.technohub.melifeapp.Interfaces.Itestcategory;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.ReportAdapter;
import com.technohub.melifeapp.classes.TestAdapter;
import com.technohub.melifeapp.models.CommerceResponse;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.TestCategoriesModel;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.models.Tests;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.presenter.ReportListPresenter;
import com.technohub.melifeapp.presenter.TestCategoryPresenter;

import java.util.ArrayList;

public class ReportsFragment extends Fragment implements ICommerce.View {
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    SpinKitView reportSpinkit;
    TextView reportTxtmsg;
    LinearLayout whitelayout;
    ReportListPresenter reportListPresenter;
    View v;
    ImageView reportBtnBack;
    String email;
    Fragment fragment;
    String userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_reports, container, false);

        v.setBackgroundColor(Color.WHITE);

        email=new LoginResponse().getSharedPreferences(getContext(),"email");
        userid=new LoginResponse().getSharedPreferences(getContext(),"userid");

        Log.e("reports",email);
        Log.e("reports",userid);

        User testCategoriesModel=new User();
        testCategoriesModel.setUser_email(email);
        testCategoriesModel.setUser_id(userid);


        reportListPresenter = new ReportListPresenter(this,email,userid);
        reportListPresenter.created();

        reportBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),DashBoardActivity.class));
            }
        });

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    @Override
    public void init() {

        reportSpinkit=v.findViewById(R.id.reportSpinkit);
        recyclerView = (RecyclerView)v.findViewById(R.id.report_recycler_view);
        reportTxtmsg =v.findViewById(R.id.reportTxtmsg);
        reportBtnBack =v.findViewById(R.id.reportBtnBack);
        whitelayout =v.findViewById(R.id.whitelayout);
    }

    @Override
    public void loadfragment(int testid,int examid)
    {
        if (testid==8) {
            fragment=new SkillReportFragment();
            loadFragment(fragment,examid,8);
        }
        else if(testid==9)
        {
            fragment=new StreamFragment();
            loadFragment(fragment,examid,9);
        }
        else if(testid==10)
        {
            fragment=new CommerceFragment();
            loadFragment(fragment,examid,10);
        }
        else if(testid==11)
        {
            fragment=new EngineeringFragment();
            loadFragment(fragment,examid,11);
        }
        else if(testid==12)
        {
            fragment=new HumanityFragment();
            loadFragment(fragment,examid,12);
        }
        else if(testid==27)
        {
            fragment=new AI_Job_Fragment();
            loadFragment(fragment,examid,27);
        }
        else if(testid==13)
        {
            fragment=new RightCareerFragment();
            loadFragment(fragment,examid,13);
        }
    }
    private boolean loadFragment(Fragment fragment,int examid,int testid) {
        //switching fragment
        Bundle args = new Bundle();
        if (fragment != null)
        { args.putInt("examid", examid);
          args.putInt("testid", testid);
            fragment.setArguments(args);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homelayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void LoadCommerceReportData(CommerceResponse commerceResponse) {

    }

    @Override
    public void LoadReportsList(TestcategoryResponse testlist) {
        ArrayList<Tests> tests=new ArrayList<>();
for(Tests t:testlist.getData())
{
    if(t.getCmplts_status().equals("Y"))
    {
       tests.add(t);
    }
}
        RecyclerView.Adapter adapter = new ReportAdapter(tests,this,getContext());
        whitelayout.setVisibility(View.VISIBLE);

        if(adapter.getItemCount()!=0)
{
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
}else {
            reportTxtmsg.setVisibility(View.VISIBLE);

//        recyclerView.setAdapter(adapter);
        }
       Log.e("adapter.getItemCount()",adapter.getItemCount()+"");

    }


}
