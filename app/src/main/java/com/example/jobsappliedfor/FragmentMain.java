package com.example.jobsappliedfor;

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
import com.example.jobsappliedfor.Database.JobDatabases;
import com.example.jobsappliedfor.Database.JobsDAO;

import java.util.ArrayList;
import java.util.Date;

public class FragmentMain extends Fragment implements View.OnClickListener {
    private final String mainTag ="EVANKARDOS_FRAGMENT_MAIN_TAG";
    RecyclerView recyclerView;
    ArrayList<String> jobs;
    EditText editText;
    Toast toast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(v);

        return v;
    }

    private  void initViews(View v){
        //initialise the button
        v.findViewById(R.id.button_enter).setOnClickListener(this);

        //initialise the editText
        editText =v.findViewById(R.id.editText_name);

        //initialise the recyclerview
        recyclerView = v.findViewById(R.id.RecuclerView_display_jobs);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this.getContext(),
                                                                LinearLayoutManager.VERTICAL,
                                                                        false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(new JobsListAdapter(jobs));
    }

    private Job createJob(String companyName){
        return new Job(companyName,false,new Date());
    }

    @Override
    public void onClick(View v) {
        Job j = createJob(editText.getText().toString());

        Log.d(mainTag, "created Job");
        toast = Toast.makeText(FragmentMain.this.getContext(), "Job Entered", Toast.LENGTH_LONG);
        toast.show();
    }
}
