package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-07-26.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//import com.example.senafunakubo.recipe.Item_adapter;

public class ItemsActivity extends Activity {

    private List<Recipe> recipeListLeft = new ArrayList<>();
    private Recipe_adapter rAdapterLeft;

    private List<Recipe> recipeListRight = new ArrayList<>();
    private Recipe_adapter rAdapterRight;

    private ListView listViewLeft;
    private ListView listViewRight;
    private Item_adapter leftAdapter;
    private Item_adapter rightAdapter;

    int[] leftViewsHeights;
    int[] rightViewsHeights;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

        rAdapterLeft = new Recipe_adapter(recipeListLeft);
        rAdapterRight = new Recipe_adapter(recipeListRight);

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
                        Intent intent1 = new Intent(ItemsActivity.this, SearchPage.class);
                        startActivity(intent1);
                        break;

                    case R.id.recipe_ic:
                        Toast.makeText(ItemsActivity.this, "This is Recipe page!",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.fav_ic:
                        Intent intent2 = new Intent(ItemsActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });



        listViewLeft = findViewById(R.id.list_view_left);
        listViewRight = findViewById(R.id.list_view_right);

        loadItems();

        listViewLeft.setOnTouchListener(touchListener);
        listViewRight.setOnTouchListener(touchListener);
        listViewLeft.setOnScrollListener(scrollListener);
        listViewRight.setOnScrollListener(scrollListener);


        prepareRecipeDataLeft();
        prepareRecipeDataRight();

        listViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ItemsActivity.this, Recipe_detail.class);
                Recipe recipe = recipeListLeft.get(position);
                String recipeUrl = recipe.getWebUrl();
                intent.putExtra("recipeUrl", recipeUrl);
                startActivity(intent);
            }
        });

        listViewRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ItemsActivity.this, Recipe_detail.class);
                Recipe recipe = recipeListRight.get(position);
                String recipeUrl = recipe.getWebUrl();
                intent.putExtra("recipeUrl", recipeUrl);
                startActivity(intent);
            }
        });


    }

    // Passing the touch event to the opposite list
    OnTouchListener touchListener = new OnTouchListener() {
        boolean dispatched = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.equals(listViewLeft) && !dispatched) {
                dispatched = true;
                listViewRight.dispatchTouchEvent(event);
            } else if (v.equals(listViewRight) && !dispatched) {
                dispatched = true;
                listViewLeft.dispatchTouchEvent(event);
            }

            dispatched = false;
            return false;
        }
    };

    /**
     * Synchronizing scrolling
     * Distance from the top of the first visible element opposite list:
     * sum_heights(opposite invisible screens) - sum_heights(invisible screens) + distance from top of the first visible child
     */
    OnScrollListener scrollListener = new OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView v, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

            if (view.getChildAt(0) != null) {
                if (view.equals(listViewLeft) ){
                    leftViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();

                    int h = 0;
                    for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
                        h += rightViewsHeights[i];
                    }

                    int hi = 0;
                    for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
                        hi += leftViewsHeights[i];
                    }

                    int top = h - hi + view.getChildAt(0).getTop();
                    listViewRight.setSelectionFromTop(listViewRight.getFirstVisiblePosition(), top);
                } else if (view.equals(listViewRight)) {
                    rightViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();

                    int h = 0;
                    for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
                        h += leftViewsHeights[i];
                    }

                    int hi = 0;
                    for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
                        hi += rightViewsHeights[i];
                    }

                    int top = h - hi + view.getChildAt(0).getTop();
                    listViewLeft.setSelectionFromTop(listViewLeft.getFirstVisiblePosition(), top);
                }

            }

        }
    };

    private void loadItems(){
        Integer[] leftItems = new Integer[]{R.drawable.bhindi,R.drawable.shrimpfriedrice,R.drawable.chige};
        Integer[] rightItems = new Integer[]{R.drawable.friedudon,R.drawable.kadai,R.drawable.eggsand,R.drawable.karbonara};

        leftAdapter = new Item_adapter(this, R.layout.item, leftItems);
        rightAdapter = new Item_adapter(this, R.layout.item, rightItems);
        listViewLeft.setAdapter(leftAdapter);
        listViewRight.setAdapter(rightAdapter);

        leftViewsHeights = new int[leftItems.length];
        rightViewsHeights = new int[rightItems.length];
    }

    public void prepareRecipeDataLeft(){
        Recipe recipe = new Recipe("Bhindi Masala", "Bhindi(Okura), Tomato...", "10 mins",
                "http://foodviva.com/indian-recipes/bhindi-masala-gravy/",R.drawable.bhindi);
        recipeListLeft.add(recipe);

        recipe = new Recipe("Shrimp Fried Rice", "Shrimp, Rice, Egg...", "20 mins",
                "http://natashaskitchen.com/2011/02/16/shrimp-fried-rice-recip/",R.drawable.shrimpfriedrice);
        recipeListLeft.add(recipe);

        recipe = new Recipe("Soondubu Jjigae", "Tofu, Pork belly, kimchi...", "30 mins",
                "http://www.koreanbapsang.com/2015/01/kimchi-soondubu-jjigae-soft-tofu-stew-kimchi.html",R.drawable.chige);
        recipeListLeft.add(recipe);

//        rAdapterLeft.notifyDataSetChanged();
    }

    public void prepareRecipeDataRight(){
        Recipe recipe = new Recipe("Fried Udon", "Udon, Cabbage, Pork belly...", "15 mins",
                "http://www.bonappetit.com/recipe/stir-fried-udon-with-pork",R.drawable.friedudon);
        recipeListRight.add(recipe);

        recipe = new Recipe("Kadai masala", "Onions, Tomatoes, Cumin seeds...", "20 mins",
                "http://www.spiceupthecurry.com/kadai-vegetable-recipe/",R.drawable.kadai);
        recipeListRight.add(recipe);

        recipe = new Recipe("Fried Egg Sandwich", "Eggs, breads, hams", "10 mins",
                "https://www.pamperedchef.com/recipe/Breakfast%7CBrunch/American/Fried+Egg+Sandwich/985997",R.drawable.eggsand);
        recipeListRight.add(recipe);

        recipe = new Recipe("Karbonara", "Eggs, Pasta, Cheese...", "25 mins",
                "https://www.bbcgoodfood.com/recipes/1052/ultimate-spaghetti-carbonara",R.drawable.karbonara);
        recipeListRight.add(recipe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}
