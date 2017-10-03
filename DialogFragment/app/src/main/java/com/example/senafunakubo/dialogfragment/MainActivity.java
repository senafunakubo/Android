package com.example.senafunakubo.dialogfragment;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button dfragButton;
    Button alertFragButton;
    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Locate the button in activity_main.xml
        dfragButton = (Button) findViewById(R.id.dfragbutton);
        alertFragButton = (Button) findViewById(R.id.alertdfragbutton);

        dfragButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DialogFragment();
                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");
            }
        });

        alertFragButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                 AlertDialogFragment aFragment = new AlertDialogFragment();
                 aFragment.show(fm," Alert Dialog Fragment");
            }
        });
    }

}
