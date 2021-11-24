package com.technohub.melifeapp.views;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alespero.expandablecardview.ExpandableCardView;
import com.technohub.melifeapp.Interfaces.IFaq;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.FaqAdapter;
import com.technohub.melifeapp.classes.Helper;
import com.technohub.melifeapp.models.FaqResponse;
import com.technohub.melifeapp.presenter.FaqPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FaqFragment extends Fragment implements IFaq.View {

    FaqPresenter faqPresenter;
    RecyclerView faqlist;
    View v;
    ImageView faqBtnBack;
    FaqAdapter faqAdapter;
    LinearLayout whitefaqlayout;



    @Override
    public void init() {

        faqlist=  v. findViewById(R.id.faqlist);
        whitefaqlayout=  v. findViewById(R.id.whitefaqlayout);
        faqBtnBack=  v. findViewById(R.id.faqBtnBack);
    }

    @Override
    public void LoadFaq(FaqResponse faqResponse) {

        faqlist.setHasFixedSize(true);
        faqlist.setLayoutManager(new LinearLayoutManager(getContext()));
        faqAdapter = new FaqAdapter(this,faqResponse.getData().getQuestion(),faqResponse.getData().getAnswer(),getContext());
        whitefaqlayout.setVisibility(View.VISIBLE);
        faqlist.setAdapter(faqAdapter);
    }

    @Override
    public void initClicks() {
        faqBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),DashBoardActivity.class));
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_faq, container, false);
        v.setBackgroundColor(Color.WHITE);


              faqPresenter=new FaqPresenter(this);
              faqPresenter.created();
              faqPresenter.loadfaqData();





        return v;
    }

}
