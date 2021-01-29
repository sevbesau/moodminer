package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Entity(tableName = "Activities")
public class Activity {

  @PrimaryKey(autoGenerate = true)
  public long aId;

  public long categoryId;

  @NonNull public String title;

  public String activityDescription;

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

  public Activity(@NonNull String title, String activityDescription) {
    this.title = title;
    this.activityDescription = activityDescription;
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
      .put("description", activityDescription)
      .put("ownerId", ownerId);
  }

  @Override
  public String toString() {
    return "Activity{" +
      "id='" + aId + '\'' +
      ", title='" + title + '\'' +
      ", description='" + activityDescription + '\'' +
      ", userId=" + ownerId +
      '}';
  }
}
