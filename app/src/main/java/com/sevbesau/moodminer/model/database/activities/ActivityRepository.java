package com.sevbesau.moodminer.model.database.activities;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.ActivityWithCategory;
import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.AsyncDelete;
import com.sevbesau.moodminer.model.database.AsyncInsert;
import com.sevbesau.moodminer.model.database.AsyncUpdate;

import java.util.List;

public class ActivityRepository {

  private ActivityDao mActivityDao;
  private LiveData<List<Activity>> mActivities;

  public ActivityRepository(Application application) {
    AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
    mActivityDao = db.activityDao();
    mActivities = mActivityDao.getAllActivities();
  }

  public LiveData<List<Activity>> getActivities() {
    return mActivities;
  }

  public LiveData<List<ActivityWithCategory>> getActivitiesWithCategory() {
    return mActivityDao.getActivitiesWithCategory();
  }

  public void deleteAll() {
    new AsyncDelete<>(mActivityDao).execute();
  }

  public void insert(Activity activity) {
    new AsyncInsert<>(mActivityDao).execute(activity);
  }

  public void update(Activity activity) {
    new AsyncUpdate<>(mActivityDao).execute(activity);
  }
}
