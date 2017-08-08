package com.example.senafunakubo.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        list.add(new Card("drawable://" + R.drawable.bhindi, "Bhindi Masala", 10));
        list.add(new Card("drawable://" + R.drawable.friedudon, "Fried Udon", 15));
        list.add(new Card("drawable://" + R.drawable.shrimpfriedrice, "Shrimp Fried Rice", 20));
        list.add(new Card("drawable://" + R.drawable.kadai, "Kadai Masala", 20));
        list.add(new Card("drawable://" + R.drawable.eggsand, "Egg Sandwich", 10));
        list.add(new Card("drawable://" + R.drawable.chige, "Sundubu-jjigae", 30));
        list.add(new Card("drawable://" + R.drawable.karbonara, "Karbonara", 25));

        final CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);



        Button mini = (Button) view.findViewById(R.id.mini);
        mini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
                Toast.makeText(getActivity(), "Mid",
                        Toast.LENGTH_SHORT).show();

            }
        });

        Button longTime = (Button) view.findViewById(R.id.longTime);
        longTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Long",
                        Toast.LENGTH_SHORT).show();
            }
        });





        return view;
    }
}
