package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.MyService;

public class MainActivity extends AppCompatActivity {
        Intent intent;
        LinearLayout bouncelayout;
        private static int TIME_OUT = 2000;
        Animation animation;
        ImageView splash_ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bouncelayout=findViewById(R.id.bouncelayout);
        splash_ai=findViewById(R.id.splash_ai);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);
        bouncelayout.startAnimation(animation);

//        mainSpinKit.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
//            /*==
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
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

                } else
                    {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, TIME_OUT);

    }



}
