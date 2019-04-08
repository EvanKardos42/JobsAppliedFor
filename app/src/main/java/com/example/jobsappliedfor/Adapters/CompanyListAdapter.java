package com.example.jobsappliedfor.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.jobsappliedfor.Database.Company;
import com.example.jobsappliedfor.JobsViewModel;
import com.example.jobsappliedfor.R;

public class CompanyListAdapter extends ListAdapter<Company, CompanyViewHolder> {

    private  JobsViewModel viewModel;

    public CompanyListAdapter(JobsViewModel viewModel) {
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
                    oldItem.getApplied() == newItem.getApplied();
        }
    };

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.job_item_row,viewGroup,false);
        return new CompanyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder companyViewHolder, int i) {
            final Company j = getItem(i);
            companyViewHolder.getTextView().setText(j.getCompanyName());
            companyViewHolder.getCheckBox().setChecked(j.getApplied());
            companyViewHolder.setCheckBoxListener(new View.OnClickListener() {
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
