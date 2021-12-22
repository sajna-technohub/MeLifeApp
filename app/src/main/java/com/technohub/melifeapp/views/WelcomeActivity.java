package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.ConnectionReceiver;

public class WelcomeActivity extends AppCompatActivity  implements ConnectionReceiver.ReceiverListener{

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.welcomelayout, new NetworkNotifyFragment ()).commit();
                if(checkConnection())
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),NetworkNotifyActivity.class));
                }


            }
        });

    }
     boolean checkConnection()
     {
        // initialize intent filter

         IntentFilter intentFilter = new IntentFilter();

        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        // register receiver
       getApplicationContext().registerReceiver(new ConnectionReceiver(), intentFilter);

        // Initialize listener
        ConnectionReceiver.Listener = this;

        // Initialize connectivity manager
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Initialize network info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        // get connection status
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        // display snack bar
//        showSnackBar(isConnected);
        if(isConnected)
            return true;
        else
            return false;
    }

//    private void showSnackBar(boolean isConnected) {
//
//        // initialize color and message
//        String message;
//        int color;
//
//        // check condition
//        if (isConnected) {
//
//            // when internet is connected
////            // set message
////            message = "Connected to Internet";
////            Log.e("networkconn",message);
////
////            // set text color
////            color = Color.WHITE;
//
//        } else {
//
//            // when internet
//            // is disconnected
//            // set message
//            message = "Not Connected to Internet";
//            Log.e("networkconn",message);
//            // set text color
//            color = Color.RED;
//            Snackbar snackbar = Snackbar.make(this.findViewById(R.id.homeCardAboutus), message, Snackbar.LENGTH_SHORT);
//
//            // initialize view
//            View view = snackbar.getView();
//
//            // Assign variable
//            TextView textView = view.findViewById(R.id.snackbar_text);
//
//            // set text color
//            textView.setTextColor(color);
//
//            // show snack bar
//            snackbar.show();
//        }
//
//        // initialize snack bar
//
//    }
}
