package com.sevbesau.moodminer.model.api;

import com.sevbesau.moodminer.model.database.users.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface APIListener {
  void onLogin(User user);
  void onSignup(User user);
  void onToken(String accesToken);
  void onData(JSONObject res) throws JSONException;
}
