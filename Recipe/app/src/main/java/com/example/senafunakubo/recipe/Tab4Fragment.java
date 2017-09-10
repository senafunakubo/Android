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
 * Created by senafunakubo on 2017-08-07.
 */

public class Tab4Fragment extends Fragment {
    private static final String TAG4 = "Tab4Fragment";

    private ListView mListView4;
    ArrayList<Recipe> list4 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.tab4_fragment, container, false);
        mListView4 = (ListView)view4.findViewById(R.id.listView_others);

        list4.add(new Recipe("Bhindi Masala", "","","","","","", 17,
                "http://kalimirchbysmita.com/bhindi-masala-in-microwave/",
                "drawable://" + R.drawable.bhindi));
        list4.add(new Recipe("Sundubu-jjigae", "","","","","","", 30, "http://www.koreanbapsang.com/2015/01/kimchi-soondubu-jjigae-soft-tofu-stew-kimchi.html",
                "drawable://" + R.drawable.chige1));
        list4.add(new Recipe("Kadai Masala", "", "","","","","",25, "http://www.mareenasrecipecollections.com/maharashtrian-style-chicken-recipe/",
                "drawable://" + R.drawable.kadai));
        list4.add(new Recipe("Ginger Pork", "", "","","","","",20, "https://norecipes.com/buta-no-shogayaki-ginger-pork",
                "drawable://" + R.drawable.gingerpork));
        list4.add(new Recipe("Vegan Gyoza", "", "","","","","",50, "http://www.myrecipes.com/recipe/vegetarian-gyoza-with-spicy-dipping-sauce",
                "drawable://" + R.drawable.gyozave));
        list4.add(new Recipe("Japanese Fried Chicken", "", "","","","","", 30, "http://www.justonecookbook.com/karaage/",
                "drawable://" + R.drawable.karaage));

        final CustomListAdapter adapter4 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list4);
        mListView4.setAdapter(adapter4);


        //たまにダブルクリックしないと動かなくなる
        Button mini = (Button) view4.findViewById(R.id.mini_others);
        mini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list4.isEmpty()){
                    addList();
                    adapter4.notifyDataSetChanged();
                }

                int arraySize = list4.size();
                for(int i=0; i<arraySize; i++){
                    if(list4.get(i).getCooking_time()>16){
                        list4.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter4.notifyDataSetChanged();
                    }
                }
            }
        });

        Button mid = (Button) view4.findViewById(R.id.mid_others);
        mid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(list4.isEmpty()){
                    addList();
                    adapter4.notifyDataSetChanged();
                }

                int arraySize = list4.size();
                for(int i=0; i<arraySize; i++){
                    if(list4.get(i).getCooking_time()<16 || list4.get(i).getCooking_time()>31){
                        list4.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter4.notifyDataSetChanged();
                    }
                }

            }
        });

        Button longTime = (Button) view4.findViewById(R.id.longTime_others);
        longTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list4.isEmpty()){
                    addList();
                    adapter4.notifyDataSetChanged();
                }


                int arraySize = list4.size();
                for(int i=0; i<arraySize; i++){
                    if(list4.get(i).getCooking_time()<=30){
                        list4.remove(i);
                        i -= 1;
                        arraySize -= 1;
                        adapter4.notifyDataSetChanged();
                    }
                }
            }
        });

        mListView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent intent = new Intent(getActivity(), Recipe_detail.class);
                Recipe recipe = list4.get(position);
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

        return view4;
    }

    public void addList(){
        list4.add(new Recipe("Bhindi Masala", "", "","","","","",17,
                "http://kalimirchbysmita.com/bhindi-masala-in-microwave/",
                "drawable://" + R.drawable.bhindi));
        list4.add(new Recipe("Sundubu-jjigae", "", "","","","","",30,
                "http://www.koreanbapsang.com/2015/01/kimchi-soondubu-jjigae-soft-tofu-stew-kimchi.html",
                "drawable://" + R.drawable.chige1));
        list4.add(new Recipe("Kadai Masala", "", "","","","","",25,
                "http://www.mareenasrecipecollections.com/maharashtrian-style-chicken-recipe/",
                "drawable://" + R.drawable.kadai));
        list4.add(new Recipe("Ginger Pork", "","","","","","", 20,
                "https://norecipes.com/buta-no-shogayaki-ginger-pork",
                "drawable://" + R.drawable.gingerpork));
        list4.add(new Recipe("Vegan Gyoza", "", "","","","","",50,
                "http://www.myrecipes.com/recipe/vegetarian-gyoza-with-spicy-dipping-sauce",
                "drawable://" + R.drawable.gyozave));
        list4.add(new Recipe("Japanese Fried Chicken", "","","","","","", 30,
                "http://www.justonecookbook.com/karaage/",
                "drawable://" + R.drawable.karaage));

        final CustomListAdapter adapter4 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list4);
        mListView4.setAdapter(adapter4);
    }
}