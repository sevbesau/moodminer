package com.sevbesau.moodminer.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.ActivityCategoryCrossRef;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.entities.Category;
import com.sevbesau.moodminer.model.database.entities.Day;
import com.sevbesau.moodminer.model.database.entities.DayActivityCrossRef;
import com.sevbesau.moodminer.model.database.entities.DayWithActivitiesAndCategories;
import com.sevbesau.moodminer.model.database.entities.User;
import com.sevbesau.moodminer.model.database.entities.UserWithActivities;
import com.sevbesau.moodminer.model.database.entities.UserWithDays;

import java.util.List;


@Dao
public interface AppDAO {

  /*
   * Activities
   */
  @Insert
  void insertActivity(Activity activity);

  @Query("SELECT * FROM Activities WHERE ownerId = :userId")
  LiveData<List<Activity>> getAllActivities(Integer userId);

  @Query("SELECT * FROM Activities WHERE ownerId = :ownerId AND title = :title AND description = :description LIMIT 1")
  Activity getSyncActivity(String title, Integer ownerId, String description);

  @Transaction
  @Query("SELECT * FROM Activities")
  LiveData<List<ActivityWithCategories>> getActivitiesWithCategories();

  @Update
  void updateActivity(Activity activity);

  @Query("DELETE FROM Activities")
  void deleteAllActivities();

  /*
   * Days
   */
  @Insert()
  void insertDay(Day day);

  @Update
  void updateDay(Day day);

  @Query("SELECT * FROM Days")
  LiveData<List<Day>> getAllDays();

  @Query("SELECT * FROM Days WHERE dayOwnerId = :ownerId AND day = :day AND month = :month AND year = :year LIMIT 1")
  LiveData<Day> getDay(Integer ownerId, Integer day, Integer month, Integer year);

  @Transaction
  @Query("SELECT * FROM Days as day" +
    " JOIN DayActivityCrossRef as da ON day.dayId = da.dayId" +
    " JOIN Activities as activity ON activity.activityId = da.activityId" +
    " WHERE dayOwnerId = :ownerId AND day = :day AND month = :month AND year = :year")
  LiveData<List<ActivityWithCategories>> getActivitiesWithCategoriesByDay(
    Integer ownerId, Integer day, Integer month, Integer year
  );

  @Query("DELETE FROM Days")
  void deleteAllDays();

  /*
   * Categories
   */
  @Insert
  void insertCategory(Category category);

  @Update
  void updateCategory(Category category);

  @Query("SELECT * FROM Categories")
  LiveData<List<Category>> getAllCategories();

  @Query("SELECT * FROM Categories")
  List<Category> getSyncAllCategories();

  @Query("DELETE FROM Categories")
  public void deleteAllCategories();

  /*
   * Users
   */
  @Insert
  void insertUser(User user);

  @Update
  void updateUser(User user);

  @Query("SELECT * FROM Users LIMIT 1")
  LiveData<User> getUser();

  @Query("SELECT * FROM Users LIMIT 1")
  User getSyncUser();

  @Transaction
  @Query("SELECT * FROM Users")
  LiveData<UserWithActivities> getUserWithActivities();

  @Transaction
  @Query("SELECT * FROM Users")
  LiveData<UserWithDays> getUserWithDays();

  @Query("SELECT * FROM Users")
  LiveData<List<User>> getAllUsers();

  @Query("DELETE FROM Users")
  void deleteAllUsers();

  /*
   * Relations
   */
  @Transaction
  @Insert
  void insertActivityCategoryCrossRef(ActivityCategoryCrossRef activityWithCategories);

  @Query("DELETE FROM ActivityCategoryCrossRef")
  void deleteAllActivityCategoryCrossRefs();

  @Insert
  void insertDayActivityCrossRef(DayActivityCrossRef dayWithActivities);

  @Query("DELETE FROM DayActivityCrossRef")
  void deleteAllDayActivityCrossRefs();
}


