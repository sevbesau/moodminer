package com.sevbesau.moodminer.model.api;

public interface API {
  void login(String email, String password, APIListener listener);
  void signup(String email, String password, APIListener listener);
  void token(APIListener listener);
}
