package com.technohub.melifeapp.views.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.views.AboutUsFragment;
import com.technohub.melifeapp.views.Contact_us_fragment;
import com.technohub.melifeapp.views.FaqFragment;
import com.technohub.melifeapp.views.LoadingFragment;
import com.technohub.melifeapp.views.ReportFragment;
import com.technohub.melifeapp.views.ReportsFragment;
import com.technohub.melifeapp.views.SuccessFrag2;
import com.technohub.melifeapp.views.TestCategoriesFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    private TextView homeTxtName,homeTxtDesc,welcomeTxtname;
    ImageView homeImgback;
    View root;
    CardView homeCardTests,homeCardReports,homeCardFaq,homeCardContactus,homeCardSettings,homeCardAboutus;
    Fragment fragment;
    String quali;
    DialogFragment dialogFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_home_fragment_new, container, false);
         root.setBackgroundColor(getResources().getColor(R.color.lightgrey));
         init();
         initClicks();
         if(new LoginResponse().getSharedPreferences(getContext(),"userid")!=null)
         Log.e("sharedpre",new LoginResponse().getSharedPreferences(getContext(),"userid"));
         return root;
    }

    public void showDialog(DialogFragment dialogFragment)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, "dialog");
    }

    void init()
    {

        homeTxtName=root.findViewById(R.id.homeTxtName);
        homeTxtDesc=root.findViewById(R.id.homeTxtDesc);
//        homeImgback=root.findViewById(R.id.homeImgback);
        welcomeTxtname=root.findViewById(R.id.welcomeTxtname);

        String name=new LoginResponse().getSharedPreferences(getContext(),"name");
        Log.e("name",name);

        if(!new LoginResponse().getSharedPreferences(getContext(),"quali").equals(null)) {
             quali = new LoginResponse().getSharedPreferences(getContext(), "quali");
             Log.e("qualiprofile",quali);
             homeTxtDesc.setText(quali);

        }
        welcomeTxtname.setText("Welcome "+name);
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
                if(homeCardTests.isEnabled())
                homeCardTests.setEnabled(false);

                fragment=new TestCategoriesFragment();
                 loadFragment(fragment);
            }
        });

        homeCardReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeCardReports.isEnabled())
                    homeCardReports.setEnabled(false);

                fragment=new ReportsFragment();
                loadFragment(fragment);
            }
        });

        homeCardFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeCardFaq.isEnabled())
                    homeCardFaq.setEnabled(false);
                fragment=new FaqFragment();
                loadFragment(fragment);
//                Toast.makeText(getContext(),"Will Release soon",Toast.LENGTH_LONG).show();
            }
        });

        homeCardAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeCardAboutus.isEnabled())
                    homeCardAboutus.setEnabled(false);

                fragment=new AboutUsFragment();
                loadFragment(fragment);
            }
        });

        homeCardContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeCardContactus.isEnabled())
                    homeCardContactus.setEnabled(false);

                fragment=new Contact_us_fragment();
                loadFragment(fragment);
            }
        });

        homeCardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeCardSettings.isEnabled())
                    homeCardSettings.setEnabled(false);

                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });

//        homeImgback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null)
        {
                     getFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.homelayout, fragment)
                    .commit();

            return true;
        }
        return false;
    }


}