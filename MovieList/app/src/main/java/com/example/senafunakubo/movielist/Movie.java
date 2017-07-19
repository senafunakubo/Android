package com.example.senafunakubo.movielist;

/**
 * Created by senafunakubo on 2017-07-18.
 */

public class Movie {

    private String title,genre,year;

    //constructor
    public Movie(String title, String genre, String year){
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    //getter
    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return genre;
    }

    public String getYear(){
        return year;
    }
}
