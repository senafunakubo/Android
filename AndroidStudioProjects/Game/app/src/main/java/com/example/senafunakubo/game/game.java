package com.example.senafunakubo.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class game extends AppCompatActivity {

    TextView pointA;
    TextView pointB;
    Button teamA3points;
    Button teamA2points;
    Button teamAfree;
    Button teamB3points;
    Button teamB2points;
    Button teamBfree;
    Button resetAll;

    int scoreA = 0;
    int scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pointA = (TextView) findViewById(R.id.pointA);
        pointB = (TextView) findViewById(R.id.pointB);

        teamA3points = (Button) findViewById(R.id.buttonA3point);
        teamA2points = (Button) findViewById(R.id.buttonA2point);
        teamAfree = (Button) findViewById(R.id.buttonAfree);
        teamB3points = (Button) findViewById(R.id.buttonB3point);
        teamB2points = (Button) findViewById(R.id.buttonB2point);
        teamBfree = (Button) findViewById(R.id.buttonBfree);
        resetAll = (Button) findViewById(R.id.button_re);


        teamA3points.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamAof3poButtonOnClick(v);
                    }
                });

        teamA2points.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamAof2poButtonOnClick(v);
                    }
                });

        teamAfree.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamAfreeButtonOnClick(v);
                    }
                });

        teamB3points.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamBof3poButtonOnClick(v);
                    }
                });

        teamB2points.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamBof2poButtonOnClick(v);
                    }
                });

        teamBfree.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamBfreeButtonOnClick(v);
                    }
                });

        resetAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetButtonOnClick(v);
                    }
                });
    }

    public void teamAof3poButtonOnClick(View v) {
        scoreA = scoreA + 3;
        String scoreAst = Integer.toString(scoreA);
        pointA.setText(scoreAst);
    }

    public void teamBof3poButtonOnClick(View v) {
        scoreB = scoreB + 3;
        String scoreBst = Integer.toString(scoreB);
        pointB.setText(scoreBst);
    }

    public void teamAof2poButtonOnClick(View v){
        scoreA = scoreA + 2;
        String scoreAst = Integer.toString(scoreA);
        pointA.setText(scoreAst);
    }

    public void teamBof2poButtonOnClick(View v){
        scoreB = scoreB + 2;
        String scoreBst = Integer.toString(scoreB);
        pointB.setText(scoreBst);
    }

    public void teamAfreeButtonOnClick(View v){
        scoreA = scoreA + 1;
        String scoreAst = Integer.toString(scoreA);
        pointA.setText(scoreAst);
    }

    public void teamBfreeButtonOnClick(View v){
        scoreB = scoreB + 1;
        String scoreBst = Integer.toString(scoreB);
        pointB.setText(scoreBst);
    }

    public void resetButtonOnClick(View v){
        pointA.setText("0");
        pointB.setText("0");
    }
}