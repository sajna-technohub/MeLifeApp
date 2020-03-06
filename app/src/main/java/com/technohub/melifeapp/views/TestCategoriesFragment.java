package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.Interfaces.Itestcategory;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.TestAdapter;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.models.Tests;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.presenter.TestCategoryPresenter;



public class TestCategoriesFragment extends Fragment implements Itestcategory.View {
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    SpinKitView testSpinkit;
    TestCategoryPresenter testCategoryPresenter;
    TestcategoryResponse testcategoryResponse=new TestcategoryResponse();
    View v;
    String email;
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        v=inflater.inflate(R.layout.fragment_test__categories, container, false);

        v.setBackgroundColor(Color.WHITE);

         email=new LoginResponse().getSharedPreferences(getContext(),"email");
         userid=new LoginResponse().getSharedPreferences(getContext(),"userid");

         User user=new User();
        user.setUser_email(email);
        user.setUserid(userid);

        testCategoryPresenter = new TestCategoryPresenter(this,user);
        testCategoryPresenter.created();

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    @Override
    public void initClicks()
    {

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void loadNoQnsFragment()
    {
        Log.e("load","No qn fragment");
        ShowNoQuestionsFragment showNoQuestionsFragment=new ShowNoQuestionsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.testcatlayout, showNoQuestionsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void loadExamFragment(String exam_id,String test_id) {
        Log.e("load","exam fragment");
        ExamFragment examFragment=new ExamFragment();
        Bundle bundle = new Bundle();
        bundle.putString("exam_id",exam_id);
        bundle.putString("test_id",test_id);
        examFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.testcatlayout, examFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    @Override
    public void loadReportFragment() {
        Log.e("load","exam fragment");
        ReportFragment reportFragment=new ReportFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.testcatlayout, reportFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void loadTestList(TestcategoryResponse testlist) {
        Log.e("load","testlist");
        this.testcategoryResponse=testlist;

        RecyclerView.Adapter adapter = new TestAdapter(testcategoryResponse,this,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {

        testSpinkit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        testSpinkit.setVisibility(View.GONE);
    }

    @Override
    public void init() {
        testSpinkit=v.findViewById(R.id.testSpinkit);

        recyclerView = (RecyclerView)v.findViewById(R.id.test_recycler_view);
    }
}
