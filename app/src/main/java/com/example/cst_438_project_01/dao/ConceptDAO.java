package com.example.cst_438_project_01.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_438_project_01.data_model.Concept;

import java.util.List;

@Dao
public interface ConceptDAO {
    @Insert
    void addConcept(Concept concept);
    @Query("SELECT COUNT(*) FROM Concept")
    int count();
    @Query("SELECT * FROM Concept")
    List<Concept> getAllConcepts();
    @Query("SELECT * FROM Concept WHERE userID = :userIDString")
    Concept findByUserID(String userIDString);
    @Query("SELECT * FROM Concept WHERE name = :nameString")
    Concept findByName(String nameString);
    @Query("SELECT * FROM Concept WHERE api_reference = :api_referenceString")
    Concept findByApiReference(String api_referenceString);
    @Query("DELETE FROM Concept WHERE userID = :userIDString")
    Concept deleteByUserID(String userIDString);
    @Query("DELETE FROM Concept WHERE name = :nameString")
    Concept deleteByName(String nameString);
    @Query("DELETE FROM Concept WHERE api_reference = :api_referenceString")
    Concept deleteByApiReference(String api_referenceString);
    @Insert
    void insertAll(List<Concept> concepts);
    @Update
    void update(Concept concept);
    @Delete
    void delete(Concept user);
}
