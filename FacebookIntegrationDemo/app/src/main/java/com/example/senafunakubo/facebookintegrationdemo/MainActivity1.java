package com.example.senafunakubo.facebookintegrationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by senafunakubo on 2017-08-29.
 */

public class MainActivity1 extends AppCompatActivity {
    CallbackManager callbackManager;
    public ProfileTracker profileTracker;
    public AccessTokenTracker accessTokenTracker;
    LoginButton loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main1);

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                nextActivity(currentProfile);
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        //.start tracking the data
        profileTracker.startTracking();
        accessTokenTracker.startTracking();
        loginButton = (LoginButton)findViewById(R.id.btn_login1);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(MainActivity1.this, "Logging in", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Log.d("cancel", "cancel!");
            }

            @Override
            public void onError(FacebookException error) {

                Log.d("error", "error!");
            }
        });
        loginButton.setReadPermissions("user_friends");
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);

//        callbackManager = CallbackManager.Factory.create();
//        setContentView(R.layout.activity_main1);
//
//        profileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                 nextActivity(currentProfile);
//            }
//        };
//
//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//
//            }
//        };
//        //.start tracking the data
//        profileTracker.startTracking();
//        accessTokenTracker.startTracking();
//        loginButton = (LoginButton)findViewById(R.id.btn_login1);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Profile profile = Profile.getCurrentProfile();
//                nextActivity(profile);
//                Toast.makeText(MainActivity1.this, "Logging in", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//        loginButton.setReadPermissions("user_friends");
//    }
//
    private void nextActivity(Profile profile){
        if (profile!= null){
            Intent main = new Intent(MainActivity1.this, UserProfileActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUri",profile.getProfilePictureUri(200,200));
            startActivity(main);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onStop() {
        super.onStop();
        profileTracker.stopTracking();
        accessTokenTracker.stopTracking();
    }

    //これないと動かないよ！！！！！
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
