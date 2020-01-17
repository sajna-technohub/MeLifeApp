package com.technohub.melifeapp.views.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.LoginResponse;

public class HomeFragment extends Fragment {

    private TextView homeTxtName;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_home, container, false);
         root.setBackgroundColor(Color.WHITE);
         init();
         return root;
    }
    void init()
    {
        homeTxtName=root.findViewById(R.id.homeTxtName);
        String name=new LoginResponse().getSharedPreferences(getContext(),"name");
        homeTxtName.setText(name);
    }
}