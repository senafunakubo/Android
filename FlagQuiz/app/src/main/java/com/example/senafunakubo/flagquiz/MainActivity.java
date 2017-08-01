package com.example.senafunakubo.flagquiz;

import android.content.Intent;
import android.preference.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //keys for reading data from SharedPreferences, two  strings are keyname
    public static final String CHOICES = "pref_numberOfChoices"; //value = 6
    public static final String REGIONS = "pref_regionsToInclude"; //value = asia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        MainActivityFragment quizFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.quizFragment);
//        quizFragment.updateGuessRows(PreferenceManager.getDefaultSharedPreferences(this));
//
//        quizFragment.updateRegions(PreferenceManager.getDefaultSharedPreferences(this));
//    }

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
