package com.example.jobsappliedfor;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Job")
public class Job {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name ="Company_Name", typeAffinity = ColumnInfo.TEXT)
    private String CompanyName;
    @ColumnInfo(name ="day",typeAffinity = ColumnInfo.TEXT)
    private Date day;
    @ColumnInfo(name ="applied",typeAffinity = ColumnInfo.INTEGER)
    private Boolean Applied;

    public Job(String companyName, Date day, Boolean applied) {
        CompanyName = companyName;
        this.day = day;
        Applied = applied;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Boolean getApplied() {
        return Applied;
    }

    public void setApplied(Boolean applied) {
        Applied = applied;
    }
}
