package com.technohub.melifeapp.views;


import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.ConnectionReceiver;
import com.technohub.melifeapp.views.ui.home.HomeFragment;

public class NetworkNotifyFragment extends Fragment implements ConnectionReceiver.ReceiverListener{

View v;
Button btntryagain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_network_notify, container, false);
        v.setBackgroundColor(Color.WHITE);
        btntryagain=v.findViewById(R.id.btntryagain);
        btntryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (checkConnection())
                            {
                                getFragmentManager()
                                        .beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                                        .replace(R.id.networklayout, new HomeFragment())
                                        .commit();
                            }else
                        Log.e("networkstatus","no");
            }
        });
        return v;
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    private boolean checkConnection() {

        // initialize intent filter
        IntentFilter intentFilter = new IntentFilter();

        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        // register receiver
        getContext().registerReceiver(new ConnectionReceiver(), intentFilter);

        // Initialize listener
        ConnectionReceiver.Listener = this;

        // Initialize connectivity manager
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

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
