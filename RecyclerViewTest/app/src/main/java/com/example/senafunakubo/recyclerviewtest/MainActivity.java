package com.example.senafunakubo.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private static final int LIST_COUNT = 100;
    private GreenAdapter2 mAdapter; //object name
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_main); // RecycleView layout
        rv = (RecyclerView)findViewById(R.id.rv1);
        LinearLayoutManager lv = new LinearLayoutManager(this);
        rv.setLayoutManager(lv);
        mAdapter = new GreenAdapter2(LIST_COUNT);
        rv.setAdapter(mAdapter);
    }

}
