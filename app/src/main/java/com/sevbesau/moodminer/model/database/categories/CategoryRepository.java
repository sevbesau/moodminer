package com.sevbesau.moodminer.model.database.categories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.AsyncDelete;
import com.sevbesau.moodminer.model.database.AsyncInsert;
import com.sevbesau.moodminer.model.database.AsyncUpdate;
import com.sevbesau.moodminer.model.database.users.User;
import com.sevbesau.moodminer.model.database.users.UserDao;

import java.util.List;

public class CategoryRepository {

  private CategoryDao mCategoryDao;
  private LiveData<List<Category>> mCategories;

  public CategoryRepository(Application application) {
    AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
    mCategoryDao = db.categoryDao();
    mCategories = mCategoryDao.getAllCategories();
  }

  public LiveData<List<Category>> getCategories() {
    return mCategories;
  }

  public void insert(Category category) {
    new AsyncInsert<>(mCategoryDao).execute(category);
  }

  public void deleteAll() {
    new AsyncDelete<>(mCategoryDao).execute();
  }

  public void update(Category category) {
    new AsyncUpdate<>(mCategoryDao).execute(category);
  }
}

