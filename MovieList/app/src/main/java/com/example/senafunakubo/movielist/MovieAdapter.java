package com.example.senafunakubo.movielist;

//import android.support.v7.app.AlertController;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by senafunakubo on 2017-07-18.
 */

//extends recyclerView adapter with dataType as classname.InnerClassName
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<Movie> movie_list;
    private  int colorno;
    Boolean isSelectedAll;

    public MovieAdapter(List<Movie> movie_list){

        this.movie_list = movie_list;

        //For background color
        colorno = 0;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView movieGenre;
        TextView movieYear;
        ImageView imageView;
        CheckBox checkBox;

        MyViewHolder(View view) {
            super(view);
            movieTitle = (TextView) view.findViewById(R.id.tv_title);
            movieGenre = (TextView) view.findViewById(R.id.tv_genre);
            movieYear = (TextView) view.findViewById(R.id.tv_year);

            imageView = (ImageView) view.findViewById(R.id.movie_image);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.number_of_list,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        //For background color
        int bgcolorno = ColorUtils.getViewHolderBackgroundColorFromInstance(context,colorno);
        viewHolder.itemView.setBackgroundColor(bgcolorno);
        colorno++;

        return viewHolder;
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movie movie = movie_list.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieGenre.setText(movie.getGenre());
        holder.movieYear.setText(movie.getYear());
        holder.imageView.setImageResource(movie.getImageUrl());

    }

    public int getItemCount() {

        return movie_list.size();

    }

}