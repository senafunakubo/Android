package com.example.senafunakubo.notification;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by senafunakubo on 2017-08-25.
 */

public class HandleIntent extends IntentService{

    public HandleIntent() {
        super("HandleIntent");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction(); // to get drink or dismiss
        HandleAction.executeTask(this, action);
    }
}
