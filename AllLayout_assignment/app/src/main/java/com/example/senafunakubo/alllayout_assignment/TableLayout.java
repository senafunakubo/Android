package com.example.senafunakubo.alllayout_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by senafunakubo on 2017-07-14.
 */

public class TableLayout extends AppCompatActivity{

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        b1 = (Button)findViewById(R.id.tableBtnA);
        b2 = (Button)findViewById(R.id.tableBtnB);

        b1.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TableLayout.this,RelativeLayout.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TableLayout.this,FrameLayout.class);
                startActivity(i);
            }
        });
    }
}
