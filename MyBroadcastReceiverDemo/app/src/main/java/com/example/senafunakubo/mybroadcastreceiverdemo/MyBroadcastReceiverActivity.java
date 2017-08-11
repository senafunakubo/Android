package com.example.senafunakubo.mybroadcastreceiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by senafunakubo on 2017-08-11.
 */

public class MyBroadcastReceiverActivity extends AppCompatActivity
        implements View.OnClickListener{
    MyBroadcastReceiveDemo MyBroadcastReceiveDemo;
    IntentFilter intentFilter;
    Button broadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyBroadcastReceiveDemo = new MyBroadcastReceiveDemo();
        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        broadcast = (Button)findViewById(R.id.button);
        broadcast.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MyBroadcastReceiveDemo,
                intentFilter);
    }

    @Override
    public void onClick(View v) {
        //it's not related to button's click
        Intent i = new Intent("android.net.conn.CONNECTIVITY_CHANGE");
        sendBroadcast(i);
    }
}