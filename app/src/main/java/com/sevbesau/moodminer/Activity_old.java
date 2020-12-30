package com.sevbesau.moodminer;

import com.sevbesau.moodminer.model.database.Category;

public class Activity_old {
  private final String title;
  private final double duration;
  private final Category category;
  private boolean clicked;


  public Activity_old(String title, double duration, Category category) {
    this.title = title;
    this.duration = duration;
    this.category = category;
    this.clicked = false;
  }

  public Activity_old click() {
    this.clicked = !this.clicked;
    return this;
  }

  public String getTitle() {
    return (this.clicked ? "X " : "") + this.title;
  }
}
