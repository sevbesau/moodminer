package com.sevbesau.moodminer.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sevbesau.moodminer.model.database.Activity;
import com.sevbesau.moodminer.model.database.ActivityRepository;
import com.sevbesau.moodminer.model.database.Category;
import com.sevbesau.moodminer.model.database.CategoryRepository;

import java.util.List;

public class Model extends AndroidViewModel {

  private ActivityRepository mActivityRepository;
  private CategoryRepository mCategoryRepository;

  private LiveData<List<Activity>> mActivities;
  private LiveData<List<Category>> mCategories;

  private static Model sInstance;

  public static Model getInstance(Application application) {
    if (sInstance == null) {
      sInstance = new Model(application);
    }
    return sInstance;
  }

  private Model(Application application) {
    super(application);

    mActivityRepository = new ActivityRepository(application);
    mActivities = mActivityRepository.getActivities();
    mCategoryRepository = new CategoryRepository(application);
    mCategories = mCategoryRepository.getCategories();
  }

  public LiveData<List<Activity>> getActivities() {
    return mActivities;
  }

  public LiveData<List<Category>> getCategories() {
    return mCategories;
  }

  public void insertActivity(Activity activity) {
    mActivityRepository.insert(activity);
  }

  public void insertCategory(Category category) {
    mCategoryRepository.insert(category);
  }
}
