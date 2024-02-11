package com.yanir.ex1601;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    TextView c1, c2, c3;
    int headphonePlugCount = 0;

    private BroadcastReceiver headphonePlugReceiver = new BroadcastReceiver() {
        public final static String CUSTOM_INTENT = "com.yanir.ex1601.HEADPHONES_PLUG_IN";
        @Override
        public void onReceive(Context context, Intent intent) {
            if (AudioManager.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                int state = intent.getIntExtra("state", -1);
                if (state == 1) {
                    headphonePlugCount++;
                    c2.setText("Headphone Plugged Count: " + headphonePlugCount);
                    if(headphonePlugCount / 5 == 0) {
                        Intent i = new Intent(CUSTOM_INTENT);
                        i.putExtra("count", headphonePlugCount/5);
                        context.sendBroadcast(i);
                    }
                }
            }
        }
    };

    public void updateTheTextView_headphone(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                c3.setText(t);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        c1.setText("Boot Count: " + prefs.getInt("bootCount", -1));

        // Register the receiver
        IntentFilter filter = new IntentFilter(AudioManager.ACTION_HEADSET_PLUG);
        registerReceiver(headphonePlugReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(headphonePlugReceiver);
    }
}