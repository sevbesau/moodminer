package com.sevbesau.moodminer.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sevbesau.moodminer.model.database.activities.Activity;
import com.sevbesau.moodminer.model.database.activities.ActivityDao;
import com.sevbesau.moodminer.model.database.activities.ActivityPopulator;
import com.sevbesau.moodminer.model.database.categories.Category;
import com.sevbesau.moodminer.model.database.categories.CategoryDao;
import com.sevbesau.moodminer.model.database.categories.CategoryPopulator;
import com.sevbesau.moodminer.model.database.users.User;
import com.sevbesau.moodminer.model.database.users.UserDao;
import com.sevbesau.moodminer.model.database.users.UserPopulator;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {BaseEntity.class, Activity.class, Category.class, User.class}, version = 6, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

  public abstract ActivityDao activityDao();
  public abstract CategoryDao categoryDao();
  public abstract UserDao userDao();

  private static AppRoomDatabase INSTANCE;

  public static AppRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (AppRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            AppRoomDatabase.class, "activity_database")
            .fallbackToDestructiveMigration()
            .addCallback(sAppRoomDatabaseCallback)
            .build();
        }
      }
    }
    return INSTANCE;
  }

  private static Callback sAppRoomDatabaseCallback =
    new Callback() {
      @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db) {
        super.onOpen(db);
        new PopulateDbAsync(INSTANCE).execute();
      }
    };

  /**
   * Populate the database in the background.
   */
  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private List<Populator> mPopulators;
    private AppRoomDatabase mDb;

    PopulateDbAsync(AppRoomDatabase db) {
      mDb = db;
      mPopulators = new ArrayList<>();
      mPopulators.add(new ActivityPopulator(db.activityDao()));
      mPopulators.add(new CategoryPopulator(db.categoryDao()));
      mPopulators.add(new UserPopulator(db.userDao()));
    }

    private void populateAll() {
      for (Populator p : mPopulators) {
        p.populate();
      }
    }

    @Override
    protected Void doInBackground(final Void... params) {
      mDb.clearAllTables();
      populateAll();
      return null;
    }
  }
}
