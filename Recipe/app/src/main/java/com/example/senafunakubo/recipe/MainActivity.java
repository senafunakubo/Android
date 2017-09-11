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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


//implements GreenAdapter2.ListItemClickListener
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
//    private SectionsPageAdapter mSectionsPageAdapter;

    private List<Recipe> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recipe_adapter rAdapter;
    //    ArrayAdapter<String> searchAdapter;
    Recipe recipe;
    String foodName,recipeUrl,foodImgUrl,cookingTime;
    private TextView txt;
    private ImageView img;
    SharedPreference sharedPreference;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyFavs";
//    public static final String Fav = "favKey";
    private String urlJsonArray = "http://192.168.57.1/recipedata.json";
//    JSONArray arr;
//    JSONArray myLists;
    List<Recipe> myFavorite;
//    ArrayList<String> myFavRecipename;
    ArrayList<String> list;
    Recipe r;
    String title,ingredients,step1,step2,step3,step4,step5;
    int cookingTimeForJson;
    String imageUrl, webUrl;
    boolean favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);
        img = (ImageView) findViewById(R.id.img);

        sharedPreference = new SharedPreference();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        myFavorite = sharedPreference.getFavorites(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        rAdapter = new Recipe_adapter(recipeList);

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

                    }

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, Recipe_detail.class);

                        Recipe recipe = recipeList.get(position);
                        Log.d("Recipe",recipeList.get(position).getRecipe_title());

                        foodName = recipe.getRecipe_title();
                        recipeUrl = recipe.getWebUrl();
                        foodImgUrl = recipe.getImageUrl();
                        cookingTime = String.valueOf(recipe.getCooking_time());
                        boolean favOK = true;
                        intent.putExtra("foodName", foodName);
                        intent.putExtra("recipeUrl", recipeUrl);
                        intent.putExtra("foodImgUrl", foodImgUrl);
                        intent.putExtra("cookingTime", cookingTime);
                        intent.putExtra("favOK", favOK);
                        startActivity(intent);

                    }

                })
        );

        try {
            prepareRecipeData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
            rAdapter = new Recipe_adapter(recipeList);
            //recyclerView object
            recyclerView.setAdapter(rAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void prepareRecipeData() throws JSONException {

        if (recyclerView.equals(null)) {
            txt.setText("You need to add a recipe!");
            img.setImageResource(R.drawable.recipenote);
        }

        //Retrieve the data from SharedPreference
        List<Recipe> fetch = sharedPreference.getFavorites(getApplicationContext());
//        Log.d("check ", " true" + fetch);
        if (fetch != null) {
            list = new ArrayList<>() ;
            ArrayAdapter<Recipe> recipeArrayAdapter = new ArrayAdapter<Recipe>(
                    this,
                    android.R.layout.simple_list_item_1,
                    fetch
            );
            for(int i=0; i<recipeArrayAdapter.getCount();i++)
            {
                 r = recipeArrayAdapter.getItem(i);
                list.add(r.getRecipe_title());
                Log.d("List",r.getRecipe_title());
            }
            makeJsonArrayRequest(list);

            Recipe_adapter recipe_adapter = new Recipe_adapter(fetch);
            recyclerView.setAdapter(recipe_adapter);
            rAdapter.notifyDataSetChanged();
        }

    }


    // 3 Buttons

    public void selectAll(View view) {
        for (Recipe m : recipeList) {
            m.setSelected(true); //select is true, clear is false
            rAdapter.notifyDataSetChanged();
        }
    }

    public void clearAll(View view) {
        for (Recipe m : recipeList) {
            m.setSelected(false);
            rAdapter.notifyDataSetChanged();
        }
    }

    public void delete(View view) {

        int arraySize = recipeList.size();
        for (int i = 0; i < arraySize; i++) {
            Log.d("recipeTEST", recipeList.get(i).toString());
            if (recipeList.get(i).isSelected()) {
                recipeList.remove(i);
                i -= 1;
                arraySize -= 1;

                rAdapter.notifyDataSetChanged();
            }
        }
    }

    private void makeJsonArrayRequest(final ArrayList<String> nameList) throws JSONException {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJsonArray,
                    (String) null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject recipeJson = (JSONObject) response.get(i);
                            title = recipeJson.getString("name");
                            ingredients = recipeJson.getString("ingredients");
                            step1 = recipeJson.getString("Step1");
                            step2 = recipeJson.getString("Step2");
                            step3 = recipeJson.getString("Step3");
                            step4 = recipeJson.getString("Step4");
                            step5 = recipeJson.getString("Step5");
                            cookingTimeForJson = recipeJson.getInt("cookingTime");
                            imageUrl = recipeJson.getString("imageUrl");
                            webUrl = recipeJson.getString("webUrl");
                            favorite = recipeJson.getBoolean("favorite");

                            Log.d("title",title);

                            for (String favRecipeName : nameList) {
                                if (title.equals(favRecipeName)) {
                                    Recipe recipeData = new Recipe();
                                    recipeData.setRecipe_title(title);
                                    recipeData.setRecipe_ingredients(ingredients);
                                    recipeData.setStep1(step1);
                                    recipeData.setStep2(step2);
                                    recipeData.setStep3(step3);
                                    recipeData.setStep4(step4);
                                    recipeData.setStep5(step5);
                                    recipeData.setCooking_time(cookingTimeForJson);
                                    recipeData.setImageUrl(imageUrl);
                                    recipeData.setWebUrl(webUrl);
                                    recipeData.isFavorite(favorite);
                                    recipeList.add(recipeData);
                                   Log.d("recipeListJson", recipeData.getRecipe_title());

                                }
                            }
                        }
                        // ArrayList<String> list   ---> get the recipeTitle based on SharedPreferences
                        // List<Recipe> recipeList  ---> get the recipe based on JSON (need to change the order)

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                }

            });
            AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

//    public static final <T> void swap (List<T> l, int i, int j) {
//        Collections.<T>swap(l, i, j);
//    }

}