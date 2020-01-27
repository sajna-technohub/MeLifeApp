package com.technohub.melifeapp.views.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.views.TestCategoriesFragment;

public class HomeFragment extends Fragment {

    private TextView homeTxtName;
    View root;
    CardView homeCardTests,homeCardReports,homeCardFaq,homeCardContactus,homeCardSettings,homeCardAboutus;
    Fragment fragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_home, container, false);
         root.setBackgroundColor(getResources().getColor(R.color.lightgrey));
         init();
         initClicks();
         return root;
    }
    void init()
    {

        homeTxtName=root.findViewById(R.id.homeTxtName);

        String name=new LoginResponse().getSharedPreferences(getContext(),"name");
        homeTxtName.setText(name);

        homeCardTests=root.findViewById(R.id.homeCardTests);
        homeCardReports=root.findViewById(R.id.homeCardReports);
        homeCardFaq=root.findViewById(R.id.homeCardFaq);
        homeCardContactus=root.findViewById(R.id.homeCardContactus);
        homeCardSettings=root.findViewById(R.id.homeCardSettings);
        homeCardAboutus=root.findViewById(R.id.homeCardAboutus);
    }
    void initClicks()
    {
        homeCardTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fragment=new TestCategoriesFragment();
                    loadFragment(fragment);
            }
        });

        homeCardReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        homeCardFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        homeCardAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        homeCardContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        homeCardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null)
        {
                     getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homelayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}