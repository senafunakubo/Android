package com.example.senafunakubo.doglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

//import static com.example.senafunakubo.doglist.R.id.parent;

public class Doglist extends AppCompatActivity {

    GridView gv;
    String[] choices = new String[16];

    {
        for(int i=0; i<16; i++){
            choices[i] = Integer.toString(i+1);
        }

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doglist);

        gv = (GridView) findViewById(R.id.gv1);
        gv.setAdapter(new ImageAdapter(this));

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_doglist, choices);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String selectedValue = (String) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),selectedValue, Toast.LENGTH_SHORT).show();


            }

        });

    }

}
