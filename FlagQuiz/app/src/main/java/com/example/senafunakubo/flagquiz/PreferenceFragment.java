package com.example.senafunakubo.flagquiz;

import android.os.Bundle;

/**
 * Created by senafunakubo on 2017-07-28.
 */

public class PreferenceFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
