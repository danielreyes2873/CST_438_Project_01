package com.example.cst_438_project_01.data_model;

/**
 * This class is the Data Model class for the Users of our app (users stored in DB)
 */
public class User {
    private String username;
    private String password;
    private int UserID;

    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
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

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
