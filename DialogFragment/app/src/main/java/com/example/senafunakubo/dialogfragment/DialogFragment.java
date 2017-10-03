package com.example.senafunakubo.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by senafunakubo on 2017-07-26.
 */

public class DialogFragment extends android.app.DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialogfragment, container, false);
        return view;
    }
}
