package com.example.cst_438_project_01;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CompendiumEntry {

    private String category;

    private List<String> commonLocations;

    private String description;

    private List<String> drops;

    private int id;

    private String image;

    private String name;

    @SerializedName("hearts_recovered")
    private int heartRecovery;

    @SerializedName("cooing_effect")
    private String effect;

    private int attack;

    private int defense;

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public List<String> getCommonLocations() {
        return commonLocations;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDrops() {
        return drops;
    }

    public int getHeartRecovery() {
        return heartRecovery;
    }

    public String getEffect() {
        return effect;
    }
}
