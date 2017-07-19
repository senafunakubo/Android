package com.example.senafunakubo.movielist;

//import android.support.v7.app.AlertController;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by senafunakubo on 2017-07-18.
 */

//extends recyclerView adapter with dataType as classname.InnerClassName
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<Movie> movie_list;

    public MovieAdapter(List<Movie> movie_list){

        this.movie_list = movie_list;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView movieGenre;
        TextView movieYear;

        MyViewHolder(View view) {
            super(view);
            movieTitle = (TextView) view.findViewById(R.id.tv_title);
//            movieTitle.setOnClickListener(this);
//            movieTitle.setOnClickListener((View.OnClickListener) this);

            movieGenre = (TextView) view.findViewById(R.id.tv_genre);
            movieYear = (TextView) view.findViewById(R.id.tv_year);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.number_of_list,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movie movie = movie_list.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieGenre.setText(movie.getGenre());
        holder.movieYear.setText(movie.getYear());

    }

    public int getItemCount() {

        return movie_list.size();

    }

}