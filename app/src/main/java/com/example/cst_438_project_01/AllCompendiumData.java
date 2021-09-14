package com.example.cst_438_project_01;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCompendiumData {

    @SerializedName("data")
    @Expose
    private AllCompendiumCategories compendiumData;

    public AllCompendiumCategories getCompendiumData() {
        return compendiumData;
    }
}
