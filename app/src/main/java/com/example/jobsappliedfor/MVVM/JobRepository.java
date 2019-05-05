package com.example.jobsappliedfor.MVVM;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.jobsappliedfor.Database.Company;
import com.example.jobsappliedfor.Database.CompanyDAO;
import com.example.jobsappliedfor.Database.CompanyDatabases;

import java.util.List;

public class JobRepository {

    private CompanyDAO jbd;

    public JobRepository(Application app) {
        this.jbd = CompanyDatabases.getDatabase(app).getJobDao();
    }

    public LiveData<List<Company>> getAll( ){
        return jbd.getAll();
    }

    public LiveData<List<Company>> getAllApplied( ){
        return jbd.getAllApplied();
    }

    public LiveData<List<Company>> getAllNotApplied( ){
        return jbd.getAllNotApplied();
    }

    public LiveData<List<Company>> getSortedByFirstLetter( ){
        return jbd.getAllByName();
    }


    public void insert(Company company){
        new InsertAsyncTask(jbd).execute(company);
    }

    public void update(Company company){
        new UpdateAsyncTask(jbd).execute(company);
    }

    public void delete(Company company){
        new DeleteAsyncTask(jbd).execute(company);
    }

    private static class InsertAsyncTask extends AsyncTask<Company,Void,Void>{

        private CompanyDAO companyDAO;

         InsertAsyncTask(CompanyDAO jobs) {
            this.companyDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            companyDAO.insert(companies[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Company,Void,Void>{

        private CompanyDAO companyDAO;

         DeleteAsyncTask(CompanyDAO jobs) {
            this.companyDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            companyDAO.delete(companies[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Company,Void,Void>{

        private CompanyDAO companyDAO;

        UpdateAsyncTask(CompanyDAO jobs) {
            this.companyDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            companyDAO.changeValue(companies[0]);
            return null;
        }
    }
}
