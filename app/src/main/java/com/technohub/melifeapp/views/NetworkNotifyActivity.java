package com.technohub.melifeapp.views;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.ConnectionReceiver;

public class NetworkNotifyActivity extends AppCompatActivity  implements ConnectionReceiver.ReceiverListener
{
Button btntryagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_notify);
        getSupportActionBar().hide();
        btntryagain=findViewById(R.id.btntryagain);
        btntryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkConnection())
                { startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                    {
                    Log.e("networkstatus", "no");
                    }
            }
        });
    }


    @Override
    public void onNetworkChange(boolean isConnected)
    {
        checkConnection();
    }

    private boolean checkConnection() {

        // initialize intent filter
        IntentFilter intentFilter = new IntentFilter();

        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        // register receiver
        getApplicationContext().registerReceiver(new ConnectionReceiver(), intentFilter);

        // Initialize listener
        ConnectionReceiver.Listener = this;

        // Initialize connectivity manager
        ConnectivityManager manager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Initialize network info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        // get connection status
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        if (isConnected)

            return true;
        else
            return false;

    }
}
