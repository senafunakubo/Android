package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-08-07.
 */

public class Card {
    private String imgURL;
    private String title;
    private int cooking_time;

    public Card(String imgURL, String title, int cooking_time) {
        this.imgURL = imgURL;
        this.title = title;
        this.cooking_time = cooking_time;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
    }

}
