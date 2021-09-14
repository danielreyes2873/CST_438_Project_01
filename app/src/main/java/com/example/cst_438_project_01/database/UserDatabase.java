package com.example.cst_438_project_01.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cst_438_project_01.dao.UserDAO;
import com.example.cst_438_project_01.data_model.User;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase{
    private static final String DB_NAME = "user_db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract UserDAO userDao();

    public void populateInitialData() {
        if (userDao().count()== 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    userDao().addUser(new User("billgates@hotmail.com", "password1"));
                    userDao().addUser(new User("doctorc@unittest.com", "password2"));
                    userDao().addUser(new User("drbruns@deeplearning.org", "password3"));
                }
            });
        }
    }
}
