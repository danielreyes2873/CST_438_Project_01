package com.example.cst_438_project_01;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAllAPI extends AppCompatActivity {
    private ScrollView viewAll;
    private TextView allItems;
    private CompendiumAPI compendiumAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        viewAll = findViewById(R.id.scrollView2);
        allItems = findViewById(R.id.allItemText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compendiumAPI = retrofit.create(CompendiumAPI.class);
        Call<AllCompendiumData> call = compendiumAPI.getAll();
        call.enqueue(new Callback<AllCompendiumData>() {
            @Override
            public void onResponse(Call<AllCompendiumData> call, Response<AllCompendiumData> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                String values = "";
                AllCompendiumData allvalues = response.body();
                List<CompendiumEntry> food = allvalues.getCompendiumData().getCreatures().getFood();
                values = "FOOD CREATURES: \n\n";
                allItems.append(values);
                for (CompendiumEntry entry : food) {
                    values = "Name: " + entry.getName() + " ID: " + entry.getId() + "\n Description" + entry.getDescription() + "\n\n";
                    allItems.append(values);
                }
                List<CompendiumEntry> nonfood = allvalues.getCompendiumData().getCreatures().getNon_food();
                values = "NON-FOOD CREATURES: \n\n";
                allItems.append(values);
                for (CompendiumEntry entry : nonfood) {
                    values = "Name: " + entry.getName() + " ID: " + entry.getId() + "\n Description" + entry.getDescription() + "\n\n";
                    allItems.append(values);
                }
                List<CompendiumEntry> monsters = allvalues.getCompendiumData().getMonsters();
                values = "MONSTERS: \n\n";
                allItems.append(values);
                for (CompendiumEntry entry : monsters) {
                    values = "Name: " + entry.getName() + " ID: " + entry.getId() + "\n Description" + entry.getDescription() + "\n\n";
                    allItems.append(values);
                }
                List<CompendiumEntry> equips = allvalues.getCompendiumData().getEquipment();
                values = "EQUIPMENT: \n\n";
                allItems.append(values);
                for (CompendiumEntry entry : equips) {
                    values = "Name: " + entry.getName() + " ID: " + entry.getId() + "\n Description" + entry.getDescription() + "\n\n";
                    allItems.append(values);
                }
                List<CompendiumEntry> materials = allvalues.getCompendiumData().getMaterials();
                values = "MATERIALS: \n\n";
                allItems.append(values);
                for (CompendiumEntry entry : materials) {
                    values = "Name: " + entry.getName() + " ID: " + entry.getId() + "\n Description" + entry.getDescription() + "\n\n";
                    allItems.append(values);
                }
                List<CompendiumEntry> treasure = allvalues.getCompendiumData().getTreasure();
                values = "TREASURE: \n\n";
                allItems.append(values);
                for (CompendiumEntry entry : treasure) {
                    values = "Name: " + entry.getName() + " ID: " + entry.getId() + "\n Description" + entry.getDescription() + "\n\n";
                    allItems.append(values);
                }
            }

            @Override
            public void onFailure(Call<AllCompendiumData> call, Throwable t) {
                String message = "Something went wrong";
                allItems.setText(message);
            }
        });
    }
}
