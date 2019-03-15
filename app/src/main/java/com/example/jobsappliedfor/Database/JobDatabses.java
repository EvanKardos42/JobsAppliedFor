package com.example.jobsappliedfor.Database;

import android.arch.persistence.room.RoomDatabase;

public abstract class JobDatabses extends RoomDatabase {
    public abstract JobsDAO jobDao();
}
