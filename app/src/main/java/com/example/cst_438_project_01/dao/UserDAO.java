package com.example.cst_438_project_01.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.cst_438_project_01.data_model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void addUser(User user);
    @Query("SELECT COUNT(*) FROM User")
    int count();
    @Query("SELECT * FROM User")
    List<User> getAllUsers();
    @Query("SELECT * FROM User WHERE UserID = :userID")
    User findByUserID(int userID);
    @Query("SELECT * FROM User WHERE username = :usernameString")
    User findByUsername(String usernameString);
    @Query("SELECT * FROM User WHERE password = :passwordString")
    User findByPassword(String passwordString);
    @Query("DELETE FROM User WHERE UserID = :userID")
    void deleteByUserID(int userID);
    @Query("DELETE FROM User WHERE username = :usernameString")
    void deleteByUsername(String usernameString);
    @Query("DELETE FROM User where password = :passwordString")
    void deleteByPassword(String passwordString);
    @Query("DELETE FROM User")
    void deleteAll();
    @Insert
    void insertAll(List<User> users);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
}