package com.sevbesau.moodminer.model.api;

import com.sevbesau.moodminer.model.database.User;

public interface APIListener {
  void onLogin(User user);
  void onSignup(User user);
  void onToken(String accesToken);
}
