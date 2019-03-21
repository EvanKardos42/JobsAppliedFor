package com.example.jobsappliedfor;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jobsappliedfor.Database.Job;

import java.util.List;


public class JobsViewModel extends AndroidViewModel {
    private JobRepository repository;
    private LiveData<List<Job>> jobs;

    public JobsViewModel(@NonNull Application application) {
        super(application);
        repository = new JobRepository(application);
        jobs=repository.getAll();

    }

    public void update(Job j){
        repository.update(j);
    }

    public void insert(Job j){
        repository.insert(j);
    }


    public void delete(Job j){
        repository.delete(j);
    }

    public LiveData<List<Job>> getAllJobs() {
        return jobs;
    }
}
