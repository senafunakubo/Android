package com.example.senafunakubo.alllayout_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by senafunakubo on 2017-07-14.
 */


public class LinearLayout extends AppCompatActivity {

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);

        b1 = (Button)findViewById(R.id.linearBtnA);
        b2 = (Button)findViewById(R.id.linearBtnB);

        b1.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LinearLayout.this,MainActivity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LinearLayout.this,RelativeLayout.class);
                startActivity(i);
            }
        });
    }
}