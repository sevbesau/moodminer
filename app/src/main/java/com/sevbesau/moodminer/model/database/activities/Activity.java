package com.sevbesau.moodminer.model.database.activities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.sevbesau.moodminer.model.database.BaseEntity;
import com.sevbesau.moodminer.model.database.categories.Category;
import com.sevbesau.moodminer.model.database.users.User;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Activities")
public class Activity extends BaseEntity {

  @NonNull
  public String title;

  public String description;

  //private Date startTime;
  public Integer duration; // minutes

  enum Frequency { DAILY, WEEKLY, MONTHLY, ANUALLY }
  public Integer frequency;

  @ForeignKey(entity = Category.class, parentColumns = "name", childColumns = "categoryName")
  public String categoryName;

  @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
  public Integer userId;

  public Activity(@NonNull String title, String description, String categoryName) {
    this.title = title;
    this.description = description;
    this.categoryName = categoryName;
  }

  public static Activity getFromJson(JSONObject json) throws JSONException {
    Activity newActivity = new Activity(
      json.getString("title"),
      json.getString("description"),
      json.getString("categoryName")
    );
    newActivity.userId = json.getInt("userId");
    return  newActivity;
  }

  public JSONObject toJson(Integer userId) throws JSONException {
    return new JSONObject()
      .put("title", title)
      .put("description", description)
      .put("categoryName", categoryName)
      .put("userId", userId);
  }

  @Override
  public String toString() {
    return "Activity{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", categoryName=" + categoryName +
      ", userId=" + userId +
      '}';
  }
}
