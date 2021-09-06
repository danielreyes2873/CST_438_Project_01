package com.example.cst_438_project_01.data_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data Model Class that holds a person, place, or thing from the Legend of Zelda.
 */

@Entity(tableName = "Concept")
public class Concept {
    @PrimaryKey(autoGenerate = true)
    private int entryID;

    //this is a foreign key but we're not explicitly informing the database that this is the case, will use WHERE clause
    @ColumnInfo(name = "UserID")
    private int UserID;

    @ColumnInfo(name = "name")
    private String name;

    //example entry: https://botw-compendium.herokuapp.com/api/v2/entry/108
    @ColumnInfo(name = "api_reference")
    private String api_reference;

    public Concept(int entryID, int userID, String name, String api_reference) {
        this.entryID = entryID;
        UserID = userID;
        this.name = name;
        this.api_reference = api_reference;
    }
}