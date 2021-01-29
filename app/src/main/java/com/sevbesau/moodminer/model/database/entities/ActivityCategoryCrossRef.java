package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"aId", "cId"})
public class ActivityCategoryCrossRef {
  public long aId;
  public long cId;
  public ActivityCategoryCrossRef(long aId, long cId) {
    this.aId = aId;
    this.cId = cId;
  }

  @Override
  public String toString() {
    return "ActivityCategoryCrossRef{" +
      "activityId=" + aId +
      ", categoryId=" + cId +
      '}';
  }
}
