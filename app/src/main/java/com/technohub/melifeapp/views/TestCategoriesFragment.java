package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.TestAdapter;

import java.util.ArrayList;


public class TestCategoriesFragment extends Fragment {
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<String> data;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_test__categories, container, false);

        v.setBackgroundColor(Color.WHITE);

        recyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList();

        AddItemsToRecyclerViewArrayList();

        RecyclerView.Adapter adapter = new TestAdapter(data);
        recyclerView.setAdapter(adapter);

        return v;
    }
    public void AddItemsToRecyclerViewArrayList()
    {
        data = new ArrayList<>();
        data.add("Skill FInder Test");
        data.add("Humanity Test");
        data.add("Psychometric Test");
        data.add("Right Skill test");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
