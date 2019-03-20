package com.example.jobsappliedfor.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobsappliedfor.Database.Job;
import com.example.jobsappliedfor.R;

import java.util.ArrayList;
import java.util.List;

public class JobsListAdapter extends RecyclerView.Adapter<JobsViewHolder> {

    private List<Job> items = new ArrayList<>();

    public JobsListAdapter(ArrayList<Job> stuff){
        if(stuff != null)
            items=stuff;

    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.job_item_row,viewGroup,false);

        return new JobsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder jobsViewHolder, int i) {
            Job j=items.get(i);
            jobsViewHolder.getTextView().setText(j.getCompanyName());
            jobsViewHolder.getCheckBox().setChecked(j.getApplied());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setJob(List<Job> j){
        items = j;
        notifyDataSetChanged();
    }

    public Job getJobAt(int position){
        return items.get(position);
    }
}
