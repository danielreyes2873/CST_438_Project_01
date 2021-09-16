package com.example.cst_438_project_01;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CompendiumAPI {

    @GET("v2")
    Call<AllCompendiumData> getAll();

    @GET("entry/{item}")
    Call<CompendiumData> getData(@Path("item") String item);
}
