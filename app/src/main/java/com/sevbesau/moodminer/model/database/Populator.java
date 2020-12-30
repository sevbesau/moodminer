package com.sevbesau.moodminer.model.database;

public abstract class Populator {

  protected DAO mDao;

  public Populator(DAO dao) {
    mDao = dao;
  }

  public void populate() {}

  public void clear() {}
}
