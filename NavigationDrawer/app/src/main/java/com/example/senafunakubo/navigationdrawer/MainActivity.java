package com.example.senafunakubo.navigationdrawer;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//We use simple string Array as ArrayAdapter to fill the listView
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    //We need toogle 1) open & 2) close
    ActionBarDrawerToggle mDrawerToogle;
    //Update the title in the actionBar
    private String mTitle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mTitle = getTitle().toString();
        //add items into drawer
        addDrawerItems();
        //Setting up open() & close() for Drawer
        setupDrawer();
        //Add a toogle switch in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems(){
        String[] osArray = {"Android", "iOS", "Windows", "Linux", "OS X"};
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                osArray);
        mDrawerList.setAdapter(mAdapter);
        //We need to perform some action on each item in the
        // navigation drawer
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You choose a OS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer(){
        //initialize mDrawerToogle that uses context
        //drawerLayout & 2 string for open/close
        //2 methods
        //onDrawerOpened()
        //onDrawerClosed()
        mDrawerToogle = new ActionBarDrawerToggle(
                this,mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            //when a drawer is completely open

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("NAVIGATION");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
            }

            //Enable the drawer indicator
            //attach it to the toogle Object
        };
        mDrawerToogle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToogle);
    }

    //onPostCreate() lifecycle method of activity
    //sync the indicator to match the current
    //state of navigation drawer


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToogle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplfiableIfstatement
        if (id == R.id.action_settings){
            return true;
        }
        //action the navigation drawer toogle
        if (mDrawerToogle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
