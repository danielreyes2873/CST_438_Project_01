package com.example.cst_438_project_01.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.cst_438_project_01.dao.ConceptDAO;
import com.example.cst_438_project_01.data_model.Concept;

/**
 * Concept Database class for Android Rooms
 */
@Database(entities = {Concept.class}, version=2, exportSchema = false)
public abstract class ConceptDb extends RoomDatabase {
    public abstract ConceptDAO concept();

    private static ConceptDb sInstance;

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

//    public void populateInitialData() {
//        if(concept().count() == 0) {
//            runInTransaction(new Runnable() {
//                @Override
//                public void run() {
//                    concept().addConcept(new Concept()); //need concept Dao for this and many other capabilities
//                }
//            });
//        }
//    }
}
