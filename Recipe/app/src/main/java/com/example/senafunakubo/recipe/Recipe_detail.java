package com.example.senafunakubo.recipe;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by senafunakubo on 2017-07-27.
 */

//レシピ詳細
public class Recipe_detail extends AppCompatActivity {

    private static String TAG = Recipe_detail.class.getSimpleName();
    private List<Recipe> recipeList = new ArrayList<>();
    private List<Recipe> recipeList1 = new ArrayList<>();
    private Timer timer1;
    private TimerTask timerTask;
    private boolean isRunning = false;
    // json response url
    private String urlJsonArray = "http://192.168.57.1/recipedata.json";
    private Button startButton;
    private Button stopButton;
    private TextView timer;
    LikeButton likeButton;
    TextView foodName;
    TextView cookingTime;
    TextView servings;
    ImageView foodImg;
    Recipe recipeData;
    SharedPreference sharedPreference;
    List<Recipe> fetch;

    private Handler customHandler = new Handler();
    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    //    int setTime = 1;
    long timeSwapBuff = 0L;
    String foodNameIntent;
    int count = -1;
    public TextView content;
    public TextView stepNumber;

    String recipeUrlIntent;
    int recipeUrlIntentInt;
    int secs;
    int mins;
    long stopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        sharedPreference = new SharedPreference();

        startButton = (Button) findViewById(R.id.start_clock);
        stopButton = (Button) findViewById(R.id.stop_clock);
        timer = (TextView) findViewById(R.id.timerValue);
        likeButton = (LikeButton) findViewById(R.id.fav_button);
        foodName = (TextView) findViewById(R.id.foodName);
        cookingTime = (TextView) findViewById(R.id.cookingTime);
        foodImg = (ImageView) findViewById(R.id.foodImg);
        servings = (TextView) findViewById(R.id.servings);

        content = (TextView) findViewById(R.id.stepContent);
        stepNumber = (TextView) findViewById(R.id.stepNum);
        timer1 = new Timer();

        //Intent from the other pages
        foodNameIntent = getIntent().getStringExtra("foodName");
        foodName.setText(foodNameIntent);

        String cookingTimeIntent = getIntent().getStringExtra("cookingTime");
        cookingTime.setText(cookingTimeIntent + " mins");

        String servingsIntent = getIntent().getStringExtra("servings");
        servings.setText(servingsIntent + " servings");

        final String foodImgIntent = getIntent().getStringExtra("foodImgUrl");

        if (foodImgIntent.contains("http")) {
            Picasso.with(this).load(foodImgIntent).error(R.drawable.error).into(foodImg);
        } else {
            foodImg.setImageResource(Integer.parseInt(foodImgIntent.substring(11)));
        }

        makeJsonArrayRequest(foodNameIntent);
        prepareRecipeData();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = true;
                startTime = SystemClock.uptimeMillis();

                customHandler.postDelayed(updateTimer, 0);

                timer1 = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        timerMethod();
                        count++;
                    }
                };
                timer1.scheduleAtFixedRate(timerTask, 1000, 10000);
                // 1秒後に開始、10秒で次の画面へ
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
                stopTime = timeInMilliseconds;
//              timeSwapBuff += timeInMilliseconds;
                Log.d("stop the time ", " = " + stopTime);
                customHandler.removeCallbacks(updateTimer);
                timer1.cancel();
            }
        });


        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                sharedPreference.addFavorite(getApplicationContext(), recipeData);
                Toast.makeText(Recipe_detail.this, "You liked it", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                sharedPreference.removeFavorite(getApplicationContext(), recipeData);
                removeRecipeData();
                Toast.makeText(Recipe_detail.this, "You unliked it", Toast.LENGTH_SHORT).show();
            }
        });

//        makeJsonArrayRequest(foodNameIntent);
//        prepareRecipeData();
//        timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                timerMethod();
//                count++;
//            }
//        };
//        timer1.scheduleAtFixedRate(timerTask, 2000, 10000);

    }

    private void makeJsonArrayRequest(final String foodNameIntent) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJsonArray,
                (String) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject recipeJson = (JSONObject) response.get(i);
                        String title = recipeJson.getString("name");
                        String ingredients = recipeJson.getString("ingredients");
                        String step1 = recipeJson.getString("Step1");
                        String step2 = recipeJson.getString("Step2");
                        String step3 = recipeJson.getString("Step3");
                        String step4 = recipeJson.getString("Step4");
                        String step5 = recipeJson.getString("Step5");
                        int cookingTime = recipeJson.getInt("cookingTime");
                        int servingsJson = recipeJson.getInt("servings");
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
                            recipeData.setServings(servingsJson);
                            recipeData.setImageUrl(imageUrl);
                            recipeData.setWebUrl(webUrl);
                            recipeData.isFavorite(favorite);

                            recipeList.add(recipeData);

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

    public void prepareRecipeData() {

        //Retrieve the data from SharedPreference
        fetch = sharedPreference.getFavorites(getApplicationContext());
        if (fetch != null) {
            recipeList1.addAll(fetch);

            for (int i = 0; i < recipeList1.size(); i++) {
                recipeList1.get(i).setFavorite(true);

                if (recipeList1.get(i).getRecipe_title().equals((String) foodName.getText())) {
                    likeButton.setLiked(true);
                }
            }
        }

    }

    public void removeRecipeData() {
        fetch = sharedPreference.getFavorites(getApplicationContext());

        for (int i = 0; i < recipeList1.size(); i++) {
            if (recipeList1.get(i).getRecipe_title().equals((String) foodName.getText())) {
                recipeList1.remove(i);
                fetch.remove(i);
                likeButton.setLiked(false);
            }
        }
        sharedPreference.saveFavorites(getApplicationContext(), fetch);
    }


    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if (isRunning == true) {

                if(stopTime==0) //stop押したことないとき
                {
                    timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
                    updatedTime = timeInMilliseconds;
                    secs = (int) (updatedTime / 1000);
                    mins = secs / 60;
                    secs = secs % 60;

                    timer.setText("" + mins + ":" + String.format("%02d", secs));
                }
                else if(stopTime > 0) //1度でもストップが押されたら
                {
                    timeInMilliseconds = SystemClock.uptimeMillis() - startTime + stopTime;
                    updatedTime = timeInMilliseconds;
                    secs = (int) (updatedTime / 1000);
                    mins = secs / 60;
                    secs = secs % 60;

                    timer.setText("" + mins + ":" + String.format("%02d", secs));

                }
                customHandler.postDelayed(this, 0); //これないとグチャる
            }
        }
    };


    private void timerMethod() {

        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {
            switch (count) {
                case 0:
                    content.setText(recipeData.getRecipe_ingredients());
                    Log.d("data ", "= " + recipeData.getRecipe_ingredients());
                    break;
                case 1:
                    content.setText(recipeData.getStep1());
                    Log.d("data ", "= " + recipeData.getStep1());
                    break;
                case 2:
                    content.setText(recipeData.getStep2());
                    Log.d("data ", "= " + recipeData.getStep2());
                    break;
                case 3:
                    content.setText(recipeData.getStep3());
                    Log.d("data ", "= " + recipeData.getStep3());
                    break;
                case 4:
                    content.setText(recipeData.getStep4());
                    Log.d("data ", "= " + recipeData.getStep4());
                    break;
                case 5:
                    content.setText(recipeData.getStep5());
                    Log.d("data ", "= " + recipeData.getStep5());
                    break;
            }
        }
    };


}