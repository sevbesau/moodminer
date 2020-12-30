package com.sevbesau.moodminer.model.database.users;

import com.sevbesau.moodminer.model.database.DAO;
import com.sevbesau.moodminer.model.database.Populator;

public class UserPopulator extends Populator {

  public UserPopulator(DAO dao) {
    super(dao);
  }

  public void populate() {
    mDao.deleteAll();
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
