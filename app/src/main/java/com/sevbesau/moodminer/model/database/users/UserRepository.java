package com.sevbesau.moodminer.model.database.users;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.AsyncDelete;
import com.sevbesau.moodminer.model.database.AsyncInsert;
import com.sevbesau.moodminer.model.database.AsyncUpdate;

public class UserRepository {

  private UserDao mUserDao;
  private LiveData<User> mUser;

  public UserRepository(Application application) {
    AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
    mUserDao = db.userDao();
    mUser = mUserDao.getUser();
  }

  LiveData<User> getUser() {
    return mUser;
  }

  public LiveData<User> get() {
    return mUserDao.getUser();
  }

  public void deleteAll() {
    new AsyncDelete<>(mUserDao).execute();
  }

  public void insert(User user) {
    new AsyncInsert<>(mUserDao).execute(user);
  }

  public void update(User user) {
    new AsyncUpdate(mUserDao).execute(user);
  }
}
