package com.sevbesau.moodminer.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sevbesau.moodminer.ActivityListAdapter;
import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.activities.Activity;
import com.sevbesau.moodminer.model.Model;

import java.util.List;

public class Activities extends AppCompatActivity
  implements View.OnClickListener {

  private static final String LOG_TAG = Activities.class.getSimpleName();
  public static final int NEW_ACTIVITY_REQUEST_CODE = 1;

  private RecyclerView mRecyclerView;
  private FloatingActionButton mAddButton;
  private Model mModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activities);

    mAddButton = findViewById(R.id.addActivityFAB);
    mAddButton.setOnClickListener(this);

    final ActivityListAdapter adapter = new ActivityListAdapter(this);
    mRecyclerView = findViewById(R.id.recyclerView);
    mRecyclerView.setAdapter(adapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    mModel = Model.getInstance(Activities.this.getApplication());

    mModel.getActivities().observe(this, new Observer<List<Activity>>() {
      @Override
      public void onChanged(@Nullable final List<Activity> activities) {
        adapter.setActivities(activities);
      }
    });
  }

  @Override
  public void onClick(View view) {
    Log.d(LOG_TAG, "Launching AddActivity");
    Intent intent = new Intent(this, ActivityAdd.class);
    startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
  }


  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == NEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
      Activity activity = new Activity(
        data.getStringExtra(ActivityAdd.EXTRA_REPLY_TITLE),
        data.getStringExtra(ActivityAdd.EXTRA_REPLY_DESCRIPTION),
        null
      );
      mModel.insertActivity(activity);
    } else {
      Toast.makeText(
        getApplicationContext(),
        "Canceled",
        Toast.LENGTH_LONG).show();
    }
  }
}
