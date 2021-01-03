package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Entity(tableName = "Activities")
public class Activity {

  @PrimaryKey(autoGenerate = true)
  public int activityId;

  @NonNull public String title;

  public String description;

  //@ForeignKey(entity = Category.class, parentColumns = "name", childColumns = "categoryName")

  //@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "ownerId")
  public Integer ownerId;

  // stretch goals
  /*
  //private Date startTime;
  public Integer duration; // minutes

  enum Frequency { DAILY, WEEKLY, MONTHLY, ANUALLY }
  public Integer frequency;
   */

  public Activity(@NonNull String title, String description) {
    this.title = title;
    this.description = description;
  }

  public static Activity getFromJson(JSONObject json) throws JSONException {
    Activity newActivity = new Activity(
      json.getString("title"),
      json.getString("description")
    );
    newActivity.ownerId = json.getInt("ownerId");
    return  newActivity;
  }

  public JSONObject toJson(Integer ownerId) throws JSONException {
    return new JSONObject()
      .put("title", title)
      .put("description", description)
      .put("ownerId", ownerId);
  }

  @Override
  public String toString() {
    return "Activity{" +
      "id='" + activityId + '\'' +
      ", title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", userId=" + ownerId +
      '}';
  }
}
