package com.example.cst_438_project_01;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompendiumData {

    @SerializedName("data")
    @Expose
    private CompendiumEntry compendiumData;

    @SerializedName("message")
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public CompendiumEntry getCompendiumData() {
        return compendiumData;
    }
}
