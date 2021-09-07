package com.example.cst_438_project_01;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompendiumData {

    @SerializedName("data")
    @Expose
    private CompendiumEntry compendiumData;

    public CompendiumEntry getCompendiumData() {
        return compendiumData;
    }
}
