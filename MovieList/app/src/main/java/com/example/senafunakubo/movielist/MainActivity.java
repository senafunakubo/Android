package com.example.senafunakubo.movielist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private List<Movie> movieList1 = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private List<ArrayList<Integer>> MoviesChecked;
    // json array response url
    private String urlJsonArray = "http://192.168.57.1/moviedata.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.d("error ", "in onCreate");
        makeJsonArrayRequest();
    }

    /**
     * Method to make json array request where response starts with [
     */
    private void makeJsonArrayRequest() {
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            Movie moviedata = null;
                            for (int i = 0; i < response.length(); i++) {
                                moviedata = new Movie();
                                JSONObject movie = (JSONObject) response.get(i);
                                String title = movie.getString("title");
                                String genre = movie.getString("genre");
                                String year = movie.getString("year");
//                                String cast = movie.getString("cast");
                                moviedata.setTitle(title);
                                moviedata.setGenre(genre);
                                moviedata.setYear(year);
//                                moviedata.setCast(cast);
                                movieList1.add(moviedata);
                            }
                            mAdapter = new MovieAdapter(movieList1);
                            recyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    public void deleteMovie(View view) {
        for (int i = 0; i < movieList1.size(); i++) {
            if (movieList1.get(i).isSelected()) {
                movieList1.remove(i);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public void selectAll(View view) {
        for (Movie m : movieList1) {
            m.setSelected(true);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void clearAll(View view) {
        for (Movie m : movieList1) {
            m.setSelected(false);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}