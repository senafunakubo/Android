package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senafunakubo on 2017-08-06.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    Recipe recipe;
    private List<Recipe> recipeListLeft = new ArrayList<>();
//    private Recipe_adapter rAdapterLeft;

    private List<Recipe> recipeListRight = new ArrayList<>();
//    private Recipe_adapter rAdapterRight;

    private ListView listViewLeft;
    private ListView listViewRight;
    private Item_adapter leftAdapter;
    private Item_adapter rightAdapter;

    int[] leftViewsHeights;
    int[] rightViewsHeights;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);


        listViewLeft = view.findViewById(R.id.list_view_left1);
        listViewRight = view.findViewById(R.id.list_view_right1);

        loadItems();

        listViewLeft.setOnTouchListener(touchListener);
        listViewRight.setOnTouchListener(touchListener);
        listViewLeft.setOnScrollListener(scrollListener);
        listViewRight.setOnScrollListener(scrollListener);


        prepareRecipeDataLeft();
        prepareRecipeDataRight();


        Button mini = (Button) view.findViewById(R.id.mini);
        mini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int arraySize = recipeListLeft.size();
                for(int i=0; i<arraySize; i++) {
                    String recipeTime = recipeListLeft.get(i).getRecipe_time();
                    Log.d("Left_recipeTime",recipeTime);


                    if(Integer.parseInt(recipeListLeft.get(i).getRecipe_time())>15){
                        recipeListLeft.remove(i);
                        --i;
                        --arraySize;
                        leftAdapter.notifyDataSetChanged();
                        listViewLeft.invalidateViews();
                        Log.d("AfterLeft_RecipeTime",recipeListLeft.get(i).getRecipe_time());
                    }
                }


                int arraySize2 = recipeListRight.size();
                for(int i=0; i<arraySize2; i++) {
                    String recipeTime2 = recipeListRight.get(i).getRecipe_time();
                    Log.d("Right_recipeTime",recipeTime2);


                    if(Integer.parseInt(recipeListRight.get(i).getRecipe_time())>15){
                        recipeListRight.remove(i);
                        --i;
                        --arraySize2;
//                        rightAdapter.notifyDataSetChanged();
                        listViewRight.invalidateViews();
                        Log.d("AfterRight_RecipeTime",recipeListRight.get(i).getRecipe_time());
                    }
                }
            }
        });

        Button mid = (Button) view.findViewById(R.id.mid);
        mid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Mid",
                        Toast.LENGTH_SHORT).show();

            }
        });

        Button longTime = (Button) view.findViewById(R.id.longTime);
        longTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Long",
                        Toast.LENGTH_SHORT).show();
            }
        });

        listViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(), Recipe_detail.class);
                Recipe recipe = recipeListLeft.get(position);
                String recipeUrl = recipe.getWebUrl();
                intent.putExtra("recipeUrl", recipeUrl);
                startActivity(intent);
            }
        });

        listViewRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(), Recipe_detail.class);
                Recipe recipe = recipeListRight.get(position);
                String recipeUrl = recipe.getWebUrl();
                intent.putExtra("recipeUrl", recipeUrl);
                startActivity(intent);
            }
        });

        return view;

    }

    // Passing the touch event to the opposite list
    View.OnTouchListener touchListener = new View.OnTouchListener() {
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
    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

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

        leftAdapter = new Item_adapter(this.getContext(), R.layout.item, leftItems);
        rightAdapter = new Item_adapter(this.getContext(), R.layout.item, rightItems);
        listViewLeft.setAdapter(leftAdapter);
        listViewRight.setAdapter(rightAdapter);

        leftViewsHeights = new int[leftItems.length];
        rightViewsHeights = new int[rightItems.length];
    }

    public void prepareRecipeDataLeft(){
        recipe = new Recipe("Bhindi Masala", "Bhindi(Okura), Tomato...", "10",
                "http://foodviva.com/indian-recipes/bhindi-masala-gravy/",R.drawable.bhindi);
        recipeListLeft.add(recipe);

        recipe = new Recipe("Shrimp Fried Rice", "Shrimp, Rice, Egg...", "20",
                "http://natashaskitchen.com/2011/02/16/shrimp-fried-rice-recip/",R.drawable.shrimpfriedrice);
        recipeListLeft.add(recipe);

        recipe = new Recipe("Soondubu Jjigae", "Tofu, Pork belly, kimchi...", "30",
                "http://www.koreanbapsang.com/2015/01/kimchi-soondubu-jjigae-soft-tofu-stew-kimchi.html",R.drawable.chige);
        recipeListLeft.add(recipe);

    }

    public void prepareRecipeDataRight(){
        recipe = new Recipe("Fried Udon", "Udon, Cabbage, Pork belly...", "15",
                "http://www.bonappetit.com/recipe/stir-fried-udon-with-pork",R.drawable.friedudon);
        recipeListRight.add(recipe);

        recipe = new Recipe("Kadai masala", "Onions, Tomatoes, Cumin seeds...", "20",
                "http://www.spiceupthecurry.com/kadai-vegetable-recipe/",R.drawable.kadai);
        recipeListRight.add(recipe);

        recipe = new Recipe("Fried Egg Sandwich", "Eggs, breads, hams", "10",
                "https://www.pamperedchef.com/recipe/Breakfast%7CBrunch/American/Fried+Egg+Sandwich/985997",R.drawable.eggsand);
        recipeListRight.add(recipe);

        recipe = new Recipe("Karbonara", "Eggs, Pasta, Cheese...", "25",
                "https://www.bbcgoodfood.com/recipes/1052/ultimate-spaghetti-carbonara",R.drawable.karbonara);
        recipeListRight.add(recipe);
    }

}
