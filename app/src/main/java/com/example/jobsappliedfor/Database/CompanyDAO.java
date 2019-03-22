package com.example.jobsappliedfor.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CompanyDAO {

    @Query("SELECT * FROM Company_table")
    LiveData<List<Company>> getAll();

    @Query("SELECT * FROM Company_table ORDER BY companyName")
    LiveData<List<Company>> getAllByName();

    @Query("SELECT * FROM Company_table WHERE applied = 1")
    LiveData<List<Company>> getAllApplied();

   @Update
    void changeValue(Company company);

    @Insert
    void insert(Company company);

    @Delete
    void delete(Company company);
}
