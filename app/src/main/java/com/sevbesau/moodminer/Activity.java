package com.sevbesau.moodminer;

public class Activity {
  private final String title;
  private final double duration;
  private final Category category;
  private boolean clicked;


  public Activity(String title, double duration, Category category) {
    this.title = title;
    this.duration = duration;
    this.category = category;
    this.clicked = false;
  }

  public Activity click() {
    this.clicked = !this.clicked;
    return this;
  }

  public String getTitle() {
    return (this.clicked ? "X " : "") + this.title;
  }
}
