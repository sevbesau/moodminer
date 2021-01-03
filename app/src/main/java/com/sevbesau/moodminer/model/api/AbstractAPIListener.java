package com.sevbesau.moodminer.model.api;

import com.sevbesau.moodminer.model.database.entities.User;

import org.json.JSONException;
import org.json.JSONObject;

public class AbstractAPIListener implements APIListener {
  @Override
  public void onLogin(User user) { }
  @Override
  public void onSignup(User user) { }
  @Override
  public void onToken(String accesToken) { }
  @Override
  public void onData(JSONObject res) throws JSONException {}
}
