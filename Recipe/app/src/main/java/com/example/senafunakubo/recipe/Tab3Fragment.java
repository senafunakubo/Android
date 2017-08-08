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

public class Tab3Fragment extends Fragment {
    private static final String TAG3 = "Tab3Fragment";

    private ListView mListView3;
    ArrayList<Card> list3 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view3 = inflater.inflate(R.layout.tab3_fragment, container, false);
        mListView3 = (ListView)view3.findViewById(R.id.listView_bread);

        list3.add(new Card("drawable://" + R.drawable.eggsand, "Egg Sandwich", 10, "https://www.pamperedchef.com/recipe/Breakfast%7CBrunch/American/Fried+Egg+Sandwich/985997"));
        list3.add(new Card("drawable://" + R.drawable.breadchana, "Bread Channa", 15, "https://www.ticklingpalates.com/bread-channa-recipe-healthy-snacks-recipes/"));
        list3.add(new Card("drawable://" + R.drawable.tacopi, "Taco Pizza", 45, "http://www.food.com/recipe/taco-pizza-174145"));
        list3.add(new Card("drawable://" + R.drawable.frencht, "French Toast", 30, "http://www.foodnetwork.com/recipes/robert-irvine/french-toast-recipe-1951408"));
        list3.add(new Card("drawable://" + R.drawable.vegansand, "Vegan Sandwich with Tofu", 20, "http://kblog.lunchboxbunch.com/2016/01/vegan-lunch-sandwich-with-sizzling.html"));
        list3.add(new Card("drawable://" + R.drawable.eggbene, "Eggs Benedict", 30, "https://norecipes.com/eggs-benedict-recipe"));

        final CustomListAdapter adapter3 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list3);
        mListView3.setAdapter(adapter3);


        //たまにダブルクリックしないと動かなくなる
        Button mini = (Button) view3.findViewById(R.id.mini_bread);
        mini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list3.isEmpty()){
                    addList();
                    adapter3.notifyDataSetChanged();
                }

                int arraySize = list3.size();
                for(int i=0; i<arraySize; i++){
                    if(list3.get(i).getCooking_time()>16){
                        list3.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter3.notifyDataSetChanged();
                    }
                }
            }
        });

        Button mid = (Button) view3.findViewById(R.id.mid_bread);
        mid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(list3.isEmpty()){
                    addList();
                    adapter3.notifyDataSetChanged();
                }

                int arraySize = list3.size();
                for(int i=0; i<arraySize; i++){
                    if(list3.get(i).getCooking_time()<16 || list3.get(i).getCooking_time()>31){
                        list3.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter3.notifyDataSetChanged();
                    }
                }

            }
        });

        Button longTime = (Button) view3.findViewById(R.id.longTime_bread);
        longTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list3.isEmpty()){
                    addList();
                    adapter3.notifyDataSetChanged();
                }


                int arraySize = list3.size();
                for(int i=0; i<arraySize; i++){
                    if(list3.get(i).getCooking_time()<=30){
                        list3.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter3.notifyDataSetChanged();
                    }
                }
            }
        });

        mListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent intent = new Intent(getActivity(), Recipe_detail.class);
                Card card = list3.get(position);
                String recipeUrl = card.getWebUrl();
                intent.putExtra("recipeUrl", recipeUrl);
                startActivity(intent);
            }
        });

        return view3;
    }

    public void addList(){
        list3.add(new Card("drawable://" + R.drawable.eggsand, "Egg Sandwich", 10, "https://www.pamperedchef.com/recipe/Breakfast%7CBrunch/American/Fried+Egg+Sandwich/985997"));
        list3.add(new Card("drawable://" + R.drawable.breadchana, "Bread Channa", 15, "https://www.ticklingpalates.com/bread-channa-recipe-healthy-snacks-recipes/"));
        list3.add(new Card("drawable://" + R.drawable.tacopi, "Taco Pizza", 45, "http://www.food.com/recipe/taco-pizza-174145"));
        list3.add(new Card("drawable://" + R.drawable.frencht, "French Toast", 30, "http://www.foodnetwork.com/recipes/robert-irvine/french-toast-recipe-1951408"));
        list3.add(new Card("drawable://" + R.drawable.vegansand, "Vegan Sandwich with Tofu", 20, "http://kblog.lunchboxbunch.com/2016/01/vegan-lunch-sandwich-with-sizzling.html"));
        list3.add(new Card("drawable://" + R.drawable.eggbene, "Eggs Benedict", 30, "https://norecipes.com/eggs-benedict-recipe"));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list3);
        mListView3.setAdapter(adapter);
    }
}