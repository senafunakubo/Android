package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by senafunakubo on 2017-07-27.
 */

public class Recipe_detail extends AppCompatActivity{

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);


//        Bundle bundle = getIntent().getExtras();
//            String udon = bundle.getString("udon");
//            webView.loadUrl(udon);
//
//            String masala = bundle.getString("masala");
//            webView.loadUrl(masala);

        String recipeUrlIntent = getIntent().getStringExtra("recipeUrl");
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(recipeUrlIntent);

    }

}
