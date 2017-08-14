package com.example.senafunakubo.imagedownload_asyntask;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by senafunakubo on 2017-08-10.
 */

public class L {
    public static void m(String message){
        Log.d("VIVZ",message);
    }

    public static void s(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
