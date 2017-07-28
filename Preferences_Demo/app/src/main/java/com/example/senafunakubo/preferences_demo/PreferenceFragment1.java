package com.example.senafunakubo.preferences_demo;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by senafunakubo on 2017-07-28.
 */

public class PreferenceFragment1 extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences1);
    }
}
