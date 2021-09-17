package com.example.cst_438_project_01.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_438_project_01.data_model.Concept;

import java.util.List;

/**
 * Concept Data access Object. Methods here can be used to access the SQL backend for android rooms.
 */
@Dao
public interface ConceptDAO {
    @Insert
    void addConcept(Concept concept);
    @Query("SELECT COUNT(*) FROM Concept")
    int count();
    @Query("SELECT * FROM Concept")
    List<Concept> getAllConcepts();
    @Query("SELECT * FROM Concept WHERE userID = :userID LIMIT 1")
    Concept findByUserID(int userID);
    @Query("SELECT * FROM Concept WHERE name = :nameString")
    Concept findByName(String nameString);
    @Query("SELECT * FROM Concept WHERE api_reference = :api_referenceString")
    Concept findByApiReference(String api_referenceString);
    @Query("DELETE FROM Concept WHERE userID = :userID")
    void deleteByUserID(int userID);
    @Query("DELETE FROM Concept WHERE name = :nameString")
    void deleteByName(String nameString);
    @Query("DELETE FROM Concept WHERE api_reference = :api_referenceString")
    void deleteByApiReference(String api_referenceString);
    @Query("DELETE FROM Concept")
    void deleteAll();
    @Query("SELECT * FROM Concept WHERE userID = :userID")
    List<Concept> findAllByUserID(int userID);
    @Insert
    void insertAll(List<Concept> concepts);
    @Update
    void update(Concept concept);
    @Delete
    void delete(Concept user);
}
