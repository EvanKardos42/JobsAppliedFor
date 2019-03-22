package com.example.jobsappliedfor.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface JobsDAO {

    @Query("SELECT * FROM Company_table ORDER BY companyName")
    LiveData<List<Company>> getAll();

   @Update
    void changeValue(Company company);

    @Insert
    void insert(Company company);

    @Delete
    void delete(Company company);
}
