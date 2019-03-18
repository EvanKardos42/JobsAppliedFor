package com.example.jobsappliedfor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.jobsappliedfor.Database.Job;
import com.example.jobsappliedfor.Database.JobDatabases;
import com.example.jobsappliedfor.Database.JobsDAO;
import java.util.List;

public class JobRepository {

    private LiveData<List<Job>> currentJobs;
    private JobsDAO jbd;

    public JobRepository(Application app) {
        this.jbd = JobDatabases.getDatabase(app).getJobDao();
        currentJobs = jbd.getAll();
    }

    public LiveData<List<Job>> getAll( ){
        return currentJobs;
    }

    public void insert(Job job){
        new InsertAsyncTask(jbd).execute(job);
    }

    public void update(Job job){
        new UpdateAsyncTask(jbd).execute(job);
    }

    public void delete(Job job){
        new DeleteAsyncTask(jbd).execute(job);
    }

    private static class InsertAsyncTask extends AsyncTask<Job,Void,Void>{

        private JobsDAO jobsDAO;

        public InsertAsyncTask(JobsDAO jobs) {
            this.jobsDAO = jobs;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            jobsDAO.insert(jobs[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Job,Void,Void>{

        private JobsDAO jobsDAO;

        public DeleteAsyncTask(JobsDAO jobs) {
            this.jobsDAO = jobs;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            jobsDAO.delete(jobs[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Job,Void,Void>{

        private JobsDAO jobsDAO;

        public UpdateAsyncTask(JobsDAO jobs) {
            this.jobsDAO = jobs;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            jobsDAO.changeValue(jobs[0]);
            return null;
        }
    }
}
