package com.sevbesau.moodminer.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.sevbesau.moodminer.activities.Listener;
import com.sevbesau.moodminer.model.api.APIListener;
import com.sevbesau.moodminer.model.api.AbstractAPIListener;
import com.sevbesau.moodminer.model.api.WebAPI;
import com.sevbesau.moodminer.model.database.entities.Day;
import com.sevbesau.moodminer.model.repositories.DayRepository;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.repositories.ActivityRepository;
import com.sevbesau.moodminer.model.database.entities.Category;
import com.sevbesau.moodminer.model.repositories.CategoryRepository;
import com.sevbesau.moodminer.model.database.entities.User;
import com.sevbesau.moodminer.model.repositories.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Model extends AndroidViewModel {

  private ActivityRepository mActivityRepository;
  private CategoryRepository mCategoryRepository;
  private UserRepository mUserRepository;
  private DayRepository mDayRepository;

  private static Model sInstance;

  private long mUserId;

  public static Model getInstance(Application application) {
    if (sInstance == null) {
      sInstance = new Model(application);
    }
    return sInstance;
  }

  private Model(Application application) {
    super(application);

    mActivityRepository = new ActivityRepository(application);
    mCategoryRepository = new CategoryRepository(application);
    mUserRepository = new UserRepository(application);
    mDayRepository = new DayRepository(application);
  }

  public void login(String email, String password, final LifecycleOwner owner) {
    mUserRepository.login(email, password, owner);
  }

  /*
  public void syncCategories(User user) {
    System.out.println("sync categories");
    mCategoryRepository.deleteAll();
    mApi.get(user, "categories", new AbstractAPIListener() {
      @Override
      public void onData(JSONObject res) {
        try {
          JSONArray jsonCategories = res.getJSONArray("data");
          for (int i = 0; i < jsonCategories.length(); i++) {
            mCategoryRepository.insert(Category.getFromJson(jsonCategories.getJSONObject(i)));
          }
        } catch (JSONException error) {
          System.out.println(error);
        }
      }
    });
  }

  public void syncActivities(final User user) {
    System.out.println("sync activities");
    // post all stored activities
    mActivityRepository.getActivities(mUserId).observe(loginOwer, new Observer<List<Activity>>() {
      @Override
      public void onChanged(final List<Activity> activities) {
        mActivityRepository.deleteAll();
        for (Activity activity : activities) {
          try {
            mApi.post(user, "activities", activity.toJson(user.userId), new AbstractAPIListener() {
              @Override
              public void onData(JSONObject res) {
                System.out.println("sync activity " + res);
              }
            });
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        mApi.get(user, "activities", new AbstractAPIListener() {
          @Override
          public void onData(JSONObject res) {
            try {
              JSONArray jsonActivities = res.getJSONArray("data");
              System.out.println("sync get activities: " + jsonActivities);
              for (int i = 0; i < jsonActivities.length(); i++) {
                Activity newActivity = Activity.getFromJson(jsonActivities.getJSONObject(i));
                // TODO get this working!
                if (!activities.contains(newActivity)) {
                  System.out.println("synced new activity: " + newActivity);
                  //mActivityRepository.insert(newActivity, 0);
                }
              }
            } catch (JSONException error) {
              System.out.println(error);
            }
          }
        });

      }
    });
    // clear all activities
    // get all stored activities from api

  }
   */

  public void signup(String email, String password, APIListener listener) {
    mUserRepository.signup(email, password);
  }

  public void setUser(User user) {
    mUserRepository.deleteAll();
    mUserRepository.insert(user);
  }

  public void clearUser() {
    mUserRepository.deleteAll();
  }

  public LiveData<User> getUser() {
    return mUserRepository.getUser();
  }

  public void updateUser(User user) {
    mUserRepository.update(user);
  }

  public LiveData<List<ActivityWithCategories>> getActivitiesWithCategoriesByDay() {
    return mDayRepository.getActivitiesWithCategoriesByDay(mUserId, new Date());
  }

  public LiveData<List<Activity>> getActivities() {
    return mActivityRepository.getActivities(mUserId);
  }

  public LiveData<List<ActivityWithCategories>> getActivitiesWithCategories() {
    return mActivityRepository.getActivitiesWithCategories(mUserId);
  }

  public LiveData<List<Category>> getCategories() {
    return mCategoryRepository.getCategories();
  }

  public void insertActivity(Activity activity, List<Long> categoryIds) {
    mActivityRepository.insert(activity, categoryIds);
  }

  public void insertCategory(Category category) {
    mCategoryRepository.insert(category);
  }

  public void insertDayActivity(Activity activity, long dayId) {
    mDayRepository.insertActivity(activity, dayId);
  }

  public LiveData<Day> getToday() {
    return mDayRepository.getDay(mUserId, new Date());
  }

  public void updateDay(Day day) {
    mDayRepository.update(day);
  }

  public void deleteActivityWithCategories(ActivityWithCategories toDelete) {
    mActivityRepository.deleteActivityWithCategories(toDelete);
  }
}
