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
import android.widget.TextView;

import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity {
private TextView textViewResult;
private CompendiumAPI compendiumAPI;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compendiumAPI = retrofit.create(CompendiumAPI.class);

        Call<CompendiumData> call = compendiumAPI.getData();

        call.enqueue(new Callback<CompendiumData>() {
            @Override
            public void onResponse(@NonNull Call<CompendiumData> call, @NonNull Response<CompendiumData> response) {
                if(!response.isSuccessful()) {
                    return;
                }

                CompendiumData values = response.body();
                //JsonObject res = response.body();


                String content = "";
                assert values != null;
                content += "Name: " + values.getCompendiumData().getName() + " id: " +
                        values.getCompendiumData().getId() + " category: "
                        + values.getCompendiumData().getCategory() + " image: "
                        + values.getCompendiumData().getImage();
                    Log.i("Content is", content);
                    textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<CompendiumData> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}