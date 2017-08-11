package com.example.senafunakubo.mybroadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by senafunakubo on 2017-08-11.
 */


public class MyBroadcastReceiveDemo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("com.example.mybroadcastreceiverdemo.SOME_ACTION"))
            Toast.makeText(context,"SOME_ACTION is received",Toast.LENGTH_LONG)
                    .show();
        else
        {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork =
                    cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork!= null
                    && activeNetwork.isConnectedOrConnecting();
            if(isConnected)
            {
                Toast.makeText(context,"NETWORK IS CONNECTED",
                        Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(context,"NETWORK IS CHANGED or DISCONNCTED",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}