package com.example.jobsappliedfor.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jobsappliedfor.R;


public class JobsViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public JobsViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView_company_name);

    }
}
