package com.example.jobsappliedfor.Fragments;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobsappliedfor.Adapters.CompanyListAdapter;
import com.example.jobsappliedfor.Database.Company;
import com.example.jobsappliedfor.JobsViewModel;
import com.example.jobsappliedfor.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMain extends Fragment implements View.OnClickListener {
    private final String mainTag ="EVANKARDOS_FRAGMENT_MAIN_TAG";
    private CompanyListAdapter jobsListAdapter;
    private JobsViewModel viewModel;
    //ArrayList<Company> companies;
    EditText editText;

    private static volatile FragmentMain INSTANCE;

    public static FragmentMain getINSTANCE() {
        if (INSTANCE == null) {

            if (INSTANCE == null) {
                INSTANCE = new FragmentMain();
            }

        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        viewModel = ViewModelProviders.of(this).get(JobsViewModel.class);
        viewModel.getAllJobs().observe(this, new Observer<List<Company>>() {
            @Override
            public void onChanged(@Nullable List<Company> companies) {
                jobsListAdapter.submitList(companies);
            }
        });
        initViews(v);
        //intiated toast
        return v;
    }

    // imitates the all the views to the fragment
    private  void initViews(View v){
        //initialise the button
        v.findViewById(R.id.button_enter).setOnClickListener(this);

        //initialise the editText
        editText = v.findViewById(R.id.editText_name);

        //initialise the recyclerview
        jobsListAdapter = new CompanyListAdapter(viewModel);
        RecyclerView recyclerView = v.findViewById(R.id.RecuclerView_display_jobs);
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
                showToast("Deleted Company");
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onClick(View v) {

        String companyName = editText.getText().toString();

        if(!companyName.isEmpty()) {
            Company j = createJob(companyName);
            viewModel.insert(j);
            Log.d(mainTag, "created Company");
            showToast("Created Company");
            editText.setText("");
        }
        Log.d(mainTag, "Button Clicked");
    }

    /*
    function that takes in a string
    * and shows uses the toast to display the
    * string
    */
    public void showToast(String s){
         Toast.makeText(FragmentMain.this.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    /*
    creates a new job object with the given string
     */
    private Company createJob(String companyName){
        return new Company(companyName,false);
    }
}
