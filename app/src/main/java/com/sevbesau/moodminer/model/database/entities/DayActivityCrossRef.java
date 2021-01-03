package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"activityId", "dayId"})
public class DayActivityCrossRef {
    @NonNull public long activityId;
    @NonNull public long dayId;
    public DayActivityCrossRef(Integer activityId, Integer dayId) {
      this.activityId = activityId;
      this.dayId = dayId;
    }

  @Override
  public String toString() {
    return "DayActivityCrossRef{" +
      "activityId=" + activityId +
      ", dayId=" + dayId +
      '}';
  }
}
