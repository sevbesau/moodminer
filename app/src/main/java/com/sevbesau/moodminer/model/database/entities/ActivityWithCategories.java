package com.sevbesau.moodminer.model.database.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;


import java.util.List;


public class ActivityWithCategories {
  @Embedded
  public Activity activity;
  @Relation(
    parentColumn = "activityId",
    entityColumn = "categoryId",
    associateBy = @Junction(ActivityCategoryCrossRef.class)
  )
  public List<Category> categories;

  @Override
  public String toString() {
    return "ActivityWithCategories{" +
      "activity=" + activity +
      ", categories=" + categories +
      '}';
  }
}
