package com.sevbesau.moodminer.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Activities")
public class Activity {

  @NonNull
  @PrimaryKey(autoGenerate = true)
  public Integer id;

  @NonNull
  private String title;

  private String description;

  //private Date startTime;
  private Integer duration; // minutes

  enum Frequency { DAILY, WEEKLY, MONTHLY, ANUALLY }
  private Integer frequency;

  @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "categoryId")
  private Integer categoryId;

  public Activity(@NonNull String title, String description) {
    this.title = title;
    this.description = description;
    //this.categoryId = category.id;
  }

  @NonNull
  public String getTitle() {
    return title;
  }
  public void setTitle(@NonNull String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Integer getDuration() {
    return duration;
  }
  public void setDuration(Integer duration) {
    this.duration = duration;
  }
  public Integer getFrequency() {
    return frequency;
  }
  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }
  public Integer getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }
}
