package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Users")
public class User {

  @PrimaryKey
  public int userId;

  @NonNull
  public String email;

  public String refreshToken;
  public String accesToken;

  public boolean synced = false;

  public static User getFromJson(JSONObject jsonObject) throws JSONException {
    String email = null;
    String refreshToken = null;
    String accesToken = null;
    Integer id = -1;
    if (jsonObject.has("email")) email = jsonObject.getString("email");
    if (jsonObject.has("id")) id = Integer.parseInt(jsonObject.getString("id"));
    if (jsonObject.has("refreshToken")) refreshToken = jsonObject.getString("refreshToken");
    if (jsonObject.has("accesToken")) accesToken = jsonObject.getString("accesToken");

    System.out.println("login "+id);

    User user = new User(email, refreshToken, accesToken, id);

    return user;
  }

  public User(@NonNull String email, String refreshToken, String accesToken, Integer userId) {
    this.email = email;
    this.refreshToken = refreshToken;
    this.accesToken = accesToken;
    this.userId = userId;
  }

  public JSONObject toJson() throws JSONException {
    return new JSONObject()
      .put("email", email);
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
      "id='" + userId + '\'' +
      ", synced='" + synced + '\'' +
      ", email='" + email + '\'' +
      ", refreshToken='" + refreshToken + '\'' +
      ", accesToken='" + accesToken + '\'' +
      '}';
  }
}
