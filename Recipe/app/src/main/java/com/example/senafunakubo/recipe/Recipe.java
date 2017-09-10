package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-07-24.
 */

public class Recipe {

    private String recipe_title,recipe_ingredients;
    private int cooking_time;
    private String step1,step2,step3,step4,step5;
    private String imageUrl,webUrl;
    private boolean isSelected, isFavorite;

    public Recipe(){}

    //constructor
    public Recipe(String recipe_title, String recipe_ingredients, String step1, String step2,
                  String step3, String step4, String step5,
                  int cooking_time, String webUrl, String imageUrl){
        this.recipe_title = recipe_title;
        this.recipe_ingredients = recipe_ingredients;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.step4 = step4;
        this.step5 = step5;
        this.cooking_time = cooking_time;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
        this.isSelected = false;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    //getter for isSelected
    public boolean isSelected(){
        return isSelected;
    }

    public boolean isFavorite(boolean fav){
        return fav;
    }


    public String getRecipe_title(){
        return recipe_title;
    }

    public void setRecipe_title(String recipe_title){
        this.recipe_title = recipe_title;
    }

    public String getRecipe_ingredients(){
        return recipe_ingredients;
    }

    public void setRecipe_ingredients(String recipe_ingredients){
        this.recipe_ingredients = recipe_ingredients;
    }

    public String getStep1(){
        return step1;
    }

    public String getStep2(){
        return step2;
    }

    public String getStep3(){
        return step3;
    }

    public String getStep4(){
        return step4;
    }

    public String getStep5(){
        return step5;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

    public void setStep4(String step4) {
        this.step4 = step4;
    }

    public void setStep5(String step5) {
        this.step5 = step5;
    }

    public int getCooking_time(){
        return cooking_time;
    }

    public void setCooking_time(int cooking_time){
        this.cooking_time = cooking_time;
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

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
