package com.sevbesau.moodminer.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ActivityDao {
  @Insert
  void insert(Activity activity);

  @Update
  void update(Activity activity);

  @Query("SELECT * FROM Activities")
  LiveData<List<Activity>> getAllActivities();

  @Query("DELETE FROM Activities")
  void deleteAll();
}
