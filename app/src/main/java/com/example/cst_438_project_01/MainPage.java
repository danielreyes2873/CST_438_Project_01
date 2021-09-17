package com.example.cst_438_project_01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {
    private Button allitems;
    private Button youritems;
    private Button searchpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        allitems = findViewById(R.id.allItemBttn);
        youritems = findViewById(R.id.yourItemBttn);
        searchpage = findViewById(R.id.SearchBttn);
        String user = getIntent().getStringExtra("USERNAME");
        int userid = getIntent().getIntExtra("USERID", 0);

        allitems.setOnClickListener(view -> {
            Intent intent = new Intent(MainPage.this, ViewAllAPI.class);
            intent.putExtra("USERNAME", user);
            intent.putExtra("USERID", userid);
            startActivity(intent);
        });
        searchpage.setOnClickListener(view ->{
            Intent intent = new Intent(MainPage.this, SearchActivity.class);
            intent.putExtra("USERNAME", user);
            intent.putExtra("USERID", userid);
            startActivity(intent);
        });

        youritems.setOnClickListener(view -> {
            Intent intent = new Intent(MainPage.this, itemsViewRec.class);
            intent.putExtra("USERID",userid);
            startActivity(intent);

        });
    }
}
