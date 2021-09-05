package com.example.cst_438_project_01;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompendiumAPI {

    @GET("entry/100")
    Call<CompendiumData> getData();
}
