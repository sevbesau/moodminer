package com.sevbesau.moodminer.model.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryRepository {

  private CategoryDao mCategoryDao;
  private LiveData<List<Category>> mCategories;

  public CategoryRepository(Application application) {
    CategoryRoomDatabase db = CategoryRoomDatabase.getDatabase(application);
    mCategoryDao = db.categoryDao();
    mCategories = mCategoryDao.getAllCategories();
  }

  public LiveData<List<Category>> getCategories() {
    return mCategories;
  }

  public void insert(Category category) {
    new insertAsyncTask(mCategoryDao).execute(category);
  }

  public void update(Category category) {
    new updateAsyncTask(mCategoryDao).execute(category);
  }

  private static class updateAsyncTask extends AsyncTask<Category, Void, Void> {

    private CategoryDao mAsyncTaskDao;

    updateAsyncTask(CategoryDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Category... params) {
      mAsyncTaskDao.update(params[0]);
      return null;
    }
  }

  private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

    private CategoryDao mAsyncTaskDao;

    insertAsyncTask(CategoryDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Category... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
