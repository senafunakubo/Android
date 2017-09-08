package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-09-07.
 */

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import com.example.senafunakubo.recipe.Recipe;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;
        import com.google.gson.Gson;

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Recipe> favorites) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    //ここがおかしい
    public void addFavorite(Context context, Recipe product) {
        List<Recipe> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Recipe>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Recipe product) {
        ArrayList<Recipe> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Recipe> getFavorites(Context context) {
        SharedPreferences settings;
        List<Recipe> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Recipe[] favoriteItems = gson.fromJson(jsonFavorites,
                    Recipe[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Recipe>(favorites);
        } else
            return null;

        return (ArrayList<Recipe>) favorites;
    }
}
