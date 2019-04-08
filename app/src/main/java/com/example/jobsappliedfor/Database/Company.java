package com.example.jobsappliedfor.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Company_table")
public class Company {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String companyName;
    private Boolean applied;


    public Company(String companyName, Boolean applied) {
        this.companyName = companyName;
        this.applied = applied;
    }

    void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Boolean getApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

}
