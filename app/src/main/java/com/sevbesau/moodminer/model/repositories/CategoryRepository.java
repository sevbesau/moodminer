package com.sevbesau.moodminer.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.Category;

import java.util.List;

public class CategoryRepository {

  private static AppRoomDatabase mDb;
  private LiveData<List<Category>> mCategories;

  public CategoryRepository(Application application) {
    mDb = AppRoomDatabase.getDatabase(application);
    mCategories = mDb.DAO().getAllCategories();
  }

  public LiveData<List<Category>> getCategories() {
    return mCategories;
  }

  public void insert(Category category) {
    new insertAsyncTask().execute(category);
  }

  public void deleteAll() {
    new deleteAsyncTask().execute();
  }

  public void update(Category category) {
    new updateAsyncTask().execute(category);
  }

  private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {
    @Override
    protected Void doInBackground(final Category... category) {
      mDb.DAO().insertCategory(category[0]);
      return null;
    }
  }

  private static class updateAsyncTask extends AsyncTask<Category, Void, Void> {
    @Override
    protected Void doInBackground(final Category... category) {
      mDb.DAO().updateCategory(category[0]);
      return null;
    }
  }

  private static class deleteAsyncTask extends AsyncTask<Activity, Void, Void> {
    @Override
    protected Void doInBackground(final Activity... activity) {
      mDb.DAO().deleteAllCategories();
      return null;
    }
  }
}

