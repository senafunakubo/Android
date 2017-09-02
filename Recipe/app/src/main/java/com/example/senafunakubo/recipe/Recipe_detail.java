package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

/**
 * Created by senafunakubo on 2017-07-27.
 */

//レシピ詳細
public class Recipe_detail extends AppCompatActivity{

    private Button startButton;
    private Button historyButton;
    private TextView timer;
    WebView webView;
    LikeButton likeButton;
    TextView foodName;
    ImageView foodImg;

    private Handler customHandler = new Handler();

    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    int setTime = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        startButton = (Button)findViewById(R.id.start_clock);
        historyButton = (Button)findViewById(R.id.cooking_history);
        timer = (TextView)findViewById(R.id.timerValue);
        likeButton = (LikeButton) findViewById(R.id.fav_button);
        foodName = (TextView) findViewById(R.id.foodName);
        foodImg = (ImageView)findViewById(R.id.foodImg);

        String foodNameIntent = getIntent().getStringExtra("foodName");
        String foodImgIntent = getIntent().getStringExtra("foodImgUrl");
        foodName.setText(foodNameIntent);
        foodImg.setImageResource(Integer.parseInt(foodImgIntent.substring(11)));

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
                Toast.makeText(Recipe_detail.this,"You liked it", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(Recipe_detail.this,"You unliked it", Toast.LENGTH_SHORT).show();
            }
        });

        //WebView
//        String recipeUrlIntent = getIntent().getStringExtra("recipeUrl");
//        webView = (WebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(recipeUrlIntent);

    }

    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {

            String recipeUrlIntent = getIntent().getStringExtra("cookingTime");
            int recipeUrlIntentInt = Integer.parseInt(recipeUrlIntent);
            Log.d("CookingTime",recipeUrlIntent);

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;

            if (mins<recipeUrlIntentInt) { //setTimeで何分以内か決める?
                timer.setText("" + mins + ":" + String.format("%02d", secs));
            }
            customHandler.postDelayed(this, 0); //これないとグチャる

        }
    };

}