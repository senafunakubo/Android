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

public class Tab2Fragment extends Fragment {
    private static final String TAG2 = "Tab2Fragment";

    private ListView mListView2;
    ArrayList<Recipe> list2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        mListView2 = (ListView)view.findViewById(R.id.listView_noodle);

        list2.add(new Recipe("Fried Udon", "","","","","","", 15, "https://www.pickledplum.com/recipe/yaki-udon-butter-recipe/",
                "drawable://" + R.drawable.yakiudon));
        list2.add(new Recipe("Karbonara", "","","","","","", 25, "http://www.stvarukusa.rs/recept/spagete-karbonara",
                "drawable://" + R.drawable.karbonara));
        list2.add(new Recipe("Noodle Soup (VEGETARIAN)", "", "","","","","",15, "http://www.recipetineats.com/dan-dan-noodle-soup-vegetarian/",
                "drawable://" + R.drawable.nooveg));
        list2.add(new Recipe("Veg Hakka Noodles", "", "","","","","",40, "http://vegecravings.com/vegetable-hakka-noodles/",
                "drawable://" + R.drawable.veghakka));
        list2.add(new Recipe("Spaghetti Meat Sauce", "", "","","","","",30, "https://norecipes.com/spaghetti-meat-sauce-recipe",
                "drawable://" + R.drawable.spaghettimeat));
        list2.add(new Recipe("Curry Udon", "", "","","","","",40, "http://www.closetcooking.com/2008/03/kare-udon-curry-udon-soup.html",
                "drawable://" + R.drawable.curryudon));

        final CustomListAdapter adapter2 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list2);
        mListView2.setAdapter(adapter2);


        //たまにダブルクリックしないと動かなくなる
        Button mini2 = (Button) view.findViewById(R.id.mini_noodle);
        mini2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list2.isEmpty()){
                    addList();
                    adapter2.notifyDataSetChanged();
                }

                int arraySize = list2.size();
                for(int i=0; i<arraySize; i++){
                    if(list2.get(i).getCooking_time()>16){
                        list2.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter2.notifyDataSetChanged();
                    }
                }
            }
        });

        Button mid = (Button) view.findViewById(R.id.mid_noodle);
        mid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(list2.isEmpty()){
                    addList();
                    adapter2.notifyDataSetChanged();
                }

                int arraySize = list2.size();
                for(int i=0; i<arraySize; i++){
                    if(list2.get(i).getCooking_time()<16 || list2.get(i).getCooking_time()>31){
                        list2.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter2.notifyDataSetChanged();
                    }
                }

            }
        });

        Button longTime = (Button) view.findViewById(R.id.longTime_noodle);
        longTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list2.isEmpty()){
                    addList();
                    adapter2.notifyDataSetChanged();
                }


                int arraySize = list2.size();
                for(int i=0; i<arraySize; i++){
                    if(list2.get(i).getCooking_time()<=30){
                        list2.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter2.notifyDataSetChanged();
                    }
                }
            }
        });

        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent intent = new Intent(getActivity(), Recipe_detail.class);
                Recipe recipe = list2.get(position);
//                         String recipeUrl = recipe.getWebUrl();
                String foodName = recipe.getRecipe_title();
                String cookingTime = String.valueOf(recipe.getCooking_time());
                String foodImgUrl = recipe.getImageUrl();

//                         intent.putExtra("recipeUrl", recipeUrl);
                intent.putExtra("foodName", foodName);
                intent.putExtra("cookingTime", cookingTime);
                intent.putExtra("foodImgUrl",foodImgUrl);
                startActivity(intent);
            }
        });



        return view;
    }

    public void addList(){
        list2.add(new Recipe("Fried Udon", "","","","","","", 15,
                "https://www.pickledplum.com/recipe/yaki-udon-butter-recipe/",
                "drawable://" + R.drawable.yakiudon));
        list2.add(new Recipe("Karbonara", "","","","","","", 25,
                "https://www.coolinarika.com/recept/992495/",
                "drawable://" + R.drawable.karbonara));
        list2.add(new Recipe("Noodle Soup (VEGETARIAN)", "", "","","","","",15,
                "https://www.vegetariantimes.com/recipes/vegan-chicken-noodle-soup",
                "drawable://" + R.drawable.nooveg));
        list2.add(new Recipe("Veg Hakka Noodles", "","","","","","", 40,
                "http://vegecravings.com/vegetable-hakka-noodles/",
                "drawable://" + R.drawable.veghakka));
        list2.add(new Recipe("Spaghetti Meat Sauce", "","","","","","",30,
                "https://norecipes.com/spaghetti-meat-sauce-recipe",
                "drawable://" + R.drawable.spaghettimeat));
        list2.add(new Recipe("Curry Udon", "","","","","","", 40,
                "http://www.closetcooking.com/2008/03/kare-udon-curry-udon-soup.html",
                "drawable://" + R.drawable.curryudon));

        final CustomListAdapter adapter2 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list2);
        mListView2.setAdapter(adapter2);
    }
}