package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.technohub.melifeapp.R;

public class SuccessFrag1 extends DialogFragment {

        View v;
        ImageView succ;
        TextView tv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        v= inflater.inflate(R.layout.fragment_success_frag1, container, false);
        succ=v.findViewById(R.id.succ);

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        //I want to start animation here
        succ.startAnimation(animation);
        return v;
    }





    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 800;
        params.height = 800;
        window.setAttributes(params);
    }


}
