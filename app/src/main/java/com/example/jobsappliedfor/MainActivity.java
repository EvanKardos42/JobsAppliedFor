package com.example.jobsappliedfor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jobsappliedfor.Fragments.FragmentMain;

public class MainActivity extends AppCompatActivity {
    Fragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // fragments implimentation code
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        frag = new FragmentMain();
        transaction.add(R.id.frameLayout,frag).commit();

    }
}
