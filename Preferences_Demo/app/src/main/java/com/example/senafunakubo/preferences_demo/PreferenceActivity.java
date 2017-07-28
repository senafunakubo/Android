package com.example.senafunakubo.preferences_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by senafunakubo on 2017-07-28.
 */

public class PreferenceActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,new PreferenceFragment1())
                .commit();
    }
}
