package com.example.senafunakubo.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.app.Notification;

/**
 * Created by senafunakubo on 2017-08-25.
 */

public class NotificationUtil {

    //Notification ID used to access our notification
    //after we displayed it. This can be handy when we need to cancel the notification or update
    //number is arbitrary and can be set to anything.

    private static final int REMINDER_NOTIFICATION_ID = 1138;
    private static final int REMINDER_PENDING_INTENT_ID = 3417;

    private static final int ACTION_DRINK_PENDING_INTENT_ID =1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID =1;

    //helper methods called contentIntent with a single parameter context.
    //It should return a pending intent. This method will create a pending
    //intent which trigger when notification is pressed. This pending intent should
    //open up the mainActivity

    private static PendingIntent contentIntent(Context context){
        //create an intent that opens MainActivity
        Intent startActivityIntent = new Intent(context, MainActivity.class);

        //Create a pending intent using getActivity that will:
        //take the context passed as parameter
        //takes a ID for pending intent
        //takes intent created to open MainActivity when the notification is triggered
        //has the flag FLAG_UPDATE_CURRENT, so that if intent is created again keep the intent
        //but update the data
        return PendingIntent.getActivity(
                context,
                REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    //Create a helper method called largeIcon which takes in a Context as a parameter and
    //returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
    private  static Bitmap largeIcon(Context context){
        //Get a Resources object from the context.
        Resources res = context.getResources();
        //Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        //resources object and R.drawable~
        Bitmap largeIcon = BitmapFactory.decodeResource(res,R.mipmap.ic_drink);
        return largeIcon;
    }

    //lets start building the notification
    public static void reminderUser(Context context){
        //Notification Compact.Builder to create it has color of R.colorPrimary
        //has small icon has title(a string resource) has content (summary again from string resource)
        //has style e.g. bigText() , template
        //has defaults i.e. vibrate
        //uses the contentIntent
        //automatically cancel the notification when user click it
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_action_name)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.title))
                .setContentText(context.getString(R.string.content))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.content)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(drinkWaterAction(context))
                .addAction(ignoreReminderAction(context)) // we can put this up to 3
                .setAutoCancel(true);

        //if build version is greater than JELLYBEAN than set priority
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        //get NotificationManager, using context.getSystemService(Context.NOTIFICATION_SERVICE)
        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Trigger the notification by calling notify on the NotificationManager
        //pass the ID value and build()
        notificationManager.notify(REMINDER_NOTIFICATION_ID,notificationBuilder.build());

    }

    //add a static method to drink
    private static NotificationCompat.Action drinkWaterAction(Context context){ //notificationCompatを候補から選ぶ

        //create a intent to launch the reminder
        Intent drinkWaterIntent = new Intent(context, HandleAction.class);
        //set action of intent
        drinkWaterIntent.setAction(HandleAction.ACTION_DRINK_WATER);
        //create a pending intent to launch the intentService
        PendingIntent drinkWaterPI = PendingIntent.getService( //background service
                context,
                ACTION_DRINK_PENDING_INTENT_ID,
                drinkWaterIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );
        //create an action for user to notify
        NotificationCompat.Action drinkWaterAction = new NotificationCompat.Action(R.mipmap.ic_drink,"Drink it",
                drinkWaterPI);
        //return the action
        return drinkWaterAction;

    }

    private static NotificationCompat.Action ignoreReminderAction(Context context){
        //create a intent to launch the reminder
        Intent ignoreWaterIntent = new Intent(context, HandleAction.class);
        //set action of intent
        ignoreWaterIntent.setAction(HandleAction.ACTION_IGNORE);
        //create a pending intent to launch the intentService
        PendingIntent ignoreWaterPI = PendingIntent.getService( //background service
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreWaterIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );
        //create an action for user to notify
        NotificationCompat.Action ignoreWaterAction = new NotificationCompat.Action(R.mipmap.ic_drink,"DISMISS",
                ignoreWaterPI);
        //return the action
        return ignoreWaterAction;
    }

    public static void clearAllNotifications(Context context){

        NotificationManager notificationManager = (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }

}
