package com.sevbesau.moodminer.model.database;

import androidx.room.Embedded;

import com.sevbesau.moodminer.model.database.activities.Activity;
import com.sevbesau.moodminer.model.database.activities.ActivityBearer;
import com.sevbesau.moodminer.model.database.categories.Category;
import com.sevbesau.moodminer.model.database.categories.CategoryBearer;

public class ActivityWithCategory {
  @Embedded
  public ActivityBearer activity;

  @Embedded
  public CategoryBearer category;

  @Override
  public String toString() {
    return "ActivityWithCategory{" +
      "activity=" + activity+
      ", category=" + category+
      '}';
  }
}
