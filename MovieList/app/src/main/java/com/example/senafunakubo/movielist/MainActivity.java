package com.example.senafunakubo.movielist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        recyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
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

    public void prepareMovieData(){
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Split", "Horror, Thriller", "2017",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Get Out", "Horror, Mystery", "2017",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Don't Breathe", "Crime, Horror, Thriller", "2016",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("The Conjuring", "Horror, Mystery, Thriller", "2013",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Fight Club", "Drama", "1999",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("3 Idiots", "Adventure, Comedy, Drama", "2009",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("PK", "Comedy, Drama, Fantasy", "2014",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Life", "Horror, Sci-Fi, Thriller", "2017",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("Moonlight", "Drama", "2016",R.drawable.sushimenicon);
        movieList.add(movie);

        movie = new Movie("The Wolf of Wall Street", "Biography, Comedy, Crime", "2013",R.drawable.sushimenicon);
        movieList.add(movie);
    }

}