package com.example.senafunakubo.facebookintegrationdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by senafunakubo on 2017-08-30.
 */

public class UserProfileActivity extends AppCompatActivity {

    private TextView name;
    private Button logoutButton;
    private ImageView img_user;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        name = (TextView)findViewById(R.id.txt_name);
        logoutButton = (Button)findViewById(R.id.logoutButton);
        shareDialog = new ShareDialog(this);

        Bundle bundle = getIntent().getExtras();
        String firstname = bundle.get("name").toString();
        String lastname = bundle.get("surname").toString();
        String imgUrl = bundle.get("imageUri").toString();

        name.setText("" + firstname + "" + lastname);


        new DownloadImage((ImageView)findViewById(R.id.img_profile))
                .execute(imgUrl); // We need execute() cuz it is Async

        logoutButton.setOnClickListener(new View.OnClickListener() {
            //you can also do login and logout
            //using LoginManager.getInstance
            //inflate of callbackManager
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(UserProfileActivity.this, MainActivity1.class);
                startActivity(login);
                finish();
            }
        });

    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {
        ImageView bmImage;

        //constructor
        public DownloadImage(ImageView bmImage){
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            //Retrieving the url for FBprofile image
            String urlDisplay = strings[0];
            Bitmap myImage = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                myImage = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bmImage.setImageBitmap(bitmap);
        }
    }
}
