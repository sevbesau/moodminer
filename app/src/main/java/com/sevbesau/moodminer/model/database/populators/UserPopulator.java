package com.sevbesau.moodminer.model.database.populators;

import com.sevbesau.moodminer.model.database.AppDAO;

public class UserPopulator extends Populator {

  public UserPopulator(AppDAO dao) {
    super(dao);
  }

  public void populate() {
    mDao.deleteAllUsers();
    /*
    mDao.insert(new User(
      //"info@sevbesau.xyz",
      //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmViM2M1NGYxZDlkNTA2ZGM5MjEzYjEiLCJlbWFpbCI6ImluZm9Ac2V2YmVzYXUueHl6IiwiaWF0IjoxNjA5Mjk0OTk3LCJleHAiOjE2MDk4OTk3OTd9.kgt4PjJlqV60e-7p-s8oljKvAaN4gfhsf7EpptBB_aw",
      null,
      null,
      null,
      null
    ));

     */
  }
}
