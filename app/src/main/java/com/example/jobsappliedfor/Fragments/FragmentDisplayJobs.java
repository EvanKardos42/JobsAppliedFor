package com.example.jobsappliedfor.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobsappliedfor.R;

public class FragmentDisplayJobs extends Fragment {


    private static volatile FragmentDisplayJobs INSTANCE;

    public static FragmentDisplayJobs getINSTANCE() {
        if (INSTANCE == null) {

                if (INSTANCE == null) {
                    INSTANCE = new FragmentDisplayJobs();
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
        View v = inflater.inflate(R.layout.fragment_display_job_info, container, false);



        //intiated toast
        return v;
    }

}
