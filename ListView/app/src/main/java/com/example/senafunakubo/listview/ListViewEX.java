package com.example.senafunakubo.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewEX extends AppCompatActivity {

// Array of strings...

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Mac OS","Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Mac OS "};

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.normallistviewex);

        //Array Adapter Takes 3 parameters
        //1. context, 2. Layout, 3. Data

        ArrayAdapter a = new  ArrayAdapter<String>(this,R.layout.activity_list_view,mobileArray);
        ListView l = (ListView)findViewById(R.id.mobile_list) ;
        l.setAdapter(a);

//        l.setOnItemClickListener(new AdapterView.OnItemClickListner(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position){
//                //Text on which you are clicking
//                String itemValue = (String)l.getItemAtPosition(position);
//
//                //Toast
//                Toast.makeText(getApplicationContext(),"Position: ", position);
//            }
//
//        });

    }

}