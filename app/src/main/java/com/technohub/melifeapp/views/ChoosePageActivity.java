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

import com.technohub.melifeapp.R;

public class ChoosePageActivity extends AppCompatActivity {
Button Choosepage_btnChoose;
    Intent intent;
//    ProgressBar mainSpinKit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_page);
//        mainSpinKit=findViewById(R.id.mainSpinKitchoose);

        Choosepage_btnChoose=findViewById(R.id.btn_signin);
        Choosepage_btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        String storedUsername = prefs.getString("name", "");
                        Log.e("log", storedUsername);
                        if (!storedUsername.equalsIgnoreCase("")) {
                            intent = new Intent(getApplicationContext(), DashBoardActivity.class);
//                            mainSpinKit.setVisibility(View.VISIBLE);
                            startActivity(intent);
//                            mainSpinKit.setVisibility(View.GONE);
                            finish();

                        } else {
                            intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
//                            mainSpinKit.setVisibility(View.GONE);
                        }
                    }


        });

    }
}
