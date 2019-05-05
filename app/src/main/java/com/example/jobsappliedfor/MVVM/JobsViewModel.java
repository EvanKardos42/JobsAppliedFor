package com.example.jobsappliedfor.MVVM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jobsappliedfor.Database.Company;

import java.util.List;


public class JobsViewModel extends AndroidViewModel {
    private JobRepository repository;

    public JobsViewModel(@NonNull Application application) {
        super(application);
        repository = new JobRepository(application);
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

    public LiveData<List<Company>> getAll() {
        return repository.getAll();
    }

    public LiveData<List<Company>> sortByLetter() {
        return repository.getSortedByFirstLetter();
    }

    public LiveData<List<Company>> getAllAppliedFor() {
        return repository.getAllApplied();
    }
    public LiveData<List<Company>> getAllNotAppliedFor() {
        return repository.getAllNotApplied();
    }
}
