package com.sevbesau.moodminer.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Users")
public class User {

  @PrimaryKey
  @NonNull
  private String email;
  private String refreshToken;
  private String accesToken;

  public static User getUser(JSONObject jsonObject) throws JSONException {
    String email = "";
    String refreshToken = "";
    String accesToken = "";
    if (jsonObject.has("email")) email = jsonObject.getString("email");
    if (jsonObject.has("refreshToken")) refreshToken = jsonObject.getString("refreshToken");
    if (jsonObject.has("accesToken")) accesToken = jsonObject.getString("accesToken");

    User user = new User(email, refreshToken, accesToken);

    return user;
  }

  public User(@NonNull String email, String refreshToken, String accesToken) {
    this.email = email;
    this.refreshToken = refreshToken;
    this.accesToken = accesToken;
  }


  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj != null && obj instanceof User) {
      User that = (User) obj;
      if (this.email == that.email) result = true;
    }
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
      "email='" + email + '\'' +
      ", refreshToken='" + refreshToken + '\'' +
      ", accesToken='" + accesToken + '\'' +
      '}';
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getAccesToken() {
    return accesToken;
  }
  public void setAccesToken(String accesToken) {
    this.accesToken = accesToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
