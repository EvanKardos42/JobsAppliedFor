package com.example.jobsappliedfor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.jobsappliedfor.Database.Company;
import com.example.jobsappliedfor.Database.CompanyDAO;
import com.example.jobsappliedfor.Database.CompanyDatabases;

import java.util.List;

public class JobRepository {

    private LiveData<List<Company>> currentJobs;
    private CompanyDAO jbd;

    public JobRepository(Application app) {
        this.jbd = CompanyDatabases.getDatabase(app).getJobDao();
        currentJobs = jbd.getAllByName();
    }

    public LiveData<List<Company>> getAll( ){
        return currentJobs;
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

        public InsertAsyncTask(CompanyDAO jobs) {
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

        public DeleteAsyncTask(CompanyDAO jobs) {
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

        public UpdateAsyncTask(CompanyDAO jobs) {
            this.companyDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            companyDAO.changeValue(companies[0]);
            return null;
        }
    }
}
