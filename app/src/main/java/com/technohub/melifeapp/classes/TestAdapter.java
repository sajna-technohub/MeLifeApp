package com.technohub.melifeapp.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.technohub.melifeapp.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
    ArrayList<String> SubjectNames;

    View view1;

    public TestAdapter(ArrayList<String> SubjectNames1) {

        this.SubjectNames = SubjectNames1;
    }

    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        view1  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(TestAdapter.ViewHolder Viewholder, int i) {

        Viewholder.SubjectTextView.setText(SubjectNames.get(i));
    }

    @Override
    public int getItemCount() {

        return SubjectNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView SubjectTextView;
        public ViewHolder(View view) {

            super(view);

            SubjectTextView = (TextView)view.findViewById(R.id.textViewName);
        }
    }
    }


