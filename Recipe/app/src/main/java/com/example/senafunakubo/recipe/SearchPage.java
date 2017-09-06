package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by senafunakubo on 2017-08-07.
 */

public class SearchPage extends AppCompatActivity {
    private static final String TAG = "SearchPage";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    final int[] ICONS = new int[]{
            R.drawable.rice,
            R.drawable.noodle,
            R.drawable.bread,
            R.drawable.others
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.getTabAt(3).setIcon(ICONS[3]);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#5D576B"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#5D576B"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#5D576B"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor("#5D576B"), PorterDuff.Mode.SRC_IN);

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(mBottomNav);
        Menu menu = mBottomNav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.search_ic:
                        Toast.makeText(SearchPage.this, "This is Search page!",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.recipe_ic:
                        Intent intent1 = new Intent(SearchPage.this, ItemActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.fav_ic:
                        Intent intent2 = new Intent(SearchPage.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter secAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        secAdapter.addFragment(new Tab1Fragment(),"Rice");
        secAdapter.addFragment(new Tab2Fragment(),"Noodle");
        secAdapter.addFragment(new Tab3Fragment(),"Bread");
        secAdapter.addFragment(new Tab4Fragment(),"Others");
        viewPager.setAdapter(secAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
