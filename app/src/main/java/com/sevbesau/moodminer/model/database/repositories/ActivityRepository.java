package com.sevbesau.moodminer.model.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.entities.ActivityBearer;
import com.sevbesau.moodminer.model.database.entities.ActivityCategoryCrossRef;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.User;

import java.util.List;

public class ActivityRepository {

  private static AppRoomDatabase mDb;

  public ActivityRepository(Application application) {
    mDb = AppRoomDatabase.getDatabase(application);
  }

  public LiveData<List<Activity>> getActivities(Integer userId) {
    return mDb.DAO().getAllActivities(userId);
  }

  public LiveData<List<ActivityWithCategories>> getActivitiesWithCategory(Integer userId) {
    return mDb.DAO().getActivitiesWithCategories();
  }

  public void deleteAll() {
    new deleteAsyncTask().execute();
  }

  public void insert(Activity activity, long categoryId) {
    new insertAsyncTask()
      .execute(new ActivityBearer(activity, categoryId));
  }

  public void update(Activity activity) {
    new updateAsyncTask().execute(activity);
  }

  private static class insertAsyncTask extends AsyncTask<ActivityBearer, Void, Void> {
    @Override
    protected Void doInBackground(final ActivityBearer... activities) {
      User user = mDb.DAO().getSyncUser();
      Activity activity = activities[0].activity;
      activity.ownerId = user.userId;
      mDb.DAO().insertActivity(activity);
      Activity newActivity = mDb.DAO().getSyncActivity(activity.title, activity.ownerId, activity.description);
      ActivityCategoryCrossRef activityCategoryCrossRef =
        new ActivityCategoryCrossRef(newActivity.activityId, activities[0].categoryId);
      mDb.DAO().insertActivityCategoryCrossRef(activityCategoryCrossRef);
      System.out.println("inserted activity "+newActivity);
      System.out.println("inserted activity "+activityCategoryCrossRef);
      return null;
    }
  }

  private static class updateAsyncTask extends AsyncTask<Activity, Void, Void> {
    @Override
    protected Void doInBackground(final Activity... activity) {
      mDb.DAO().updateActivity(activity[0]);
      return null;
    }
  }

  private static class deleteAsyncTask extends AsyncTask<Activity, Void, Void> {
    @Override
    protected Void doInBackground(final Activity... activity) {
      mDb.DAO().deleteAllActivities();
      return null;
    }
  }
}
