package com.yanir.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class RebootReceiver extends BroadcastReceiver {

    private static final String TAG = "RebootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        // log the event
        Log.d(TAG, "onReceive called");

        //create shared preferences object
        SharedPreferences prefs = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        //get the editor object
        SharedPreferences.Editor editor = prefs.edit();
        // increment the number of boots by one if it is not exist it will be created
        editor.putInt("bootCount", prefs.getInt("bootCount", 0) + 1);
        //save the changes asynchronously
        editor.apply();

        // make a toast to show the boot count
        Toast.makeText(context, "Boot Count: " + prefs.getInt("bootCount", -1), Toast.LENGTH_LONG).show();

        // log the boot count
        System.out.println("Boot Count: " + prefs.getInt("bootCount", -1));
    }
}