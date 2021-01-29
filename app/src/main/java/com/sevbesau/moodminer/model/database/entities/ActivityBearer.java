package com.sevbesau.moodminer.model.database.entities;

import java.util.List;

public class ActivityBearer {
  public Activity activity;
  public List<Long> categoryIds;
  public ActivityBearer(Activity activity, List<Long> categoryIds) {
    this.activity = activity;
    this.categoryIds = categoryIds;
  }
}
