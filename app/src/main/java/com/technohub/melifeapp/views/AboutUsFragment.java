package com.technohub.melifeapp.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.technohub.melifeapp.R;


public class AboutUsFragment extends Fragment {
View v;
ImageView aboutBtnBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_about_us, container, false);
        v.setBackgroundColor(Color.WHITE);
        aboutBtnBack=v.findViewById(R.id.aboutBtnBack);
        aboutBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),DashBoardActivity.class));
            }
        });
        return v;
    }




}
