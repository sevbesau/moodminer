package com.sevbesau.moodminer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sevbesau.moodminer.R;

public class Main extends AppCompatActivity {

    private static final String LOG_TAG = Main.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void launchMyActivities(View view) {
        Log.d(LOG_TAG, "Launching MyActivities");
        Intent intent = new Intent(this, Activities.class);
        startActivityForResult(intent, 1);
    }

    public void launchMyDay(View view) {
        Log.d(LOG_TAG, "Launching MyDay");
        Intent intent = new Intent(this, Today.class);
        startActivityForResult(intent, 1);
    }

    public void launchCategories(View view) {
        Log.d(LOG_TAG, "Launching Categories");
        Intent intent = new Intent(this, Categories.class);
        startActivityForResult(intent, 1);
    }
}
