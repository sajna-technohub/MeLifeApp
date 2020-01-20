package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.MyService;

public class MainActivity extends AppCompatActivity {
        Intent intent;
        ProgressBar mainSpinKit;
        private static int TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mainSpinKit=findViewById(R.id.mainSpinKit);
        mainSpinKit.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String storedUsername = prefs.getString("name", "");
                Log.e("log", storedUsername);

                if (!storedUsername.equalsIgnoreCase("")) {
                    intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                    mainSpinKit.setVisibility(View.GONE);
                    finish();

                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                    mainSpinKit.setVisibility(View.GONE);
                }
            }
        }, TIME_OUT);

    }



}
