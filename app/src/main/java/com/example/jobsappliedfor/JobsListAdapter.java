package com.example.jobsappliedfor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class JobsListAdapter extends RecyclerView.Adapter<JobsViewHolder> {

    ArrayList<String> items;

    JobsListAdapter(ArrayList<String> stuff){
        items= stuff;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_item_row,viewGroup,false);

        return new JobsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder jobsViewHolder, int i) {
            jobsViewHolder.textView.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
