package com.sevbesau.moodminer.model.database;

public class AsyncDelete<Entity> extends AsyncOperation<Entity> {

  public AsyncDelete(DAO<Entity> dao) {
    super(dao);
  }

  public void executeOperation(final Entity param) {
    mAsyncTaskDao.deleteAll();
  }
}
