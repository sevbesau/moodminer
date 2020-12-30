package com.sevbesau.moodminer.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.sevbesau.moodminer.activities.Listener;
import com.sevbesau.moodminer.model.api.APIListener;
import com.sevbesau.moodminer.model.api.AbstractAPIListener;
import com.sevbesau.moodminer.model.api.WebAPI;
import com.sevbesau.moodminer.model.database.activities.Activity;
import com.sevbesau.moodminer.model.database.activities.ActivityRepository;
import com.sevbesau.moodminer.model.database.categories.Category;
import com.sevbesau.moodminer.model.database.categories.CategoryRepository;
import com.sevbesau.moodminer.model.database.users.User;
import com.sevbesau.moodminer.model.database.users.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Model extends AndroidViewModel {

  private ActivityRepository mActivityRepository;
  private CategoryRepository mCategoryRepository;
  private UserRepository mUserRepository;

  private LiveData<List<Activity>> mActivities;
  private LiveData<List<Category>> mCategories;

  private WebAPI mApi;

  private static Model sInstance;

  public static Model getInstance(Application application) {
    if (sInstance == null) {
      sInstance = new Model(application);
    }
    return sInstance;
  }

  private Model(Application application) {
    super(application);

    mApi = new WebAPI(application);

    mActivityRepository = new ActivityRepository(application);
    mActivities = mActivityRepository.getActivities();
    mCategoryRepository = new CategoryRepository(application);
    mCategories = mCategoryRepository.getCategories();
    mUserRepository = new UserRepository(application);
  }

  private List<Listener> listeners = new ArrayList<>();
  public void addListener(Listener l) { listeners.add(l); }
  public void removeListener(Listener l) { listeners.remove(l); }
  private void pingListeners() {
    for (Listener l : listeners) {
      l.ping();
    }
  }

  private void authLoop(User user) {
    System.out.println("sync authLoop "+user);
    if (user.refreshToken != null && user.accesToken == null) {
      authenticate(user);
    }
    if (!user.synced && user.refreshToken != null && user.accesToken != null) {
      syncUser(user);
    }
    if (user.synced) {
      syncActivities(user);
      syncCategories(user);
      pingListeners();
    }
  }

  LifecycleOwner loginOwer;

  public void login(String email, String password, final LifecycleOwner owner) {
    System.out.println("sync login");
    loginOwer = owner;
    mApi.login(email, password, new AbstractAPIListener() {
      @Override
      public void onLogin(User user) {
        authLoop(user);
      }
    });
  }

  public void authenticate(final User user) {
    System.out.println("sync authenticate");
    mApi.token(user.refreshToken, new AbstractAPIListener() {
      @Override
      public void onToken(String accesToken) {
        user.accesToken = accesToken;
        authLoop(user);
      }
    });
  }

  public void syncUser(final User user) {
    // post the user, this returns the user with it's id
    System.out.println("sync user");
    try {
      mApi.post(user, "users", user.toJson(), new AbstractAPIListener() {
        @Override
        public void onData(JSONObject res) throws JSONException {
          User userWithId = User.getFromJson(res);
          userWithId.synced = true;
          userWithId.refreshToken = user.refreshToken;
          userWithId.accesToken = user.accesToken;
          mUserRepository.insert(user);
          authLoop(userWithId);
        }
      });
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

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
    mActivityRepository.getActivities().observe(loginOwer, new Observer<List<Activity>>() {
      @Override
      public void onChanged(final List<Activity> activities) {
        for (Activity activity : activities) {
          try {
            mApi.post(user, "activities", activity.toJson(user.id), new AbstractAPIListener() {
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
              System.out.println("sync get activities: "+jsonActivities);
              for (int i = 0; i < jsonActivities.length(); i++) {
                Activity newActivity = Activity.getFromJson(jsonActivities.getJSONObject(i));
                // TODO get this working!
                if (!activities.contains(newActivity)) {
                  System.out.println("synced new activity: "+newActivity);
                  mActivityRepository.insert(newActivity);
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

  public void signup(String email, String password, APIListener listener) {
    mApi.signup(email, password, listener);
  }

  public void setUser(User user) {
    mUserRepository.deleteAll();
    mUserRepository.insert(user);
  }

  public void clearUser() {
    mUserRepository.deleteAll();
  }

  public LiveData<User> getUser() {
    return mUserRepository.get();
  }

  public void updateUser(User user) {
    mUserRepository.update(user);
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
