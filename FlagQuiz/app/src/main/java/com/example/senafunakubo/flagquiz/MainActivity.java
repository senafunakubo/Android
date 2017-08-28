package com.example.senafunakubo.flagquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.preference.*;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    //keys for reading data from SharedPreferences, two  strings are keyName
    public static final String CHOICES = "pref_numberOfChoices"; //value = 6
    public static final String REGIONS = "pref_regionsToInclude"; //value = asia
    public boolean phoneDevice = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

//        Register listener for Shared~
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(preferenceChangeListener);


        //define a screen size
        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        //if device is a tablet, set phoneDevice to false
        // if running on phone-sized device, allow only portrait orientation
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            phoneDevice = false;

        if (phoneDevice){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
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

    private OnSharedPreferenceChangeListener preferenceChangeListener = new OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            MainActivityFragment quizFragment =
                    (MainActivityFragment) getFragmentManager().findFragmentById(R.id.quizFragment);

            //check which preference is changed
            if(key.equals(CHOICES)){
                quizFragment.updateGuessRows(sharedPreferences);
                quizFragment.startQuiz();
            }
            else if (key.equals(REGIONS)){
                    quizFragment.updateRegions(sharedPreferences);
                    quizFragment.startQuiz();
            }
            Toast.makeText(getApplicationContext(),"The quiz is reset",Toast.LENGTH_SHORT).show();
        }
    };

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
