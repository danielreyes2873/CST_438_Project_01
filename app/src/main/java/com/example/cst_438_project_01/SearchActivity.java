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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
private TextView textViewResult;
private EditText searchField;
private CompendiumAPI compendiumAPI;
private Button searchButton;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textViewResult = findViewById(R.id.textView);
        searchButton = findViewById(R.id.button);
        searchField = findViewById(R.id.editTextSearch);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compendiumAPI = retrofit.create(CompendiumAPI.class);


        searchButton.setOnClickListener(view -> {
            String search = searchField.getText().toString();
            search = search.replace(' ','_');
            Call<CompendiumData> call = compendiumAPI.getData(search);
            call.enqueue(new Callback<CompendiumData>() {
                @Override
                public void onResponse(@NonNull Call<CompendiumData> call, @NonNull Response<CompendiumData> response) {
                    if(!response.isSuccessful()) {
                        return;
                    }

                    CompendiumData values = response.body();
                    String content = "";
                    if (values.getCompendiumData().getId()!=0) {
                        if (values.getCompendiumData().getCategory().equals("creatures")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() + "\n Click to View Image";
                        } else if (values.getCompendiumData().getCategory().equals("monsters")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() + " Click to View Image";
                        } else if (values.getCompendiumData().getCategory().equals("equipment")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() + "\n Attack: " + values.getCompendiumData().getAttack()
                                    + " Defense: " + values.getCompendiumData().getDefense() + "\n Click to View Image";
                        } else if (values.getCompendiumData().getCategory().equals("materials")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() + " Click to View Image";
                        } else {
                            content += "Name: " + values.getCompendiumData().getName() + " Description: " +
                                    values.getCompendiumData().getDescription() + " Click to View Image";
                        }

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