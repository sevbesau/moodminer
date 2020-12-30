package com.sevbesau.moodminer.model.database;

import android.os.AsyncTask;

import com.sevbesau.moodminer.model.database.users.User;

abstract class AsyncOperation<Entity> extends AsyncTask<Entity, Void, Void> {

  protected DAO<Entity> mAsyncTaskDao;

  AsyncOperation(DAO<Entity> dao) {
    mAsyncTaskDao = dao;
  }

  void executeOperation(final Entity param) {}

  @Override
  protected Void doInBackground(final Entity... params) {
    if (params.length == 0) executeOperation(null);
    else {
      for (Entity param : params) {
        executeOperation(param);
      }
    }
    return null;
  }
}
