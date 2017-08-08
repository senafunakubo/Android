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

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private ListView mListView;
    ArrayList<Card> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        mListView = (ListView)view.findViewById(R.id.listView_search);

        //タコライス,オムライス
        list.add(new Card("drawable://" + R.drawable.shrimpfriedrice, "Shrimp Fried Rice", 20));
        list.add(new Card("drawable://" + R.drawable.biryani, "Chicken Biryani", 40));
        list.add(new Card("drawable://" + R.drawable.asaririce, "Clam Rice", 50));
        list.add(new Card("drawable://" + R.drawable.vegetariansushi, "Vegetarian Sushi", 25));
        list.add(new Card("drawable://" + R.drawable.inari, "Inari Sushi", 20));
        list.add(new Card("drawable://" + R.drawable.onigiri, "Rice Ball", 15));

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

        return view;
    }

    public void addList(){
        list.add(new Card("drawable://" + R.drawable.shrimpfriedrice, "Shrimp Fried Rice", 20));
        list.add(new Card("drawable://" + R.drawable.biryani, "Chicken Biryani", 40));
        list.add(new Card("drawable://" + R.drawable.asaririce, "Clam Rice", 50));
        list.add(new Card("drawable://" + R.drawable.vegetariansushi, "Vegetarian Sushi", 25));
        list.add(new Card("drawable://" + R.drawable.inari, "Inari Sushi", 20));
        list.add(new Card("drawable://" + R.drawable.onigiri, "Rice Ball", 15));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);
    }
}
