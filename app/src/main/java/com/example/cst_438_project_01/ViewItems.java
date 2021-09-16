package com.example.cst_438_project_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cst_438_project_01.data_model.Concept;
import com.example.cst_438_project_01.database.ConceptDb;
import com.example.cst_438_project_01.database.UserDatabase;

import java.util.List;

public class ViewItems extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        TextView viewItemsWelcome = findViewById(R.id.viewItemsWelcome);
        TextView itemsList = findViewById(R.id.itemsList);
        Button returnButton = findViewById(R.id.returnButton);
        int userid = getIntent().getIntExtra("USERID", 0);

        UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
        ConceptDb concepts = ConceptDb.getInstance(this.getApplicationContext());

        String welcomeMessage = userDatabase.userDao().findByUserID(userid).getUsername() + "'s Items";
        viewItemsWelcome.setText(welcomeMessage);

        List<Concept> items = concepts.concept().findAllByUserID(userid);

        for(Concept item: items) {
            String content = "";
            content += "Name: " + item.getName() + "\n";
            content += "API Reference: " + item.getApi_reference() + "\n\n";
            itemsList.append(content);
        }

        returnButton.setOnClickListener(this);
//        returnButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}