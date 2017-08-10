package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-07-24.
 */

public class Recipe {

    private String recipe_title,recipe_ingredients;
    private int cooking_time;
    private String imageUrl;
    private String webUrl;
    private boolean isSelected;

    //constructor
    public Recipe(String recipe_title, String recipe_ingredients, int cooking_time, String webUrl, String imageUrl){
        this.recipe_title = recipe_title;
        this.recipe_ingredients = recipe_ingredients;
        this.cooking_time = cooking_time;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
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

    public int getCooking_time(){
        return cooking_time;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getWebUrl(){
        return webUrl;
    }

    public void setWebUrl(String webUrl){
        this.webUrl = webUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
