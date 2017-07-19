package com.example.senafunakubo.movielist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


//implements GreenAdapter2.ListItemClickListener
public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private  MovieAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        LinearLayoutManager lv = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lv);
        recyclerView.setHasFixedSize(true);

        mAdapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        else if(id == R.id.action_refresh){
            mAdapter = new MovieAdapter(movieList);
            //recyclerView object
            recyclerView.setAdapter(mAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}