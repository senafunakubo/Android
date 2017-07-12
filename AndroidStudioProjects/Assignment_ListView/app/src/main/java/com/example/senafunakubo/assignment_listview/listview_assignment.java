package com.example.senafunakubo.assignment_listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listview_assignment extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normallistview);

        String[] menuNameArray = getResources().getStringArray(R.array.menu_items);
        //Array Adapter Takes 3 parameters
        //1. context, 2. Layout, 3. Data

        ArrayAdapter a = new  ArrayAdapter<String>(this,R.layout.activity_listview_assignment,menuNameArray);
        ListView l = (ListView)findViewById(R.id.ListView_menu);
        l.setAdapter(a);
    }
}
