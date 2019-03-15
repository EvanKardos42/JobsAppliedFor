package com.example.jobsappliedfor.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.jobsappliedfor.Job;

import java.util.List;

@Dao
public interface JobsDAO {

    @Query("SELECT * FROM job")
    List<Job> getAll();

    @Query("SELECT * FROM job WHERE id IN (:jobIds)")
    List<Job> loadAllByIds(int[] jobIds);


    @Insert
    void insert(Job job);

    @Delete
    void delete(Job job);
}
