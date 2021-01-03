package com.sevbesau.moodminer.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.ActivityCategoryCrossRef;
import com.sevbesau.moodminer.model.database.entities.Category;
import com.sevbesau.moodminer.model.database.populators.ActivityPopulator;
import com.sevbesau.moodminer.model.database.populators.CategoryPopulator;
import com.sevbesau.moodminer.model.database.entities.Day;
import com.sevbesau.moodminer.model.database.entities.DayActivityCrossRef;
import com.sevbesau.moodminer.model.database.entities.User;
import com.sevbesau.moodminer.model.database.populators.Populator;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {
  Activity.class,
  ActivityCategoryCrossRef.class,
  Category.class,
  User.class,
  Day.class,
  DayActivityCrossRef.class
}, version = 15, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

  public abstract AppDAO DAO();

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
      mPopulators.add(new CategoryPopulator(db.DAO()));
      //mPopulators.add(new ActivityPopulator(db.DAO()));
      //mPopulators.add(new UserPopulator(db.userDao()));
      //mPopulators.add(new DayPopulator(db.dayDao()));
    }

    private void populateAll() {
      for (Populator p : mPopulators) {
        p.populate();
      }
    }

    @Override
    protected Void doInBackground(final Void... params) {
      //mDb.clearAllTables();
      mDb.DAO().deleteAllUsers();
      //populateAll();
      return null;
    }
  }
}
