package com.sevbesau.moodminer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchMyActivities(View view) {
        Log.d(LOG_TAG, "Launching MyActivities");
        Intent intent = new Intent(this, MyActivities.class);
        startActivityForResult(intent, 1);
    }

    public void launchMyDay(View view) {
        Log.d(LOG_TAG, "Launching MyDay");
        Intent intent = new Intent(this, MyDay.class);
        startActivityForResult(intent, 1);
    }
}
