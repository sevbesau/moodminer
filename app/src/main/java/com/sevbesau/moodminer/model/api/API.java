package com.sevbesau.moodminer.model.api;

import com.sevbesau.moodminer.model.database.users.User;

public interface API {
  void login(String email, String password, APIListener listener);
  void signup(String email, String password, APIListener listener);
  void token(String refreshToken, APIListener listener);
  void get(User user, String resource, APIListener listener);
}
