package com.sevbesau.moodminer.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
  @Insert
  void insert(User user);

  @Update
  void update(User user);

  @Query("SELECT * FROM Users as user WHERE user.email = :email")
  LiveData<User> getUserByEmail(String email);

  @Query("SELECT * FROM Users LIMIT 1")
  LiveData<User> getUser();

  @Query("SELECT * FROM Users")
  LiveData<List<User>> getAllUsers();

  @Query("DELETE FROM Users")
  void deleteAll();
}
