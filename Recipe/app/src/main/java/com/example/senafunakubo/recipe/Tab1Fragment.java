/*

 Rice

*/


package com.example.senafunakubo.recipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

/**
 * Created by senafunakubo on 2017-08-06.
 */

public class Tab1Fragment extends Fragment {

    private ListView mListView;
    ArrayList<Recipe> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        mListView = (ListView)view.findViewById(R.id.listView_search);

        //タコライス,オムライス
        addList();


        Button mini = (Button) view.findViewById(R.id.mini);
        mini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                listPrepare();

                int arraySize = list.size();
                for(int i=0; i<arraySize; i++){

                    if(list.get(i).getCooking_time()>16){
                        list.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        ((CustomListAdapter)mListView.getAdapter()).notifyDataSetChanged();
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

                listPrepare();

                int arraySize = list.size();
                for(int i=0; i<arraySize; i++){
                    if(list.get(i).getCooking_time()<16 || list.get(i).getCooking_time()>31){
                        list.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        ((CustomListAdapter)mListView.getAdapter()).notifyDataSetChanged();
                    }
                }

            }
        });

        Button longTime = (Button) view.findViewById(R.id.longTime);
        longTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                listPrepare();

                int arraySize = list.size();
                for(int i=0; i<arraySize; i++){
                    if(list.get(i).getCooking_time()<=30){
                        list.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        ((CustomListAdapter)mListView.getAdapter()).notifyDataSetChanged();
                    }
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                         Intent intent = new Intent(getActivity(), Recipe_detail.class);
                         Recipe recipe = list.get(position);
                         String foodName = recipe.getRecipe_title();
                         String cookingTime = String.valueOf(recipe.getCooking_time());
                         String foodImgUrl = recipe.getImageUrl();
                         String servings = String.valueOf(recipe.getServings());

                         intent.putExtra("foodName", foodName);
                         intent.putExtra("cookingTime", cookingTime);
                         intent.putExtra("foodImgUrl",foodImgUrl);
                         intent.putExtra("servings", servings);
                         startActivity(intent);
             }
        });

        return view;
    }


    public void addList(){
        list.add(new Recipe("Shrimp Fried Rice", "" ,"","","","","", 20, 4, "http://allrecipes.com/recipe/21561/shrimp-fried-rice-ii/",
                "drawable://" + R.drawable.shrimpfriedrice));
        list.add(new Recipe("Chicken Biryani", "","","","","","", 40, 4, "http://www.food.com/recipe/chicken-biryani-316697",
                "drawable://" + R.drawable.biryani));
        list.add(new Recipe("Clam Rice", "","","","","","", 50, 4, "https://toirokitchen.com/blogs/recipes/47899203-clam-rice",
                "drawable://" + R.drawable.asaririce));
        list.add(new Recipe("Vegetarian Sushi", "","","","","","", 25, 6, "https://www.jvs.org.uk/2012/09/19/vegetarian-sushi/",
                "drawable://" + R.drawable.vegetariansushi1));
        list.add(new Recipe("Inari Sushi", "","","","","","", 20, 1, "https://www.japancentre.com/en/recipes/53-inari-sushi",
                "drawable://" + R.drawable.inari));
        list.add(new Recipe("Rice Ball", "","","","","","", 15, 2,
                "http://www.wandercooks.com/simple-onigiri-recipe/",
                "drawable://" + R.drawable.onigiri));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);
    }

    public void listPrepare(){
        list.clear();
        addList();
        ((CustomListAdapter)mListView.getAdapter()).notifyDataSetChanged();
    }
}
