package com.sevbesau.moodminer.model.database.activities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sevbesau.moodminer.model.database.DAO;

import java.util.List;

@Dao
public interface ActivityDao extends DAO<Activity> {
  @Insert
  void insert(Activity activity);

  @Update
  void update(Activity activity);

  @Query("SELECT * FROM Activities")
  LiveData<List<Activity>> getAllActivities();

  @Query("DELETE FROM Activities")
  public void deleteAll();
}
