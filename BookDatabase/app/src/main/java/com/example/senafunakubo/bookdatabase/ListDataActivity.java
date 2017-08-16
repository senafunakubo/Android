package com.example.senafunakubo.bookdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by senafunakubo on 2017-08-15.
 */

public class ListDataActivity extends AppCompatActivity{

    private static final String TAG = "ListDataActivity";

    DatabaseHandler mDatabaseHandler;

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.listView);
        mDatabaseHandler = new DatabaseHandler(this);

        populateListView();

    }

    private void populateListView() {
        Cursor data = mDatabaseHandler.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(0));
            listData.add(data.getString(1));
            listData.add(data.getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);

    }

}
