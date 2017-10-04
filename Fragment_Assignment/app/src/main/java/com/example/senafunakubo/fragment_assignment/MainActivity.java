package com.example.senafunakubo.fragment_assignment;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //it gives you the phone's orientation
        //portrait or landscape
        Configuration config = getResources().getConfiguration();

        //create a instance for fragment
        FragmentManager fragmentManager = getFragmentManager();

        //create a fragmentTransaction instance
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //check the orientation
        //act accordingly
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            //create an instance for landscapemode fragment
            FragmentOne fragmentOne = new FragmentOne();
            fragmentTransaction.replace(android.R.id.content, fragmentOne);

        }
        else if(config.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            //create an instance for portrait mode
            FragmentTwo fragmentTwo = new FragmentTwo();
            fragmentTransaction.replace(android.R.id.content, fragmentTwo);

        }
        fragmentTransaction.commit();
    }
}
