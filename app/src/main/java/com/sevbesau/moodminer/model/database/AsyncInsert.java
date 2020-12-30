package com.sevbesau.moodminer.model.database;

import android.os.AsyncTask;

import com.sevbesau.moodminer.model.database.users.User;

public class AsyncInsert<Entity> extends AsyncOperation<Entity> {

  public AsyncInsert(DAO<Entity> dao) {
    super(dao);
  }

  public void executeOperation(final Entity param) {
    mAsyncTaskDao.insert(param);
  }
}
