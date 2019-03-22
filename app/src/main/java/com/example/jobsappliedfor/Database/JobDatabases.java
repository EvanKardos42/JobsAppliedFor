package com.example.jobsappliedfor.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Company.class},version = 2,exportSchema = false)
@TypeConverters({Company.Converters.class})
public abstract class JobDatabases extends RoomDatabase {
    public abstract JobsDAO getJobDao();

    private static volatile JobDatabases INSTANCE;

    public static JobDatabases getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (JobDatabases.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            JobDatabases.class,"Job_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
