package com.example.senafunakubo.flagquiz;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by senafunakubo on 2017-08-01.
 */

public class MainActivityFragment extends Fragment {
    private static final int FLAGS_IN_QUIZ = 10; //10問あるよのやつ

    private List<String> fileNameList;

    private LinearLayout quizLinearLayout;

    private int guessRows;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        return v;
    }
}
