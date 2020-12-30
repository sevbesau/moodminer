package com.sevbesau.moodminer.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Activity.class}, version = 3, exportSchema = false)
public abstract class ActivityRoomDatabase extends RoomDatabase {

  public abstract ActivityDao activityDao();

  private static ActivityRoomDatabase INSTANCE;

  public static ActivityRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (ActivityRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            ActivityRoomDatabase.class, "activity_database")
            .fallbackToDestructiveMigration()
            .addCallback(sActivityRoomDatabaseCallback)
            .build();
        }
      }
    }
    return INSTANCE;
  }

  private static ActivityRoomDatabase.Callback sActivityRoomDatabaseCallback =
    new ActivityRoomDatabase.Callback() {
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

    private final ActivityDao mDao;
    String[] activityTitles = {"koken", "lopen", "huiswerk"};
    String[] activityDescriptions = {
      "eten maken voor jezelf of anderen",
      "aan je conditie werken",
      "werken aan je toekomst"
    };
    //Category category = new Category("creativiteit", "", 1);
    //String[] activityTitles = {"koken", "lopen", "huiswerk"};

    PopulateDbAsync(ActivityRoomDatabase db) {
      mDao = db.activityDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
      // Start the app with a clean database every time.
      // Not needed if you only populate the database
      // when it is first created
      mDao.deleteAll();

      for (int i = 0; i <= activityTitles.length - 1; i++) {
        Activity activity = new Activity(
          activityTitles[i],
          activityDescriptions[i]
        );
        mDao.insert(activity);
      }
      return null;
    }
  }
}
