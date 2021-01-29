package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.sevbesau.moodminer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Entity(tableName = "Days")
public class Day {

  @PrimaryKey(autoGenerate = true)
  public long dayId;
  public float score;
  @NonNull
  public long dayOwnerId;
  @NonNull
  public Integer day;
  @NonNull
  public Integer month;
  @NonNull
  public Integer year;

  public Day(long dayOwnerId, Integer day, Integer month, Integer year) {
    this.dayOwnerId = dayOwnerId;
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /*
  public static Day getFromJson(JSONObject categoryJson) throws JSONException {
    Integer imageResource = categoryJson.getString("image").equals("") ?
      R.drawable.img_golf : Integer.parseInt(categoryJson.getString("image"));
    return new Day(
      categoryJson.getString("name"),
      categoryJson.getString("description"),
      imageResource,
      0
    );
  }

   */

  @Override
  public String toString() {
    return "Day{" +
      "dayId=" + dayId +
      ", score=" + score +
      ", date=" + day +
      "/" + month +
      "/" + year +
      '}';
  }
}
