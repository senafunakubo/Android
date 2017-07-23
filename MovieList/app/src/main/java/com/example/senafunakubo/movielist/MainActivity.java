package com.example.senafunakubo.movielist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


//implements GreenAdapter2.ListItemClickListener
public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private  MovieAdapter mAdapter;
    private int[] selectedMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015",R.drawable.madmax);
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015",R.drawable.inside);
        movieList.add(movie);

        movie = new Movie("Split", "Horror, Thriller", "2017",R.drawable.split);
        movieList.add(movie);

        movie = new Movie("Get Out", "Horror, Mystery", "2017",R.drawable.getout);
        movieList.add(movie);

        movie = new Movie("Don't Breathe", "Crime, Horror, Thriller", "2016",R.drawable.dont);
        movieList.add(movie);

        movie = new Movie("The Conjuring", "Horror, Mystery, Thriller", "2013",R.drawable.conjuring);
        movieList.add(movie);

        movie = new Movie("Fight Club", "Drama", "1999",R.drawable.fight);
        movieList.add(movie);

        movie = new Movie("3 Idiots", "Adventure, Comedy, Drama", "2009",R.drawable.idiots);
        movieList.add(movie);

        movie = new Movie("PK", "Comedy, Drama, Fantasy", "2014",R.drawable.pk);
        movieList.add(movie);

        movie = new Movie("Life", "Horror, Sci-Fi, Thriller", "2017",R.drawable.life);
        movieList.add(movie);

        movie = new Movie("Moonlight", "Drama", "2016",R.drawable.moon);
        movieList.add(movie);

        movie = new Movie("The Wolf of Wall Street", "Biography, Comedy, Crime", "2013",R.drawable.thewolf);
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    public void selectAll(View view){
        for (Movie m : movieList){
            m.setSelected(true); //select is true, clear is false
            mAdapter.notifyDataSetChanged();
        }
    }

    public void clearAll(View view){
        for (Movie m : movieList){
            m.setSelected(false); //select is true, clear is false
            mAdapter.notifyDataSetChanged();
        }
    }

    public void delete(View view){
//        for (int i=0; i<movieList.size(); i++){
//            if (movieList.get(i).isSelected()){
//                movieList.remove(i);
//            }
//        }
//        mAdapter.notifyDataSetChanged();

        int arraySize = movieList.size();
        for(int i=0; i<arraySize; i++){
            if( movieList.get(i).isSelected()){
                movieList.remove(i);
                i -= 1;
                arraySize -= 1;

                mAdapter.notifyDataSetChanged();
            }
        }
    }




}