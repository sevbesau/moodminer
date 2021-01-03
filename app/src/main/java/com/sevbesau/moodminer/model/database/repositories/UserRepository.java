package com.sevbesau.moodminer.model.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.entities.User;

public class UserRepository {

  private static AppRoomDatabase mDb;
  private LiveData<User> mUser;

  public UserRepository(Application application) {
    mDb = AppRoomDatabase.getDatabase(application);
    mUser = mDb.DAO().getUser();
  }

  LiveData<User> getUser() {
    return mUser;
  }

  public LiveData<User> get() {
    return mDb.DAO().getUser();
  }

  public void deleteAll() {
    new deleteAsyncTask().execute();
  }

  public void insert(User user) {
    //new deleteAsyncTask().execute();
    new insertAsyncTask().execute(user);
  }

  public void update(User user) {
    new updateAsyncTask().execute(user);
  }

  private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
    @Override
    protected Void doInBackground(final User... user) {
      mDb.DAO().insertUser(user[0]);
      return null;
    }
  }

  private static class updateAsyncTask extends AsyncTask<User, Void, Void> {
    @Override
    protected Void doInBackground(final User... user) {
      mDb.DAO().updateUser(user[0]);
      return null;
    }
  }

  private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {
    @Override
    protected Void doInBackground(final User... user) {
      mDb.DAO().deleteAllUsers();
      return null;
    }
  }
}
