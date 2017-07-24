package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-07-24.
 */

public class Recipe {

    private String recipe_title,recipe_ingredients,recipe_time;
    private int imageUrl;
    private boolean isSelected;

    //constructor
    public Recipe(String recipe_title, String recipe_ingredients, String recipe_time, int imageUrl){
        this.recipe_title = recipe_title;
        this.recipe_ingredients = recipe_ingredients;
        this.recipe_time = recipe_time;
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
    public String getRecipe_title(){
        return recipe_title;
    }

    public String getRecipe_ingredients(){
        return recipe_ingredients;
    }

    public String getRecipe_time(){
        return recipe_time;
    }

    public int getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

}
