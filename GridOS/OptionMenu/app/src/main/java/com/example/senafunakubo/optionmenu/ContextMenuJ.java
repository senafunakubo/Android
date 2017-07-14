package com.example.senafunakubo.optionmenu;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by senafunakubo on 2017-07-13.
 */


public class ContextMenuJ extends AppCompatActivity {
    TextView tv;
    private ListView lv_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_country = (ListView) findViewById(R.id.lv_country);

//        tv = (TextView)findViewById(R.id.tv_context);
//        registerForContextMenu(tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.feeds:
                feeds();
                return true;

            case R.id.friends:
                friends();
                return true;

            case R.id.about:
                about();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_with_icons,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.menu_bookmark)
            if (item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
//            Toast.makeText(this, "bookmark is selected",Toast.LENGTH_SHORT).show();
        else if(id==R.id.menu_delete)
            if (item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
//            Toast.makeText(this,"delete is selected",Toast.LENGTH_SHORT).show();
        else if(id==R.id.menu_preferences)
            if (item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
//            Toast.makeText(this,"preferences is selected",Toast.LENGTH_SHORT).show();
        else if(id==R.id.menu_save)
            if (item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
//            Toast.makeText(this,"save is selected",Toast.LENGTH_SHORT).show();
        else if(id==R.id.menu_search)
            if (item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
//            Toast.makeText(this,"search is selected",Toast.LENGTH_SHORT).show();
        else

        if (item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);
//            Toast.makeText(this,"share is selected",Toast.LENGTH_SHORT).show();
        return true;

    }

    @Override
    protected void onResume() {
        String[] countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        lv_country.setAdapter(adapter);
        registerForContextMenu(lv_country);
        super.onResume();
    }

    public void showPopUpMenu(View v){
        //Creating the instance of PopupMenu
        PopupMenu m= new PopupMenu(this, v);
        //Inflating the Popup using xml file
        MenuInflater i = m.getMenuInflater();
        i.inflate(R.menu.menu_with_icons,m.getMenu());
        // This activity implements OnMenuItemClickListener
        m.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_bookmark:
                        // Single menu item is selected do something
                        // Ex: launching new activity/screen or show alert message

                        Toast.makeText(ContextMenuJ.this, "Bookmark" + " is Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menu_save:
                        Toast.makeText(ContextMenuJ.this, "Save is Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menu_search:
                        Toast.makeText(ContextMenuJ.this, "Search is Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menu_share:
                        Toast.makeText(ContextMenuJ.this, "Share is Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menu_delete:
                        Toast.makeText(ContextMenuJ.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menu_preferences:
                        Toast.makeText(ContextMenuJ.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        return false;
                }
            }
        });



        m.show();//showing popup menu
    }


    public void feeds(){

        Toast.makeText(getApplicationContext(),"feeds is selected", Toast.LENGTH_SHORT).show();
    }

    public void friends(){

        Toast.makeText(getApplicationContext(),"friends is selected", Toast.LENGTH_SHORT).show();
    }

    public void about(){

        Toast.makeText(getApplicationContext(),"about is selected", Toast.LENGTH_SHORT).show();
    }


}