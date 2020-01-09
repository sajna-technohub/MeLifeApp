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
import com.technohub.melifeapp.classes.MyService;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    ProgressBar mainSpinKit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
//        startService(new Intent(getBaseContext(), MyService.class));
        mainSpinKit=findViewById(R.id.mainSpinKit);
        mainSpinKit.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                    intent = new Intent(getApplicationContext(), ChoosePageActivity.class);
                    startActivity(intent);
                    mainSpinKit.setVisibility(View.GONE);
                    finish();

            }
        }, 3000);
    }


}
