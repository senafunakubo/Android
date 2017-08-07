package com.example.senafunakubo.recipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by senafunakubo on 2017-08-06.
 */

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        return view;
    }
}