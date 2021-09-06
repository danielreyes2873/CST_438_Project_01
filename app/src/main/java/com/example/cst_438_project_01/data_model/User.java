package com.example.cst_438_project_01.data_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class is the Data Model class for the Users of our app (users stored in DB)
 */

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int UserID;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;

    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        UserID = userID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
