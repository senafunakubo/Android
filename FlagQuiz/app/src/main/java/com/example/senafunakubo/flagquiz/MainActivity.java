package com.example.senafunakubo.flagquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    //keys for reading data from SharedPreferences, two  strings are keyName
    public static final String CHOICES = "pref_numberOfChoices"; //value = 6
    public static final String REGIONS = "pref_regionsToInclude"; //value = asia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

    }

    @Override
    protected void onStart(){
        super.onStart();

        //インスタンス作成
        MainActivityFragment quizFragment = (MainActivityFragment)
                getFragmentManager().findFragmentById(R.id.quizFragment);

        Context context = getApplicationContext();
        quizFragment.updateGuessRows(PreferenceManager.getDefaultSharedPreferences(context));
        quizFragment.updateRegions(PreferenceManager.getDefaultSharedPreferences(this));
        quizFragment.startQuiz();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent preferencesIntent = new Intent(this, PreferenceActivity.class);
        startActivity(preferencesIntent);
        return super.onOptionsItemSelected(item);
    }

}
