package com.sevbesau.moodminer.model.database;

import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;


public interface DAO<Type> {
  @Insert
  void insert(Type model);
  void update(Type model);
  void deleteAll();
}


