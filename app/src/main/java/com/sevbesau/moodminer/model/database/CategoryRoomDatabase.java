package com.sevbesau.moodminer.model.database;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sevbesau.moodminer.R;

@Database(entities = {Category.class}, version = 3, exportSchema = false)
public abstract class CategoryRoomDatabase extends RoomDatabase {

  public abstract CategoryDao categoryDao();

  private static CategoryRoomDatabase INSTANCE;

  public static CategoryRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (CategoryRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            CategoryRoomDatabase.class, "category_database")
            .fallbackToDestructiveMigration()
            .addCallback(sCategoryRoomDatabaseCallback)
            .build();
        }
      }
    }
    return INSTANCE;
  }

  private static Callback sCategoryRoomDatabaseCallback =
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

    private final CategoryDao mDao;
    String[] categoryTitles = {
      "lichaamsbeweging",
      "ontspanning",
      "creativiteit",
      "uitdaging",
      "sociaal",
      "rust"
    };
    String[] categoryDescriptions = {
      "je lichaam moe maken",
      "dingen doen die je weer opladen",
      "out of the box",
      "daag jezelf uit",
      "spreek eens af",
      "'niks' doen"
    };

    PopulateDbAsync(CategoryRoomDatabase db) {
      mDao = db.categoryDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
      // Start the app with a clean database every time.
      // Not needed if you only populate the database
      // when it is first created
      mDao.deleteAll();

      for (int i = 0; i <= categoryTitles.length - 1; i++) {
        Category category = new Category(
          categoryTitles[i],
          categoryDescriptions[i],
          R.drawable.img_running
        );
        mDao.insert(category);
      }
      return null;
    }
  }
}
