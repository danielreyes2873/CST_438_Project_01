package com.example.cst_438_project_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project_01.data_model.Concept;
import com.example.cst_438_project_01.database.ConceptDb;

public class SearchActivity extends AppCompatActivity {
private TextView textViewResult;
private EditText searchField;
private CompendiumAPI compendiumAPI;
private Button searchButton;
private Button itemButton;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textViewResult = findViewById(R.id.textView);
        searchButton = findViewById(R.id.button);
        searchField = findViewById(R.id.editTextSearch);
        itemButton = findViewById(R.id.button2);
        itemButton.setVisibility(View.INVISIBLE);
        String username = getIntent().getStringExtra("USERNAME");
        int userid = getIntent().getIntExtra("USERID", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compendiumAPI = retrofit.create(CompendiumAPI.class);
        ConceptDb concepts = ConceptDb.getInstance(this.getApplicationContext());


        searchButton.setOnClickListener(view -> {
            itemButton.setVisibility(View.INVISIBLE);
            String search = searchField.getText().toString();
            search = search.replace(' ','_');
            Call<CompendiumData> call = compendiumAPI.getData(search);
            String finalSearch = search;
            call.enqueue(new Callback<CompendiumData>() {
                @Override
                public void onResponse(@NonNull Call<CompendiumData> call, @NonNull Response<CompendiumData> response) {
                    if(!response.isSuccessful()) {
                        return;
                    }

                    CompendiumData values = response.body();
                    String content = "";
                    if (values.getCompendiumData().getId()!=0) {
                        StringBuilder drops = new StringBuilder();
                        StringBuilder locations = new StringBuilder();
                        for (String loc : values.getCompendiumData().getCommonLocations()) {
                            locations.append(loc).append(", ");
                        }
                        if (values.getCompendiumData().getCategory().equals("creatures")) {
                            if (values.getCompendiumData().getId()<=47 && values.getCompendiumData().getId()>8) {
                                for (String drop : values.getCompendiumData().getDrops()) {
                                    drops.append(drop).append(", ");
                                }
                                content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                        values.getCompendiumData().getDescription() + "\n Drops: " + drops
                                        + "\n Common Locations: " + locations + "\n Click to View Image";
                            } else {
                                content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                        values.getCompendiumData().getDescription() + "\n Click to View Image";
                            }
                        } else if (values.getCompendiumData().getCategory().equals("monsters")) {
                            for (String drop : values.getCompendiumData().getDrops()) {
                                drops.append(drop).append(", ");
                            }
                            content += "Name: " + values.getCompendiumData().getName() + "\nDescription: " +
                                    values.getCompendiumData().getDescription() + "\nDrops: " + drops
                                    + "\nCommon Locations: " + locations + "\n Click to View Image";
                        } else if (values.getCompendiumData().getCategory().equals("equipment")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() + "\n Attack: " + values.getCompendiumData().getAttack()
                                    + " Defense: " + values.getCompendiumData().getDefense() +
                                    "\nCommon Locations: " + locations + "\n Click to View Image";
                        } else if (values.getCompendiumData().getCategory().equals("materials")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() +
                                    "\nCommon Locations: " + locations + "\n Click to View Image";
                        } else {
                            content += "Name: " + values.getCompendiumData().getName() + " Description: " +
                                    values.getCompendiumData().getDescription() +
                                    "\nCommon Locations: " + locations + "\n Click to View Image";
                        }
                        itemButton.setVisibility(View.VISIBLE);
                        itemButton.setOnClickListener(view ->{
                            concepts.concept().addConcept(new Concept(userid, username, finalSearch));

                            Log.i("q","Hello Everybody");
                            for(Concept x : concepts.concept().getAllConcepts()) {
                                Log.i("q",x.getName());
                                Log.i("q",x.getApi_reference());
                            }

                            String added = "Item Added";
                            textViewResult.setText(added);
                            itemButton.setVisibility(View.INVISIBLE);
                        });
                    } else {
                        content = "Nothing found, Please search again";
                    }
                    textViewResult.setText(content);
                    // assert values != null;

                    Log.i("Content is", content);

                }

                @Override
                public void onFailure(Call<CompendiumData> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });
        });

    }
}