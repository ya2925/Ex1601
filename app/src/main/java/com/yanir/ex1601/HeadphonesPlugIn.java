package com.yanir.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.Log;

public class HeadphonesPlugIn extends BroadcastReceiver {

    private static final String TAG = "HeadphonesPlugIn";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive called with intent: " + intent);
        if (AudioManager.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
            int state = intent.getIntExtra("state", -1);
            Log.d(TAG, "Headset state: " + state);
            if (state == 1) {
                SharedPreferences prefs = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                int headphonePlugCount = prefs.getInt("headphonePlugCount", 0);
                headphonePlugCount++;
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("headphonePlugCount", headphonePlugCount);
                editor.apply();
                Log.d(TAG, "Headphone plugged in. Count: " + headphonePlugCount);
            }
        }
    }
}