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

import java.util.ArrayList;

public class FragmentMain extends Fragment {
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
        //intialize the button
        v.findViewById(R.id.button_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(mainTag, "enter button clicked");
                toast = Toast.makeText(FragmentMain.this.getContext(), "Job Entered", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        recyclerView = v.findViewById(R.id.RecuclerView_display_jobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),
                                                                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new JobsListAdapter(jobs));

        return v;
    }
}
