package com.example.senafunakubo.facebookintegrationdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button b1;

     TextView status;
     LoginButton loginButton;
     CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For Facebook Login
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create(); // It always be before setContentView
        setContentView(R.layout.activity_main);

        status = (TextView)findViewById(R.id.txt_status);
        loginButton = (LoginButton)findViewById(R.id.loginButton);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                status.setText("LOGIN SUCCESSFUL\n" + loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                status.setText("LOGIN CANCELED");
            }

            @Override
            public void onError(FacebookException error) {
                status.setText("ERROR" + error.getLocalizedMessage());
            }
        });

        loginButton.setReadPermissions("email");


//        b1 = (Button)findViewById(R.id.btn_share);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                Uri img = Uri.parse("android.resource://com.example.senafunakubo.addressbookapp/*");
//                try {
//                    InputStream is = getContentResolver().openInputStream(img);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                shareIntent.setType("image/jpeg");
//                shareIntent.putExtra(Intent.EXTRA_STREAM, img);
//                startActivity(Intent.createChooser(shareIntent, "SHARING USING"));
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
