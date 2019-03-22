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
    @TypeConverters(Converters.class)
    private Date day;

    public Company(String companyName, Boolean applied, Date day) {
        this.companyName = companyName;
        this.applied = applied;
        this.day = day;
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

    public Date getDay() {
        return day;
    }

    static class Converters {
        @TypeConverter
        public Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }


        @TypeConverter
        public Long dateToTimestamp(Date date) {
            if (date == null) {
                return null;
            } else {
                return date.getTime();
            }
        }
    }
}
