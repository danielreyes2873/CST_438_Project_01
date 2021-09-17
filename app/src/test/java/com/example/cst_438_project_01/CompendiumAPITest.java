package com.example.cst_438_project_01;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompendiumAPITest {
    private CompendiumAPI compendiumAPI;
    private CompendiumAPI compendiumAPI2;
    private Retrofit retrofit;
    private Retrofit retrofit2;
    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compendiumAPI = retrofit.create(CompendiumAPI.class);

        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compendiumAPI2 = retrofit2.create(CompendiumAPI.class);
    }
    @Test
    public void testGetData()  {
        Call<CompendiumData> call = compendiumAPI.getData("flameblade");
        CompendiumData data = null;
        try {
            Response<CompendiumData> response = call.execute();
            data = response.body();
        }
        catch (IOException e){
        }

        assertNotNull(data);
    }

    @Test
    public void testGetAll() {
        Call<AllCompendiumData> call = compendiumAPI2.getAll();
        AllCompendiumData data = null;
        try {
            Response<AllCompendiumData> response = call.execute();
            data = response.body();
        }
        catch (IOException e){
        }

        assertNotNull(data);
    }
}
