package com.example.senafunakubo.movielist;

/**
 * Created by senafunakubo on 2017-07-18.
 */

public class Movie {

    private String title,genre,year;
    private int imageUrl;
    private boolean isSelected; // boolean variable for a movie

    //constructor
    public Movie(String title, String genre, String year, int imageUrl){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.imageUrl = imageUrl;
        this.isSelected = false;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }

    //getter for isSelected
    public boolean isSelected(){
        return isSelected;
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

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
