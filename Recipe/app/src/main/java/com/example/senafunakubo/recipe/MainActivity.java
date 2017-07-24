package com.example.senafunakubo.recipe;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


//implements GreenAdapter2.ListItemClickListener
public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recipe_adapter rAdapter;
    private int[] selectedRecipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For changing background-color of the title bar
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F44336"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        rAdapter = new Recipe_adapter(recipeList);

        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(rLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        prepareRecipeData();

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
        Recipe recipe = new Recipe("Fried Udon", "Udon, Cabbage, Pork belly...", "15 mins",R.drawable.friedudon);
        recipeList.add(recipe);

        recipe = new Recipe("Bhindi Masala", "Bhindi(Okura), Tomato...", "10 mins",R.drawable.bhindi);
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
