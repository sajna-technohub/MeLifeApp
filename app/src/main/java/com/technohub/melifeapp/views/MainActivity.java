package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.technohub.melifeapp.R;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    ProgressBar mainSpinKit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mainSpinKit=findViewById(R.id.mainSpinKit);
        mainSpinKit.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String storedUsername = prefs.getString("apiToken", "");
                Log.e("log", storedUsername);
                if (!storedUsername.equalsIgnoreCase("")) {
                    intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    startActivity(intent);
                    mainSpinKit.setVisibility(View.GONE);
                    finish();

                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    mainSpinKit.setVisibility(View.GONE);
                }
            }
        }, 3000);


    }


}
