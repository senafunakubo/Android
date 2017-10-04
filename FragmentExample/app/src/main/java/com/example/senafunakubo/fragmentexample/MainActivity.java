package com.example.senafunakubo.fragmentexample;

import android.content.res.Configuration;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            //create an instance for landscapemode fragment
            LM_fragment lm_fragment = new LM_fragment();
            fragmentTransaction.replace(android.R.id.content, lm_fragment);
        }
        else if(config.orientation == Configuration.ORIENTATION_PORTRAIT)
         {
             //create an instance for portrait mode
             PM_fragment pm_fragment = new PM_fragment();
             fragmentTransaction.replace(android.R.id.content, pm_fragment);
         }
         fragmentTransaction.commit();
    }
}
