package com.sevbesau.moodminer.model;

import android.app.Application;

import com.sevbesau.moodminer.model.api.API;
import com.sevbesau.moodminer.model.api.APIListener;
import com.sevbesau.moodminer.model.api.WebAPI;
import com.sevbesau.moodminer.model.database.User;

public class Model_old {
  private static Model_old sInstance = null;

  public static Model_old getInstance(Application application) {
    if (sInstance == null) {
      sInstance = new Model_old(application);
    }
    return sInstance;
  }

  private final Application mApplication;
  private final API mApi;

  private Model_old(Application application) {
    mApplication = application;
    mApi = new WebAPI(mApplication);
  }

  public Application getApplication() { return mApplication; }

  public void login(String email, String password, APIListener listener) {
    mApi.login(email, password, listener);
  }

  public void signup(String email, String password, APIListener listener) {
    mApi.signup(email, password, listener);
  }

  public void authenticate(APIListener listener) {
    mApi.token(listener);
  }

  private User mUser;

  public User getUser() {
    return mUser;
  }
  public void setUser(User User) {
    // TODO locally store the user
    mUser = User;
  }
}
