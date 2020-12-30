package com.sevbesau.moodminer.model.database;

public class AsyncUpdate<Entity> extends AsyncOperation<Entity> {

  public AsyncUpdate(DAO<Entity> dao) {
    super(dao);
  }

  public void executeOperation(final Entity param) {
    mAsyncTaskDao.update(param);
  }
}
