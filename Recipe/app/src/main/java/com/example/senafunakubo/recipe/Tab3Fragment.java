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

public class Tab3Fragment extends Fragment {
    private static final String TAG3 = "Tab3Fragment";

    private ListView mListView3;
    ArrayList<Card> list3 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view3 = inflater.inflate(R.layout.tab3_fragment, container, false);
        mListView3 = (ListView)view3.findViewById(R.id.listView_bread);

        list3.add(new Card("drawable://" + R.drawable.eggsand, "Egg Sandwich", 10));
        list3.add(new Card("drawable://" + R.drawable.breadchana, "Bread Channa", 15));
        list3.add(new Card("drawable://" + R.drawable.tacopi, "Taco Pizza", 45));
        list3.add(new Card("drawable://" + R.drawable.frencht, "French Toast", 30));
        list3.add(new Card("drawable://" + R.drawable.vegansand, "Vegan Sandwich with Tofu", 20));
        list3.add(new Card("drawable://" + R.drawable.eggbene, "Eggs Benedict", 30));

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

        return view3;
    }

    public void addList(){
        list3.add(new Card("drawable://" + R.drawable.eggsand, "Egg Sandwich", 10));
        list3.add(new Card("drawable://" + R.drawable.breadchana, "Bread Channa", 15));
        list3.add(new Card("drawable://" + R.drawable.tacopi, "Taco Pizza", 45));
        list3.add(new Card("drawable://" + R.drawable.frencht, "French Toast", 30));
        list3.add(new Card("drawable://" + R.drawable.vegansand, "Vegan Sandwich with Tofu", 20));
        list3.add(new Card("drawable://" + R.drawable.eggbene, "Eggs Benedict", 30));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list3);
        mListView3.setAdapter(adapter);
    }
}