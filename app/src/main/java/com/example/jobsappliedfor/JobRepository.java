package com.example.jobsappliedfor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.jobsappliedfor.Database.Company;
import com.example.jobsappliedfor.Database.JobDatabases;
import com.example.jobsappliedfor.Database.JobsDAO;
import java.util.List;

public class JobRepository {

    private LiveData<List<Company>> currentJobs;
    private JobsDAO jbd;

    public JobRepository(Application app) {
        this.jbd = JobDatabases.getDatabase(app).getJobDao();
        currentJobs = jbd.getAll();
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

        private JobsDAO jobsDAO;

        public InsertAsyncTask(JobsDAO jobs) {
            this.jobsDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            jobsDAO.insert(companies[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Company,Void,Void>{

        private JobsDAO jobsDAO;

        public DeleteAsyncTask(JobsDAO jobs) {
            this.jobsDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            jobsDAO.delete(companies[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Company,Void,Void>{

        private JobsDAO jobsDAO;

        public UpdateAsyncTask(JobsDAO jobs) {
            this.jobsDAO = jobs;
        }

        @Override
        protected Void doInBackground(Company... companies) {
            jobsDAO.changeValue(companies[0]);
            return null;
        }
    }
}
