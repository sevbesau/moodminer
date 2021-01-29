package com.sevbesau.moodminer.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.api.WebAPI;
import com.sevbesau.moodminer.model.database.entities.ActivityBearer;
import com.sevbesau.moodminer.model.database.entities.ActivityCategoryCrossRef;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.Category;
import com.sevbesau.moodminer.model.database.entities.User;

import java.util.List;

public class ActivityRepository {

  private static AppRoomDatabase mDb;
  private WebAPI mApi;

  public ActivityRepository(Application application) {
    mDb = AppRoomDatabase.getDatabase(application);
    mApi = new WebAPI(application);
  }

  public LiveData<List<Activity>> getActivities(long userId) {
    return mDb.DAO().getAllActivities(userId);
  }

  public LiveData<List<ActivityWithCategories>> getActivitiesWithCategories(long userId) {
    return mDb.DAO().getActivitiesWithCategories();
  }

  public void deleteAll() {
    new deleteAsyncTask().execute();
  }

  public void insert(Activity activity, List<Long> categoryIds) {
    new insertAsyncTask()
      .execute(new ActivityBearer(activity, categoryIds));
  }

  public void update(Activity activity) {
    new updateAsyncTask().execute(activity);
  }

  public void deleteActivityWithCategories(ActivityWithCategories toDelete) {
    System.out.println("activity delete: " + toDelete);
  }

  private static class insertAsyncTask extends AsyncTask<ActivityBearer, Void, Void> {
    @Override
    protected Void doInBackground(final ActivityBearer... bearers) {
      User user = mDb.DAO().getSyncUser();
      for (ActivityBearer bearer : bearers) {
        Activity activity = bearer.activity;
        activity.ownerId = user.userId;
        mDb.DAO().insertActivity(activity);
        System.out.println("inserted activity "+activity);
        for (long categoryId : bearer.categoryIds) {
          ActivityCategoryCrossRef activityCategoryCrossRef =
            new ActivityCategoryCrossRef(activity.aId, categoryId);
          mDb.DAO().insertActivityCategoryCrossRef(activityCategoryCrossRef);
          System.out.println("inserted activity category crossref "+activityCategoryCrossRef);
        }
      }
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
