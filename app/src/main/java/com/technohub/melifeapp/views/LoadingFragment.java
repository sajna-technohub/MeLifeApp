package com.technohub.melifeapp.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.technohub.melifeapp.R;

public class LoadingFragment extends Fragment {

    View v;
    LinearLayout bouncelayout;
    Animation animation;
    ImageView splash_ai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_loading, container, false);
        bouncelayout=v.findViewById(R.id.bouncelayout);
        splash_ai=v.findViewById(R.id.splash_ai);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce_animation);
        bouncelayout.startAnimation(animation);
        return v;
    }



}
