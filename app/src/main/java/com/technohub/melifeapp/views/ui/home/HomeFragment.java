package com.technohub.melifeapp.views.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.views.ReportFragment;
import com.technohub.melifeapp.views.TestCategoriesFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
                fragment=new ReportFragment();
                loadFragment(fragment);
            }
        });

        homeCardFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                   String s= dateFormat.format(today);
                        Log.e("date",s);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                        Date today = Calendar.getInstance().getTime();
                        String s= dateFormat.format(today);
                        Log.e("date af delay",s);
                    }
                }, 5000);



//                String startDate = "20/03/11 17:15:15";
//                String stopDate = "20/03/11 17:16:05";
//
//// Custom date format
//                SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//
//                Date d1 = null;
//                Date d2 = null;
//                try {
//                    d1 = format.parse(startDate);
//                    d2 = format.parse(stopDate);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//// Get msec from each, and subtract.
//                long diff = d2.getTime() - d1.getTime();
//                long diffSeconds = diff / 1000;
//                long diffMinutes = diff / (60 * 1000);
//                long diffHours = diff / (60 * 60 * 1000);
//                Log.v("Time in seconds: " , diffSeconds + " seconds.");
//                Log.v("Time in minutes: " + diffMinutes , " minutes.");
//                Log.v("Time in hours: " + diffHours , " hours.");


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