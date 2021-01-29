package com.sevbesau.moodminer.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sevbesau.moodminer.adapters.ActivityListAdapter;
import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.adapters.ActivitySpinnerAdapter;
import com.sevbesau.moodminer.adapters.CategorySpinnerAdapter;
import com.sevbesau.moodminer.model.Model;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.entities.Category;
import com.sevbesau.moodminer.model.database.entities.Day;
import com.sevbesau.moodminer.model.database.entities.DayWithActivitiesAndCategories;

import java.util.ArrayList;
import java.util.List;

public class Today extends AppCompatActivity
  implements RatingBar.OnRatingBarChangeListener,
  AdapterView.OnItemSelectedListener {

  private static final String LOG_TAG = Today.class.getSimpleName();
  public static final int ADD_ACTIVITY_REQUEST_CODE = 1;

  private RatingBar mRatingBar;
  private RecyclerView mDayActivities;
  private Spinner mActivitySpinner;

  private List<Activity> mSpinnerActivites;
  private ActivitySpinnerAdapter mActivitySpinnerAdapter;

  private ActivityListAdapter mActivitiesAdapter;

  private Model mModel;

  private Day mDay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.today);

    mModel = Model.getInstance(Today.this.getApplication());
    mRatingBar = findViewById(R.id.day_ratingBar);
    mRatingBar.setOnRatingBarChangeListener(this);

    mActivitiesAdapter = new ActivityListAdapter(this);
    mDayActivities = findViewById(R.id.day_activitiesRecycler);
    mDayActivities.setAdapter(mActivitiesAdapter);
    mDayActivities.setLayoutManager(new LinearLayoutManager(this));

    mModel.getToday().observe(this, new Observer<Day>() {
      @Override
      public void onChanged(@Nullable final Day today) {
        System.out.println("today: "+today);
        if (today != null) {
          mDay = today;
          mRatingBar.setRating(mDay.score);
        }
      }
    });

    mModel.getActivitiesWithCategoriesByDay().observe(this, new Observer<List<ActivityWithCategories>>() {
      @Override
      public void onChanged(@Nullable final List<ActivityWithCategories> activities) {
        System.out.println("today: "+activities);
        mActivitiesAdapter.setActivities(activities);
      }
    });

    mActivitySpinner = (Spinner) findViewById(R.id.day_activity_spinner);
    mActivitySpinner.setOnItemSelectedListener(this);

    mSpinnerActivites = new ArrayList<>();
    mActivitySpinnerAdapter = new ActivitySpinnerAdapter(this, mSpinnerActivites);

    mModel.getActivities().observe(this, new Observer<List<Activity>>() {
      @Override
      public void onChanged(@Nullable final List<Activity> activities) {
        mSpinnerActivites.clear();
        for (Activity activity : activities) {
          System.out.println("spinner add " + activity);
          mSpinnerActivites.add(activity);
        }
        mActivitySpinner.setAdapter(mActivitySpinnerAdapter);
      }
    });
  }

  @Override
  public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
    mDay.score = rating;
    mModel.updateDay(mDay);
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    Activity selected = (Activity) parent.getItemAtPosition(position);
    System.out.println("today: insert: " + selected);
    mModel.insertDayActivity(selected, mDay.dayId);
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
