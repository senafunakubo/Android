package com.example.senafunakubo.optionmenu;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


//このクラス使ってないぜ！！！！！
//要注意


public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.feeds:
                feeds();
                return true;

            case R.id.friends:
                friends();
                return true;

            case R.id.about:
                about();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void feeds(){

        Toast.makeText(getApplicationContext(),"feeds is selected", Toast.LENGTH_SHORT).show();
    }

    public void friends(){

        Toast.makeText(getApplicationContext(),"friends is selected", Toast.LENGTH_SHORT).show();
    }

    public void about(){

        Toast.makeText(getApplicationContext(),"about is selected", Toast.LENGTH_SHORT).show();
    }



}
