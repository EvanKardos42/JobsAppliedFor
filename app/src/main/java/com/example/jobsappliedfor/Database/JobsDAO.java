package com.example.jobsappliedfor.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.jobsappliedfor.Job;

import java.util.List;

@Dao
public interface JobsDAO {

    @Query("SELECT * FROM jobs")
    LiveData<List<Job>> getAll();

    @Insert
    void insert(Job job);

    @Delete
    void delete(Job job);
}
