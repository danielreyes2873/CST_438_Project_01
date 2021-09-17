package com.example.cst_438_project_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cst_438_project_01.data_model.Concept;
import com.example.cst_438_project_01.database.ConceptDb;
import com.example.cst_438_project_01.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class itemsViewRec extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_view_rec);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        int userid = getIntent().getIntExtra("USERID", 0);

        UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
        ConceptDb concepts = ConceptDb.getInstance(this.getApplicationContext());

        List<Concept> items = concepts.concept().findAllByUserID(userid);

        for(Concept item: items) {
            exampleList.add(new ExampleItem(R.drawable.ic_triforce_seeklogo_com, item.getApi_reference(),item.getName()));
        }


    }
}