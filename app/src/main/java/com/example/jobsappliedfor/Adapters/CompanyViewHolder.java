package com.example.jobsappliedfor.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jobsappliedfor.R;


class CompanyViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private CheckBox checkBox;

    CompanyViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView_company_name);
        checkBox = itemView.findViewById(R.id.checkBox_applied);

    }

     CheckBox getCheckBox() {
        return checkBox;
    }

     TextView getTextView() {
        return textView;
    }

    void setCheckBoxListener(View.OnClickListener listener) {
        checkBox.setOnClickListener(listener);
    }
}
