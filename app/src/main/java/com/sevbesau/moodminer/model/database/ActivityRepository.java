package com.sevbesau.moodminer.model.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepository {

  private ActivityDao mActivityDao;
  private LiveData<List<Activity>> mActivities;

  public ActivityRepository(Application application) {
    ActivityRoomDatabase db = ActivityRoomDatabase.getDatabase(application);
    mActivityDao = db.activityDao();
    mActivities = mActivityDao.getAllActivities();
  }

  public LiveData<List<Activity>> getActivities() {
    return mActivities;
  }

  public void insert(Activity activity) {
    new insertAsyncTask(mActivityDao).execute(activity);
  }

  public void update(Activity activity) {
    new updateAsyncTask(mActivityDao).execute(activity);
  }

  private static class updateAsyncTask extends AsyncTask<Activity, Void, Void> {

    private ActivityDao mAsyncTaskDao;

    updateAsyncTask(ActivityDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Activity... params) {
      mAsyncTaskDao.update(params[0]);
      return null;
    }
  }

  private static class insertAsyncTask extends AsyncTask<Activity, Void, Void> {

    private ActivityDao mAsyncTaskDao;

    insertAsyncTask(ActivityDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Activity... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
