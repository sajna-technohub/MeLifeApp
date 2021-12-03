package com.technohub.melifeapp.classes;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.snackbar.Snackbar;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.views.DashBoardActivity;

public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!Common.isConnectedToInternet(context)){

            AlertDialog.Builder builder =  new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.networkalertlayout,null);
            builder.setView(view);
            Button btnRetry = view.findViewById(R.id.btntryagain2);
            builder.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRetry.setOnClickListener(view1 -> {
                dialog.dismiss();
                onReceive(context,intent);
            });
        }
    }
}