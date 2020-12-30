package com.sevbesau.moodminer.model.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.api.API;
import com.sevbesau.moodminer.model.api.APIListener;
import com.sevbesau.moodminer.model.api.WebAPI;

public class UserRepository {

  private UserDao mUserDao;
  private LiveData<User> mUser;
  private API mApi;

  UserRepository(Application application) {
    UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
    mUserDao = db.userDao();
    mUser = mUserDao.getUser();
    mApi = new WebAPI(application);
  }

  LiveData<User> getUser() {
    return mUser;
  }

  public void insert(User user) {
    new insertAsyncTask(mUserDao).execute(user);
  }

  public void update(User user) {
    new updateAsyncTask(mUserDao).execute(user);
  }

  public void login(String email, String password, APIListener listener) {
    mApi.login(email, password, listener);
  }

  public void signup(String email, String password, APIListener listener) {
    mApi.signup(email, password, listener);
  }

  public void authenticate(APIListener listener) {
    mApi.token(listener);
  }

  private static class updateAsyncTask extends AsyncTask<User, Void, Void> {

    private UserDao mAsyncTaskDao;

    updateAsyncTask(UserDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final User... params) {
      mAsyncTaskDao.update(params[0]);
      return null;
    }
  }

  private static class insertAsyncTask extends android.os.AsyncTask<User, Void, Void> {

    private UserDao mAsyncTaskDao;

    insertAsyncTask(UserDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final User... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }

}
