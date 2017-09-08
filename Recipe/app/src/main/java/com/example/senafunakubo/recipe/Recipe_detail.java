package com.example.senafunakubo.recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by senafunakubo on 2017-07-27.
 */

//レシピ詳細
public class Recipe_detail extends AppCompatActivity {

    private static String TAG = Recipe_detail.class.getSimpleName();
    private List<Recipe> recipeList = new ArrayList<>();
    private List<Recipe> recipeListForFav = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecipeMainAdapter recipeMainAdapter;

    // json response url
    private String urlJsonObj = "http://192.168.57.1/recipedata1.json";
    private String urlJsonArray = "http://192.168.57.1/recipedata.json";

    private Button startButton;
    private Button historyButton;
    private TextView timer;
    WebView webView;
    LikeButton likeButton;
    TextView foodName;
    TextView cookingTime;
    ImageView foodImg;
    Recipe recipeData;
    SharedPreference sharedPreference;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyFavs";
    public static final String Fav = "favKey";
    Set<String> arr; // = new JSONArray();
    SharedPreferences.Editor editor;
    int position;


    private Handler customHandler = new Handler();

    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    int setTime = 1;
    String foodNameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        sharedPreference = new SharedPreference();

        startButton = (Button) findViewById(R.id.start_clock);
        historyButton = (Button) findViewById(R.id.cooking_history);
        timer = (TextView) findViewById(R.id.timerValue);
        likeButton = (LikeButton) findViewById(R.id.fav_button);
        foodName = (TextView) findViewById(R.id.foodName);
        cookingTime = (TextView) findViewById(R.id.cookingTime);
        foodImg = (ImageView) findViewById(R.id.foodImg);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
         arr = new HashSet<String>() ;
        editor = sharedpreferences.edit();


        recyclerView = (RecyclerView) findViewById(R.id.recycleStep);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager myLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        position = getIntent().getIntExtra("position",0);
        foodNameIntent = getIntent().getStringExtra("foodName");
        final String foodImgIntent = getIntent().getStringExtra("foodImgUrl");
        String cookingTimeIntent = getIntent().getStringExtra("cookingTime");
        foodName.setText(foodNameIntent);
        cookingTime.setText(cookingTimeIntent + " mins");

        if (foodImgIntent.contains("http")){
            Picasso.with(this).load(foodImgIntent).error(R.drawable.error).into(foodImg);
        }
        else {
            foodImg.setImageResource(Integer.parseInt(foodImgIntent.substring(11)));
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimer, 0);

            }
        });


        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                sharedPreference.addFavorite(getApplicationContext(),recipeData);
//                arr.add(foodNameIntent);
                Toast.makeText(Recipe_detail.this, "You liked it", Toast.LENGTH_SHORT).show();
//                editor.putStringSet(Fav,arr);
//              Log.d("key value" ,"= " + sharedpreferences.getStringSet(Fav,null));
//                Log.d("position ", "= "+ recipeList.get(position));
//                editor.apply();

                Intent intent = new Intent(Recipe_detail.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(Recipe_detail.this, "You unliked it", Toast.LENGTH_SHORT).show();
            }
        });

//        makeJsonObjectRequest();
        makeJsonArrayRequest(foodNameIntent);

    }

        private void makeJsonArrayRequest(final String foodNameIntent) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,urlJsonArray,
                (String) null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject recipeJson = (JSONObject) response.get(i);
                                String title = recipeJson.getString("name");
                                String ingredients =  recipeJson.getString("ingredients");
                                String step1 = recipeJson.getString("Step1");
                                String step2 = recipeJson.getString("Step2");
                                String step3 = recipeJson.getString("Step3");
                                String step4 = recipeJson.getString("Step4");
                                String step5 = recipeJson.getString("Step5");
                                int cookingTime = recipeJson.getInt("cookingTime");
                                String imageUrl = recipeJson.getString("imageUrl");
                                String webUrl = recipeJson.getString("webUrl");
                                boolean favorite = recipeJson.getBoolean("favorite");

                                if (title.equals(foodNameIntent)) {
                                    recipeData = new Recipe();

                                    recipeData.setRecipe_title(title);
                                    recipeData.setRecipe_ingredients(ingredients);
                                    recipeData.setStep1(step1);
                                    recipeData.setStep2(step2);
                                    recipeData.setStep3(step3);
                                    recipeData.setStep4(step4);
                                    recipeData.setStep5(step5);
                                    recipeData.setCooking_time(cookingTime);
                                    recipeData.setImageUrl(imageUrl);
                                    recipeData.setWebUrl(webUrl);
                                    recipeData.isFavorite(favorite);
                                    recipeListForFav.add(recipeData);

                                    Recipe recipeData1 = new Recipe();
                                    Recipe recipeData2 = new Recipe();
                                    Recipe recipeData3 = new Recipe();
                                    Recipe recipeData4 = new Recipe();
                                    Recipe recipeData5 = new Recipe();
                                    Recipe recipeData6 = new Recipe();

                                    recipeData1.setStep1(ingredients);
                                    recipeData2.setStep1(step1);
                                    recipeData3.setStep1(step2);
                                    recipeData4.setStep1(step3);
                                    recipeData5.setStep1(step4);
                                    recipeData6.setStep1(step5);

                                    recipeList.add(recipeData1);
                                    recipeList.add(recipeData2);
                                    recipeList.add(recipeData3);
                                    recipeList.add(recipeData4);
                                    recipeList.add(recipeData5);
                                    recipeList.add(recipeData6);
                                    recipeMainAdapter = new RecipeMainAdapter(recipeList);
                                    recyclerView.setAdapter(recipeMainAdapter);

                                }
                            }

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



    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {

            String recipeUrlIntent = getIntent().getStringExtra("cookingTime");
            int recipeUrlIntentInt = Integer.parseInt(recipeUrlIntent);
//            Log.d("CookingTime", recipeUrlIntent);

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;

            if (mins < recipeUrlIntentInt) { //setTimeで何分以内か決める?
                timer.setText("" + mins + ":" + String.format("%02d", secs));
            }
            customHandler.postDelayed(this, 0); //これないとグチャる

        }
    };

}