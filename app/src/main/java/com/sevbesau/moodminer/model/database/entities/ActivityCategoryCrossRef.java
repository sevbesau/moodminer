package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"activityId", "categoryId"})
public class ActivityCategoryCrossRef {
    @NonNull public long activityId;
    @NonNull public long categoryId;
    public ActivityCategoryCrossRef(long activityId, long categoryId) {
      this.activityId = activityId;
      this.categoryId = categoryId;
    }

  @Override
  public String toString() {
    return "ActivityCategoryCrossRef{" +
      "activityId=" + activityId +
      ", categoryId=" + categoryId +
      '}';
  }
}
