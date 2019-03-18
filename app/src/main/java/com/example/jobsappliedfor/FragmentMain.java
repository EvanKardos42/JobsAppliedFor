package com.example.jobsappliedfor;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobsappliedfor.Adapters.JobsListAdapter;
import com.example.jobsappliedfor.Database.Job;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentMain extends Fragment implements View.OnClickListener {
    private final String mainTag ="EVANKARDOS_FRAGMENT_MAIN_TAG";
    private RecyclerView recyclerView;
    private JobsListAdapter jobsListAdapter;
    ArrayList<Job> jobs;
    EditText editText;
    Toast toast;
    private JobsViewModel viewModel;

    @SuppressLint("ShowToast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(v);
        viewModel = ViewModelProviders.of(this).get(JobsViewModel.class);
        viewModel.getAllJobs().observe(this, new Observer<List<Job>>() {
            @Override
            public void onChanged(@Nullable List<Job> jobs) {
                jobsListAdapter.setJob(jobs);
            }
        });
        //intiated toast
        toast = Toast.makeText(FragmentMain.this.getContext(), "", Toast.LENGTH_LONG);
        return v;
    }

    private  void initViews(View v){
        //initialise the button
        v.findViewById(R.id.button_enter).setOnClickListener(this);

        //initialise the editText
        editText =v.findViewById(R.id.editText_name);

        //initialise the recyclerview
        jobsListAdapter=new JobsListAdapter(jobs);
        recyclerView = v.findViewById(R.id.RecuclerView_display_jobs);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this.getContext(),
                                                                LinearLayoutManager.VERTICAL,
                                                                        false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(jobsListAdapter);
    }

    private Job createJob(String companyName){
        return new Job(companyName,false,new Date());
    }

    @Override
    public void onClick(View v) {
        String companyName=editText.getText().toString();
        if(companyName.isEmpty() ||companyName.contains(" ")) {
            Job j = createJob(companyName);
            viewModel.insert(j);
            Log.d(mainTag, "created Job");
            toast.setText("entered job");
            toast.show();
            editText.setText("");
        }
    }
}
