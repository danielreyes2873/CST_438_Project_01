package com.example.cst_438_project_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cst_438_project_01.data_model.Concept;
import com.example.cst_438_project_01.database.ConceptDb;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {

    private TextView textViewResult;
    private EditText searchField;
    private CompendiumAPI compendiumAPI;
    private Button searchButton;
    private Button itemButton;

    //used for Popup window
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    //Used to close Popup
    private Button closeButton;

    //This will hold the image
    private ImageView image;
    private TextView imageName;
  
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
                                showImage(values.getCompendiumData().getImage(), values.getCompendiumData().getName());
                            } else {
                                content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                        values.getCompendiumData().getDescription() + "\n Click to View Image";
                                showImage(values.getCompendiumData().getImage(), values.getCompendiumData().getName());
                            }
                        } else if (values.getCompendiumData().getCategory().equals("monsters")) {
                            for (String drop : values.getCompendiumData().getDrops()) {
                                drops.append(drop).append(", ");
                            }
                            content += "Name: " + values.getCompendiumData().getName() + "\nDescription: " +
                                    values.getCompendiumData().getDescription() + "\nDrops: " + drops
                                    + "\nCommon Locations: " + locations + "\n Click to View Image";
                            showImage(values.getCompendiumData().getImage(), values.getCompendiumData().getName());
                        } else if (values.getCompendiumData().getCategory().equals("equipment")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() + "\n Attack: " + values.getCompendiumData().getAttack()
                                    + " Defense: " + values.getCompendiumData().getDefense() +
                                    "\nCommon Locations: " + locations + "\n Click to View Image";
                            showImage(values.getCompendiumData().getImage(), values.getCompendiumData().getName());
                        } else if (values.getCompendiumData().getCategory().equals("materials")) {
                            content += "Name: " + values.getCompendiumData().getName() + "\n Description: " +
                                    values.getCompendiumData().getDescription() +
                                    "\nCommon Locations: " + locations + "\n Click to View Image";
                            showImage(values.getCompendiumData().getImage(), values.getCompendiumData().getName());
                        } else {
                            content += "Name: " + values.getCompendiumData().getName() + " Description: " +
                                    values.getCompendiumData().getDescription() +
                                    "\nCommon Locations: " + locations + "\n Click to View Image";
                            showImage(values.getCompendiumData().getImage(), values.getCompendiumData().getName());
                        }
                        itemButton.setVisibility(View.VISIBLE);
                        itemButton.setOnClickListener(view ->{
                            concepts.concept().addConcept(new Concept(userid, username, finalSearch));
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

    public void showImage(String url, String nameOfImage){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popUp = getLayoutInflater().inflate(R.layout.image_popup, null);

        closeButton = (Button) popUp.findViewById(R.id.closeButton);
        image = (ImageView) popUp.findViewById(R.id.itemImage);
        imageName = (TextView) popUp.findViewById(R.id.itemName);

        imageName.setText(nameOfImage);

        Glide.with(this)
                .load(url)
                .into(image);

        dialogBuilder.setView(popUp);
        dialog = dialogBuilder.create();
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


}