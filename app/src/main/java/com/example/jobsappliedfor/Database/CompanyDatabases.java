package com.example.jobsappliedfor.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Company.class},version = 2,exportSchema = false)
@TypeConverters({Company.Converters.class})
public abstract class CompanyDatabases extends RoomDatabase {
    public abstract CompanyDAO getJobDao();

    private static volatile CompanyDatabases INSTANCE;

    public static CompanyDatabases getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CompanyDatabases.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompanyDatabases.class,"Job_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
