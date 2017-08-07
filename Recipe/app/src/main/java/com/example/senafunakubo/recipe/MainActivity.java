package com.example.senafunakubo.recipe;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.media.RatingCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//implements GreenAdapter2.ListItemClickListener
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;

    private List<Recipe> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recipe_adapter rAdapter;
    ArrayAdapter<String> searchAdapter;
    Recipe recipe;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For changing background-color of the title bar
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F44336"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);

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

                        switch (position) {
                            case 0:
                                Recipe recipe = new Recipe("Fried Udon", "Udon, Cabbage, Pork belly...", "15 mins","http://www.bonappetit.com/recipe/stir-fried-udon-with-pork", R.drawable.friedudon);
                                recipeList.add(recipe);
                                rAdapter.notifyDataSetChanged();
                                break;

                            default:
                                recipe = new Recipe("Bhindi Masala", "Bhindi(Okura), Tomato...", "10 mins","http://foodviva.com/indian-recipes/bhindi-masala-gravy/",R.drawable.bhindi);
                                recipeList.add(recipe);
                                rAdapter.notifyDataSetChanged();
                                break;
                        }

                    }

                    @Override public void onItemClick(View view, int position) {

                            Intent intent = new Intent(MainActivity.this, Recipe_detail.class);
                            Recipe recipe = recipeList.get(position);
                            String recipeUrl = recipe.getWebUrl();
                            intent.putExtra("recipeUrl", recipeUrl);
                            startActivity(intent);


//                        copyに対応できないので停止
//                        Intent intent;
//                        switch (position){
//                            case 0:
//                                intent = new Intent(MainActivity.this, Recipe_detail.class);
//                                String udon = "http://www.bonappetit.com/recipe/stir-fried-udon-with-pork";
//                                intent.putExtra("udon", udon);
//                                break;
//
//                            case 1:
//                                intent = new Intent(MainActivity.this, Recipe_detail.class);
//                                String masala = "http://www.vegrecipesofindia.com/bhindi-masala/";
//                                intent.putExtra("masala", masala);
//                                break;
//
//                            default:
//                                intent =  new Intent(MainActivity.this, MainActivity.class);
//                                break;
//                        }
//                        startActivity(intent);
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
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item
                switch (item.getItemId()) {

                    case R.id.search_ic:
                        break;

                    case R.id.recipe_ic:
                        Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.fav_ic:
                        break;
                }

                return false;
            }
        });


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter secAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        secAdapter.addFragment(new Tab1Fragment(),"3-15mins");
        secAdapter.addFragment(new Tab2Fragment(),"15-30mins");
        secAdapter.addFragment(new Tab3Fragment(),"30mins+");
        viewPager.setAdapter(secAdapter);
    }




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
        Recipe recipe = new Recipe("Fried Udon", "Udon, Cabbage, Pork belly...", "15 mins","http://www.bonappetit.com/recipe/stir-fried-udon-with-pork", R.drawable.friedudon);
        recipeList.add(recipe);

        recipe = new Recipe("Bhindi Masala", "Bhindi(Okura), Tomato...", "10 mins","http://foodviva.com/indian-recipes/bhindi-masala-gravy/",R.drawable.bhindi);
        recipeList.add(recipe);

        rAdapter.notifyDataSetChanged();
    }

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
            if( recipeList.get(i).isSelected()){
                recipeList.remove(i);
                i -= 1;
                arraySize -= 1;

                rAdapter.notifyDataSetChanged();
            }
        }
    }


}
