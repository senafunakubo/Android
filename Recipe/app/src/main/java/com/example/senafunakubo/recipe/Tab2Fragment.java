package com.example.senafunakubo.recipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by senafunakubo on 2017-08-06.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG2 = "Tab2Fragment";

    private ListView mListView2;
    ArrayList<Card> list2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        mListView2 = (ListView)view.findViewById(R.id.listView_noodle);

        list2.add(new Card("drawable://" + R.drawable.friedudon, "Fried Udon", 15));
        list2.add(new Card("drawable://" + R.drawable.karbonara, "Karbonara", 25));
        list2.add(new Card("drawable://" + R.drawable.nooveg, "Noodle Soup (VEGETARIAN)", 15));
        list2.add(new Card("drawable://" + R.drawable.veghakka, "Veg Hakka Noodles", 35));
        list2.add(new Card("drawable://" + R.drawable.spaghettimeat, "Spaghetti Meat Sauce", 30));
        list2.add(new Card("drawable://" + R.drawable.curryudon, "Curry Udon", 40));


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





        return view;
    }

    public void addList(){

        list2.add(new Card("drawable://" + R.drawable.friedudon, "Fried Udon", 15));
        list2.add(new Card("drawable://" + R.drawable.karbonara, "Karbonara", 25));
        list2.add(new Card("drawable://" + R.drawable.nooveg, "Noodle Soup (VEGETARIAN)", 15));
        list2.add(new Card("drawable://" + R.drawable.veghakka, "Veg Hakka Noodles", 35));
        list2.add(new Card("drawable://" + R.drawable.spaghettimeat, "Spaghetti Meat Sauce", 30));
        list2.add(new Card("drawable://" + R.drawable.curryudon, "Curry Udon", 40));

        final CustomListAdapter adapter2 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list2);
        mListView2.setAdapter(adapter2);
    }
}