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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    private JobsViewModel viewModel;
    ArrayList<Job> jobs;
    EditText editText;
    Toast toast;


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
        toast = Toast.makeText(FragmentMain.this.getContext(), "", Toast.LENGTH_SHORT);
        return v;
    }

    private  void initViews(View v){
        //initialise the button
        v.findViewById(R.id.button_enter).setOnClickListener(this);

        //initialise the editText
        editText =v.findViewById(R.id.editText_name);

        //initialise the recyclerview
        jobsListAdapter=new JobsListAdapter(jobs);
        jobsListAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb= (CheckBox)v;
                if(cb.isChecked()){
                    Log.d(mainTag, "checked");
                }
            }
        });

        recyclerView = v.findViewById(R.id.RecuclerView_display_jobs);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this.getContext(),
                                                                LinearLayoutManager.VERTICAL,
                                                                        false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(jobsListAdapter);

        // helps with animations of swiping to delete a job
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                viewModel.delete(jobsListAdapter.getJobAt(viewHolder.getAdapterPosition()));
                showToast("Deleted Job");
            }
        }).attachToRecyclerView(recyclerView);

    }

    private Job createJob(String companyName){
        return new Job(companyName,false,new Date());
    }

    @Override
    public void onClick(View v) {

        String companyName = editText.getText().toString();

        if(!companyName.isEmpty()) {
            Job j = createJob(companyName);
            viewModel.insert(j);
            Log.d(mainTag, "created Job");
           showToast("created Job");
            editText.setText("");
        }
        Log.d(mainTag, "Button Clicked");
    }

    public void showToast(String s){
        toast.setText(s);
        toast.show();
    }
}
