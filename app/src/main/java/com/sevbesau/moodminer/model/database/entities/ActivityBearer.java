package com.sevbesau.moodminer.model.database.entities;

public class ActivityBearer {
  public Activity activity;
  public long categoryId;
  public ActivityBearer(Activity activity, long categoryId) {
    this.activity = activity;
    this.categoryId = categoryId;
  }
}
