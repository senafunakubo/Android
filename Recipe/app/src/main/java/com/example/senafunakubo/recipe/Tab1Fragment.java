package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by senafunakubo on 2017-08-06.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private ListView mListView;
    ArrayList<Recipe> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        mListView = (ListView)view.findViewById(R.id.listView_search);

        //タコライス,オムライス
        list.add(new Recipe("Shrimp Fried Rice", "" , 20, "http://allrecipes.com/recipe/21561/shrimp-fried-rice-ii/",
                "drawable://" + R.drawable.shrimpfriedrice));
        list.add(new Recipe("Chicken Biryani", "", 40, "https://www.bbcgoodfood.com/recipes/4686/chicken-biryani",
                "drawable://" + R.drawable.biryani));
        list.add(new Recipe("Clam Rice", "", 50, "https://toirokitchen.com/blogs/recipes/47899203-clam-rice",
                "drawable://" + R.drawable.asaririce));
        list.add(new Recipe("Vegetarian Sushi", "", 25, "http://www.epicurious.com/recipes/food/views/veggie-sushi-rolls-367009",
                "drawable://" + R.drawable.vegetariansushi));
        list.add(new Recipe("Inari Sushi", "", 20, "https://www.japancentre.com/en/recipes/53-inari-sushi",
                "drawable://" + R.drawable.inari));
        list.add(new Recipe("Rice Ball", "", 15,
                "http://j-simplerecipes.com/recipes_metric/rice/rice_balls/karashi-mentaiko-rice-ball.html",
                "drawable://" + R.drawable.onigiri));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);


        //たまにダブルクリックしないと動かなくなる
        Button mini = (Button) view.findViewById(R.id.mini);
        mini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list.isEmpty()){
                    addList();
                    adapter.notifyDataSetChanged();
                }

                int arraySize = list.size();
                for(int i=0; i<arraySize; i++){
                    if(list.get(i).getCooking_time()>16){
                        list.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter.notifyDataSetChanged();
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

                if(list.isEmpty()){
                    addList();
                    adapter.notifyDataSetChanged();
                }

                int arraySize = list.size();
                for(int i=0; i<arraySize; i++){
                    if(list.get(i).getCooking_time()<16 || list.get(i).getCooking_time()>31){
                        list.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter.notifyDataSetChanged();
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
                if(list.isEmpty()){
                    addList();
                    adapter.notifyDataSetChanged();
                }


                int arraySize = list.size();
                for(int i=0; i<arraySize; i++){
                    if(list.get(i).getCooking_time()<=30){
                        list.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                         Intent intent = new Intent(getActivity(), Recipe_detail.class);
                         Recipe card = list.get(position);
                         String recipeUrl = card.getWebUrl();
                         intent.putExtra("recipeUrl", recipeUrl);
                         startActivity(intent);
             }
        });

        return view;
    }

    public void addList(){
        list.add(new Recipe("Shrimp Fried Rice", "" , 20, "http://allrecipes.com/recipe/21561/shrimp-fried-rice-ii/",
                "drawable://" + R.drawable.shrimpfriedrice));
        list.add(new Recipe("Chicken Biryani", "", 40, "https://www.bbcgoodfood.com/recipes/4686/chicken-biryani",
                "drawable://" + R.drawable.biryani));
        list.add(new Recipe("Clam Rice", "", 50, "https://toirokitchen.com/blogs/recipes/47899203-clam-rice",
                "drawable://" + R.drawable.asaririce));
        list.add(new Recipe("Vegetarian Sushi", "", 25, "http://www.epicurious.com/recipes/food/views/veggie-sushi-rolls-367009",
                "drawable://" + R.drawable.vegetariansushi));
        list.add(new Recipe("Inari Sushi", "", 20, "https://www.japancentre.com/en/recipes/53-inari-sushi",
                "drawable://" + R.drawable.inari));
        list.add(new Recipe("Rice Ball", "", 15,
                "http://j-simplerecipes.com/recipes_metric/rice/rice_balls/karashi-mentaiko-rice-ball.html",
                "drawable://" + R.drawable.onigiri));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);
    }
}
