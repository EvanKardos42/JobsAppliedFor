package com.example.jobsappliedfor;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jobsappliedfor.Database.Company;

import java.util.List;


public class JobsViewModel extends AndroidViewModel {
    private JobRepository repository;
    private LiveData<List<Company>> jobs;

    public JobsViewModel(@NonNull Application application) {
        super(application);
        repository = new JobRepository(application);
        jobs=repository.getAll();

    }

    public void update(Company j){
        repository.update(j);
    }

    public void insert(Company j){
        repository.insert(j);
    }

    public void delete(Company j){
        repository.delete(j);
    }

    public LiveData<List<Company>> getAllJobs() {
        return jobs;
    }
}
