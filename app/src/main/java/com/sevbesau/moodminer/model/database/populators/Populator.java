package com.sevbesau.moodminer.model.database.populators;

import com.sevbesau.moodminer.model.database.AppDAO;

public abstract class Populator {

  protected AppDAO mDao;

  public Populator(AppDAO dao) {
    mDao = dao;
  }

  public void populate() {}

}
