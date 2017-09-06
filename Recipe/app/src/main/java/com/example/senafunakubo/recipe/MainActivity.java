package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


//implements GreenAdapter2.ListItemClickListener
public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "MainActivity";
//    private SectionsPageAdapter mSectionsPageAdapter;

    private List<Recipe> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recipe_adapter rAdapter;
//    ArrayAdapter<String> searchAdapter;
    Recipe recipe;
    private TextView txt;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.txt);
        img = (ImageView)findViewById(R.id.img);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        rAdapter = new Recipe_adapter(recipeList);

        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(rLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);

        prepareRecipeData();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this,recyclerView, new RecyclerItemClickListener.OnItemClickListener() {


                    @Override public void onLongClick(View view, int position) {

                    }

                    @Override public void onItemClick(View view, int position) {

                            Intent intent = new Intent(MainActivity.this, Recipe_detail.class);
                            Recipe recipe = recipeList.get(position);
                            String recipeUrl = recipe.getWebUrl();
                            String cookingTime = String.valueOf(recipe.getCooking_time());
                            intent.putExtra("recipeUrl", recipeUrl);
                            intent.putExtra("cookingTime", cookingTime);
                            startActivity(intent);

                    }

                })
        );

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(mBottomNav);
        Menu menu = mBottomNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.search_ic:
                        Intent intent1 = new Intent(MainActivity.this, SearchPage.class);
                        startActivity(intent1);
                        break;

                    case R.id.recipe_ic:
                        Intent intent2 = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.fav_ic:
                        Toast.makeText(MainActivity.this, "This is Favorite page!",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });


//        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
//
//        mViewPager = (ViewPager)findViewById(R.id.container);
//        setupViewPager(mViewPager);
//
//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);


    }


//    private void setupViewPager(ViewPager viewPager){
//        SectionsPageAdapter secAdapter = new SectionsPageAdapter(getSupportFragmentManager());
//        secAdapter.addFragment(new Tab1Fragment(),"3-15mins");
//        secAdapter.addFragment(new Tab2Fragment(),"15-30mins");
//        secAdapter.addFragment(new Tab3Fragment(),"30mins+");
//        viewPager.setAdapter(secAdapter);
//    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        else if(id == R.id.action_refresh){
            rAdapter = new Recipe_adapter(recipeList);
            //recyclerView object
            recyclerView.setAdapter(rAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void prepareRecipeData(){
        if (recipeList.isEmpty()){
            txt.setText("You need to add a recipe!");
            img.setImageResource(R.drawable.recipenote);
        }
        else {

        }

//        rAdapter.notifyDataSetChanged();

    }


    // 3 Buttons

    public void selectAll(View view){
        for (Recipe m : recipeList){
            m.setSelected(true); //select is true, clear is false
            rAdapter.notifyDataSetChanged();
        }
    }

    public void clearAll(View view){
        for (Recipe m : recipeList){
            m.setSelected(false); //select is true, clear is false
            rAdapter.notifyDataSetChanged();
        }
    }

    public void delete(View view){

        int arraySize = recipeList.size();
        for(int i=0; i<arraySize; i++){
            if(recipeList.get(i).isSelected()){
                recipeList.remove(i);
                i -= 1;
                arraySize -= 1;

                rAdapter.notifyDataSetChanged();
            }
        }
    }


}
