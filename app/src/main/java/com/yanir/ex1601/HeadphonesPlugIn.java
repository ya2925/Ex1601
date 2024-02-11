package com.yanir.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.Log;
import com.yanir.ex1601.MainActivity;

public class HeadphonesPlugIn extends BroadcastReceiver {

    private final static String CUSTOM_INTENT = "com.yanir.ex1601.HEADPHONES_PLUG_IN";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(CUSTOM_INTENT)) {
            int count = intent.getIntExtra("count", -1);
            Log.d("HeadphonesPlugIn", "Headphones plugged in " + count + " times");
            updateTheTextView_headphone("Headphones plugged in " + count + " times");
        }
    }
}