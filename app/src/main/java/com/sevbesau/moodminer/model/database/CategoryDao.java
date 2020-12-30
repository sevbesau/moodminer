package com.sevbesau.moodminer.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {
  @Insert
  void insert(Category category);

  @Update
  void update(Category category);

  @Query("SELECT * FROM Categories")
  LiveData<List<Category>> getAllCategories();

  @Query("DELETE FROM Categories")
  void deleteAll();
}
