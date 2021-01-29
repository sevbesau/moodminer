package com.sevbesau.moodminer.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sevbesau.moodminer.model.api.AbstractAPIListener;
import com.sevbesau.moodminer.model.api.WebAPI;
import com.sevbesau.moodminer.model.database.AppRoomDatabase;
import com.sevbesau.moodminer.model.database.entities.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRepository {

  private static AppRoomDatabase mDb;
  private WebAPI mApi;
  private MutableLiveData<User> mUser;

  public UserRepository(Application application) {
    mDb = AppRoomDatabase.getDatabase(application);
    mApi = new WebAPI(application);

    //mUser = mDb.DAO().getUser();
    mUser = new MutableLiveData<>();
  }

  private void authLoop(User user) {
    System.out.println("sync authLoop " + user);
    if (user.refreshToken != null && user.accesToken == null) {
      authenticate(user);
    }
    if (!user.synced && user.refreshToken != null && user.accesToken != null) {
      syncUser(user);
    }
    if (user.synced) {
      user.isLoggedIn = true;
      update(user);
    }
  }


  private LifecycleOwner loginOwner;

  public void login(String email, String password, final LifecycleOwner owner) {
    System.out.println("sync login");
    loginOwner = owner;
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
          //insert(userWithId);
          authLoop(userWithId);
        }
      });
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void signup(String email, String password) {
    mApi.signup(email, password, new AbstractAPIListener() {
      @Override
      public void onSignup(User user) {
        mUser.setValue(user);
      }
    });
  }

  public LiveData<User> getUser() {
    return mUser;
  }

  public void deleteAll() {
    new deleteAsyncTask().execute();
  }

  public void insert(User user) {
    //new deleteAsyncTask().execute();
    new insertAsyncTask().execute(user);
  }

  public void update(User user) {
    new updateAsyncTask().execute(user);
  }

  private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
    @Override
    protected Void doInBackground(final User... user) {
      mDb.DAO().insertUser(user[0]);
      return null;
    }
  }

  private static class updateAsyncTask extends AsyncTask<User, Void, Void> {
    @Override
    protected Void doInBackground(final User... user) {
      mDb.DAO().updateUser(user[0]);
      return null;
    }
  }

  private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {
    @Override
    protected Void doInBackground(final User... user) {
      mDb.DAO().deleteAllUsers();
      return null;
    }
  }
}
