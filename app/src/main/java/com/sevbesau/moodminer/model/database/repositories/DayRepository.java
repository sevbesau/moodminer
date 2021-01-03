package com.sevbesau.moodminer.model.database.repositories;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.entities.Day;
import com.sevbesau.moodminer.model.database.entities.DayActivityCrossRef;
import com.sevbesau.moodminer.model.database.entities.DayWithActivitiesAndCategories;

import java.util.Date;
import java.util.List;

public class DayRepository {

  private static AppRoomDatabase mDb;
  private LiveData<List<Day>> mDays;

  private LiveData<DayWithActivitiesAndCategories> mTodayWithActivitiesAndCategories;

  public DayRepository(Application application) {
    mDb = AppRoomDatabase.getDatabase(application);
    mDays = mDb.DAO().getAllDays();
  }

  public LiveData<List<Day>> getDays() {
    return mDays;
  }

  public LiveData<Day> getDay(Integer ownerId, Date date) {
    try {
      insert(new Day(ownerId, date.getDay(), date.getMonth(), date.getYear()));
    } catch (Error e) {
      e.printStackTrace();
    }
    return mDb.DAO().getDay(ownerId, date.getDay(), date.getMonth(), date.getYear());
  }

  public LiveData<List<ActivityWithCategories>> getActivitiesWithCategoriesByDay(Integer ownerId, Date date) {
    return mDb.DAO().getActivitiesWithCategoriesByDay(ownerId, date.getDay(), date.getMonth(), date.getYear());
  }

  public void insert(Day day) {
    new insertAsyncTask().execute(day);
  }

  public void insertActivity(Activity activity, Integer dayId) {
    new insertActivityAsyncTask().execute(new DayActivityCrossRef(activity.activityId, dayId));
  }

  public void deleteAll() {
    new deleteAsyncTask().execute();
  }

  public void update(Day day) {
    new updateAsyncTask().execute(day);
  }

  private static class insertAsyncTask extends AsyncTask<Day, Void, Void> {
    @Override
    protected Void doInBackground(final Day... day) {
      mDb.DAO().insertDay(day[0]);
      System.out.println("today inserted: "+day[0]);
      return null;
    }
  }

  private static class insertActivityAsyncTask extends AsyncTask<DayActivityCrossRef, Void, Void> {
    @Override
    protected Void doInBackground(final DayActivityCrossRef... ref) {
      try {
        mDb.DAO().insertDayActivityCrossRef(ref[0]);
        System.out.println("today inserted: "+ref[0]);
      } catch (SQLiteConstraintException e) {
        System.out.println("today exists: "+ref[0]);
        e.printStackTrace();
      }
      return null;
    }
  }

  private static class updateAsyncTask extends AsyncTask<Day, Void, Void> {
    @Override
    protected Void doInBackground(final Day... day) {
      mDb.DAO().updateDay(day[0]);
      return null;
    }
  }

  private static class deleteAsyncTask extends AsyncTask<Day, Void, Void> {
    @Override
    protected Void doInBackground(final Day... day) {
      mDb.DAO().deleteAllDays();
      return null;
    }
  }
}

