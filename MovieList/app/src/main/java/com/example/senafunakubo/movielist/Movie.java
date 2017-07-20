package com.example.senafunakubo.movielist;

/**
 * Created by senafunakubo on 2017-07-18.
 */

public class Movie {

    private String title,genre,year;
    private int imageUrl;

    //constructor
    public Movie(String title, String genre, String year, int imageUrl){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.imageUrl = imageUrl;
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

    public int getImageUrl(){
        return imageUrl;
    }
}
