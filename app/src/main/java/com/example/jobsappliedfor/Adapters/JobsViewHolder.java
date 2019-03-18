package com.example.jobsappliedfor.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jobsappliedfor.R;


public class JobsViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private CheckBox checkBox;

    JobsViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView_company_name);
        checkBox = itemView.findViewById(R.id.checkBox_applied);

    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextView getTextView() {
        return textView;
    }
}
