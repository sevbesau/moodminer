package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"activityId", "dayId"})
public class DayActivityCrossRef {
    @NonNull public long activityId;
    @NonNull public long dayId;
    public DayActivityCrossRef(long activityId, long dayId) {
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
