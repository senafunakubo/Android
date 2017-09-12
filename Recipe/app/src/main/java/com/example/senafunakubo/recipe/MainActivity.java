package com.example.senafunakubo.recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Recipe> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recipe_adapter rAdapter;
    //    ArrayAdapter<String> searchAdapter;
    Recipe recipe;
    String foodName,recipeUrl,foodImgUrl,cookingTime;
    SharedPreference sharedPreference;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyFavs";
    List<Recipe> myFavorite;
    List<Recipe> fetch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreference = new SharedPreference();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        myFavorite = sharedPreference.getFavorites(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
//        rAdapter = new Recipe_adapter(recipeList);

        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(rLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        if (myFavorite != null) {
            rAdapter = new Recipe_adapter(myFavorite);
            recyclerView.setAdapter(rAdapter);
        }


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {


                    @Override
                    public void onLongClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, Recipe_detail.class);

                        Recipe recipe = recipeList.get(position);
                        Log.d("Recipe",recipeList.get(position).getRecipe_title());

                        foodName = recipe.getRecipe_title();
                        recipeUrl = recipe.getWebUrl();
                        foodImgUrl = recipe.getImageUrl();
                        cookingTime = String.valueOf(recipe.getCooking_time());
//                        boolean favOK = true;
                        intent.putExtra("foodName", foodName);
                        intent.putExtra("recipeUrl", recipeUrl);
                        intent.putExtra("foodImgUrl", foodImgUrl);
                        intent.putExtra("cookingTime", cookingTime);
//                        intent.putExtra("favOK", favOK);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemClick(View view, int position) {

                    }

                })
        );

//        try {
            prepareRecipeData();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(mBottomNav);
        Menu menu = mBottomNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.search_ic:
                        Intent intent1 = new Intent(MainActivity.this, SearchPage.class);
                        startActivity(intent1);
                        break;

                    case R.id.recipe_ic:
                        Intent intent2 = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.fav_ic:
                        Toast.makeText(MainActivity.this, "This is Favorite page!",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_refresh) {
            rAdapter = new Recipe_adapter(myFavorite);
            //recyclerView object
            recyclerView.setAdapter(rAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void prepareRecipeData(){

        //Retrieve the data from SharedPreference
        fetch = sharedPreference.getFavorites(getApplicationContext());
        if (fetch != null) {
            recipeList.addAll(fetch);
            Recipe_adapter recipe_adapter = new Recipe_adapter(fetch);
            recyclerView.setAdapter(recipe_adapter);
            rAdapter.notifyDataSetChanged();
        }

    }


    // 3 Buttons

    public void selectAll(View view) {
        for (Recipe m : recipeList) {
            m.setSelected(true);
        }
        ((Recipe_adapter) recyclerView.getAdapter()).notifyDataSetChanged();
    }

    public void clearAll(View view) {
        for (Recipe m : recipeList) {
            m.setSelected(false);
            ((Recipe_adapter) recyclerView.getAdapter()).notifyDataSetChanged();
        }
    }

    public void delete(View view) {

        int arraySize = recipeList.size();
        for (int i = 0; i < arraySize; i++) {
            if (recipeList.get(i).isSelected()) {
                recipeList.remove(i);
                fetch.remove(i);
                i -= 1;
                arraySize -= 1;
                ((Recipe_adapter) recyclerView.getAdapter()).notifyDataSetChanged();
            }
        }
        sharedPreference.saveFavorites(getApplicationContext(),fetch);
    }

}