package com.sevbesau.moodminer.model.api;

import com.sevbesau.moodminer.model.database.User;

public class AbstractAPIListener implements APIListener {
  @Override
  public void onLogin(User user) { }
  @Override
  public void onSignup(User user) { }
  @Override
  public void onToken(String accesToken) { }
}
