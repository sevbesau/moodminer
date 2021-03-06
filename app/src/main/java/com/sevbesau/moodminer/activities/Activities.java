package com.sevbesau.moodminer.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sevbesau.moodminer.adapters.ActivityListAdapter;
import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.Model;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.entities.Category;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Activities extends AppCompatActivity
  implements View.OnClickListener {

  private static final String LOG_TAG = Activities.class.getSimpleName();
  public static final int NEW_ACTIVITY_REQUEST_CODE = 1;

  private RecyclerView mRecyclerView;
  private FloatingActionButton mAddButton;

  private Model mModel;
  private ActivityListAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activities);

    mAddButton = findViewById(R.id.day_add_activity);
    mAddButton.setOnClickListener(this);

    mAdapter = new ActivityListAdapter(this);
    mRecyclerView = findViewById(R.id.day_activitiesRecycler);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    mModel = Model.getInstance(Activities.this.getApplication());

    mModel.getActivitiesWithCategories().observe(this, new Observer<List<ActivityWithCategories>>() {
      @Override
      public void onChanged(@Nullable final List<ActivityWithCategories> activities) {
        mAdapter.setActivities(activities);
      }
    });

    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
      0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
    ) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        ActivityWithCategories toDelete = mAdapter.getActivity(viewHolder.getAdapterPosition());
        mModel.deleteActivityWithCategories(toDelete);
        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
      }
    });

    helper.attachToRecyclerView(mRecyclerView);
  }

  @Override
  public void onClick(View view) {
    Log.d(LOG_TAG, "Launching ActivityNew");
    Intent intent = new Intent(this, ActivityNew.class);
    startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == NEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
      Activity activity = new Activity(
        data.getStringExtra(ActivityNew.EXTRA_REPLY_TITLE),
        data.getStringExtra(ActivityNew.EXTRA_REPLY_DESCRIPTION)
      );
      mModel.insertActivity(activity, new ArrayList<Long>().addAll(data.getLongArrayExtra(ActivityNew.EXTRA_REPLY_CATEGORY_ID)));

    } else {
      Toast.makeText(
        getApplicationContext(),
        "Canceled",
        Toast.LENGTH_LONG).show();
    }
  }
}
