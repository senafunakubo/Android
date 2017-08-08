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
 * Created by senafunakubo on 2017-08-07.
 */

public class Tab4Fragment extends Fragment {
    private static final String TAG4 = "Tab4Fragment";

    private ListView mListView4;
    ArrayList<Card> list4 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.tab4_fragment, container, false);
        mListView4 = (ListView)view4.findViewById(R.id.listView_others);

        list4.add(new Card("drawable://" + R.drawable.bhindi, "Bhindi Masala", 10));
        list4.add(new Card("drawable://" + R.drawable.chige, "Sundubu-jjigae", 30));
        list4.add(new Card("drawable://" + R.drawable.kadai, "Kadai Masala", 25));
        list4.add(new Card("drawable://" + R.drawable.gingerpork, "Ginger Pork", 20));
        list4.add(new Card("drawable://" + R.drawable.gyozave, "Vegan Gyoza", 50));
        list4.add(new Card("drawable://" + R.drawable.karaage, "Japanese Fried Chicken", 30));

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

        return view4;
    }

    public void addList(){
        list4.add(new Card("drawable://" + R.drawable.bhindi, "Bhindi Masala", 10));
        list4.add(new Card("drawable://" + R.drawable.chige, "Sundubu-jjigae", 30));
        list4.add(new Card("drawable://" + R.drawable.kadai, "Kadai Masala", 25));
        list4.add(new Card("drawable://" + R.drawable.gingerpork, "Ginger Pork", 20));
        list4.add(new Card("drawable://" + R.drawable.gyozave, "Vegan Gyoza", 50));
        list4.add(new Card("drawable://" + R.drawable.karaage, "Japanese Fried Chicken", 30));

        final CustomListAdapter adapter4 = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list4);
        mListView4.setAdapter(adapter4);
    }
}