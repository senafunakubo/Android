package com.example.senafunakubo.alllayout_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView linear;
    TextView relative;
    TextView table;
    TextView frame;
    TextView listView;
    TextView gridView;
    TextView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = (TextView)findViewById(R.id.Linear);
        relative = (TextView)findViewById(R.id.Relative);
        table = (TextView)findViewById(R.id.Table);
        frame = (TextView)findViewById(R.id.Frame);
        listView = (TextView)findViewById(R.id.List);
        gridView = (TextView)findViewById(R.id.Grid);
        recycler = (TextView)findViewById(R.id.Recycler);

        linear.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LinearLayout.class);
                startActivity(i);
            }
        });

        relative.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RelativeLayout.class);
                startActivity(i);
            }
        });

        table.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TableLayout.class);
                startActivity(i);
            }
        });

        frame.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,FrameLayout.class);
                startActivity(i);
            }
        });

        listView.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListView.class);
                startActivity(i);
            }
        });

        gridView.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,GridView.class);
                startActivity(i);
            }
        });

        recycler.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Recycler.class);
                startActivity(i);
            }
        });
    }
}
