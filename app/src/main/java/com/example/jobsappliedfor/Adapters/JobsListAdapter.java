package com.example.jobsappliedfor.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.jobsappliedfor.Database.Company;
import com.example.jobsappliedfor.JobsViewModel;
import com.example.jobsappliedfor.R;

import java.util.ArrayList;
import java.util.List;

public class JobsListAdapter extends ListAdapter<Company,JobsViewHolder> {

    private  JobsViewModel viewModel;

    public JobsListAdapter(JobsViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.viewModel = viewModel;
    }

    private static final DiffUtil.ItemCallback<Company> DIFF_CALLBACK = new DiffUtil.ItemCallback<Company>() {
        @Override
        public boolean areItemsTheSame(Company oldItem, Company newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Company oldItem, Company newItem) {
            return oldItem.getCompanyName().equals(newItem.getCompanyName()) &&
                    oldItem.getDay().equals(newItem.getDay()) &&
                    oldItem.getApplied() == newItem.getApplied();
        }
    };

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.job_item_row,viewGroup,false);
        return new JobsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder jobsViewHolder, int i) {
            final Company j = getItem(i);
            jobsViewHolder.getTextView().setText(j.getCompanyName());
            jobsViewHolder.getCheckBox().setChecked(j.getApplied());
            jobsViewHolder.setCheckBoxListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v instanceof CheckBox){
                       j.setApplied(((CheckBox) v).isChecked());
                       viewModel.update(j);
                    }
                }
            });
    }




    public Company getJobAt(int position){
        return getItem(position);
    }

}
