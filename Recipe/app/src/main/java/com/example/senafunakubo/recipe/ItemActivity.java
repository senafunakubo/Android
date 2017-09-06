package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-09-06.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_recycler);

        mRecyclerView = (RecyclerView) findViewById(R.id.masonry_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        ItemAdapter adapter = new ItemAdapter(this,recipeList);
        mRecyclerView.setAdapter(adapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(mBottomNav);
        Menu menu = mBottomNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.search_ic:
                        Intent intent1 = new Intent(ItemActivity.this, SearchPage.class);
                        startActivity(intent1);
                        break;

                    case R.id.recipe_ic:
                        Toast.makeText(ItemActivity.this, "This is Recipe page!",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.fav_ic:
                        Intent intent2 = new Intent(ItemActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });

        prepareRecipeData();

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this,mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {


                    @Override public void onLongClick(View view, int position) {

                    }

                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(ItemActivity.this, Recipe_detail.class);
                        Recipe recipe = recipeList.get(position);
                        String foodName = recipe.getRecipe_title();
                        String cookingTime = String.valueOf(recipe.getCooking_time());
                        String foodImgUrl = recipe.getImageUrl();

                        intent.putExtra("foodName", foodName);
                        intent.putExtra("cookingTime", cookingTime);
                        intent.putExtra("foodImgUrl",foodImgUrl);
                        startActivity(intent);

                    }

                })
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void prepareRecipeData(){
        Recipe recipe = new Recipe("Shrimp Fried Rice", "" ,"","","","","", 20, "http://allrecipes.com/recipe/21561/shrimp-fried-rice-ii/",
                "drawable://" + R.drawable.shrimpfriedrice);
        recipeList.add(recipe);

        recipe = new Recipe("Fried Udon", "","","","","","", 15, "http://www.bonappetit.com/recipe/stir-fried-udon-with-pork",
                "drawable://" + R.drawable.friedudon);
        recipeList.add(recipe);

        recipe = new Recipe("Clam Rice", "","","","","","", 50, "https://toirokitchen.com/blogs/recipes/47899203-clam-rice",
                "drawable://" + R.drawable.asaririce);
        recipeList.add(recipe);

        recipe = new Recipe("Egg Sandwich", "","","","","","", 10,
                "https://www.pamperedchef.com/recipe/Breakfast%7CBrunch/American/Fried+Egg+Sandwich/985997",
                "drawable://" + R.drawable.eggsand);
        recipeList.add(recipe);

        recipe = new Recipe("Sundubu-jjigae", "", "","","","","",30,
                "https://mykoreankitchen.com/sundubu-jjigae/",
                "drawable://" + R.drawable.chige);
        recipeList.add(recipe);

        recipe = new Recipe("Karbonara", "","","","","","", 25,
                "https://www.coolinarika.com/recept/992495/",
                "drawable://" + R.drawable.karbonara);
        recipeList.add(recipe);

        recipe = new Recipe("Kadai Masala", "", "","","","","",25,
                "http://indianhealthyrecipes.com/kadai-paneer-gravy-recipe/",
                "drawable://" + R.drawable.kadai);
        recipeList.add(recipe);

        recipe = new Recipe("Eggs Benedict", "","","","","","", 30,
                "https://norecipes.com/eggs-benedict-recipe",
                "drawable://" + R.drawable.eggbene);
        recipeList.add(recipe);

        recipe = new Recipe("Curry Udon", "","","","","","", 40,
                "http://www.closetcooking.com/2008/03/kare-udon-curry-udon-soup.html",
                "drawable://" + R.drawable.curryudon);
        recipeList.add(recipe);

        recipe = new Recipe("Chicken Biryani", "","","","","","", 40, "http://www.food.com/recipe/chicken-biryani-316697",
                "drawable://" + R.drawable.biryani);
        recipeList.add(recipe);

        recipe = new Recipe("Japanese Fried Chicken", "","","","","","", 30,
                "http://www.justonecookbook.com/karaage/",
                "drawable://" + R.drawable.karaage);
        recipeList.add(recipe);

        recipe = new Recipe("Vegan Sandwich with Tofu", "","","","","","", 20,
                "http://kblog.lunchboxbunch.com/2016/01/vegan-lunch-sandwich-with-sizzling.html",
                "drawable://" + R.drawable.vegansand);
        recipeList.add(recipe);
    }


}
