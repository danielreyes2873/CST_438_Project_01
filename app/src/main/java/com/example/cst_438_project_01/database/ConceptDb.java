package com.example.cst_438_project_01.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.cst_438_project_01.data_model.Concept;

@Database(entities = {Concept.class}, version=1, exportSchema = false)
public abstract class ConceptDb extends RoomDatabase {
    public abstract ConceptDao concept();

    private static ConceptDao sInstance;

    public static synchronized ConceptDb getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ConceptDb.class, "concept.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    public void populateInitialData() {
        if(concept().count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    concept().add(new Concept()); //need concept Dao for this and many other capabilities
                }
            });
        }
    }
}
