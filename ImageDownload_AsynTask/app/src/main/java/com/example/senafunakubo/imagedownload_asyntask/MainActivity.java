package com.example.senafunakubo.imagedownload_asyntask;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText selectiontext;
    ListView chooseImageList;
    String[] listofImages;
    ProgressBar downloadImageProgress;
    TextView textView;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectiontext = (EditText) findViewById(R.id.urlselectionText);
        chooseImageList = (ListView) findViewById(R.id.chooseImageList);
        downloadImageProgress = (ProgressBar) findViewById(R.id.downloadProgress);
        listofImages = getResources().getStringArray(R.array.imageUrls);
        textView = (TextView)findViewById(R.id.textViewProgress);
        chooseImageList.setOnItemClickListener(this);
    }

    public void downloadImage(View view) {
        if (selectiontext.getText().toString() != null &&
                selectiontext.getText().toString().length() > 0) {
            // create instance of subClass (MyTask).
            // call method execute() on it and it accepts text read from textview as parameter.
            MyTask1 myTask1 = new MyTask1();
            myTask1.execute(selectiontext.getText().toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectiontext.setText(listofImages[position]);
    }

    class MyTask1 extends AsyncTask<String,Integer,Boolean> {

        private int contentLength = -1;
        private int counter = 0;
        private int count = 0;

        @Override
        protected void onPreExecute() {
            //set visibility of progressbar to visible
            downloadImageProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            // Create an instance of URL, HttpURLConnection, InputStream, FileOutputStream,File class
            // Create a boolean variable successful and set its initial value to false
            // if image download successfully set it to true. return a boolean value of success
            // Write a code that download the image from internet
            // count how many bytes are downloaded for that image and use this count to show the progress

            boolean successful = false;
            URL downloadURL = null;
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            File file = null;

            try {
                downloadURL = new URL(params[0]);
                connection = (HttpURLConnection) downloadURL.openConnection();
                contentLength = connection.getContentLength();
                inputStream = connection.getInputStream();
                verifyStoragePermissions(MainActivity.this);

                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + Uri.parse(params[0]).getLastPathSegment());
                fileOutputStream = new FileOutputStream(file);

                int read = -1;
                byte[] buffer = new byte[1024];
                while ((read = inputStream.read(buffer))!= -1){
                    fileOutputStream.write(buffer,0,read);
                    counter = counter + read;
//                    L.m("counter" + counter + "length" + contentLength);
                    publishProgress(counter);
                }
                successful = true;

            }catch (MalformedURLException e){
                L.m(e + "");

            }catch (IOException e){
                L.m(e + "");

            }finally {
                if(connection != null){
                    connection.disconnect();
                }
                if(inputStream != null){
                    try {
                        inputStream.close();
                    }catch (IOException e){

                        L.m(e + "");
                    }
                }
                if(fileOutputStream != null){
                     try {
                         fileOutputStream.close();
                     }catch (IOException e){

                         L.m(e + "");
                     }
                }
            }

            return successful;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //calculate the progress and show it on progressbar
//            count++;
            int calculatedProgress = (int)(((double) values[0]/contentLength) * 100);
            downloadImageProgress.setProgress(calculatedProgress);
            textView.setText("UPDATING..." + calculatedProgress + " % ");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //set visibility of progressbar to gone
            downloadImageProgress.setVisibility(View.GONE);
        }
    }



}
