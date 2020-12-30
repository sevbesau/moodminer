package com.sevbesau.moodminer.model.database;

import android.content.res.TypedArray;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.sevbesau.moodminer.R;

@Entity(tableName = "Categories")
public class Category {

  @NonNull
  @PrimaryKey(autoGenerate = true)
  public Integer id;

  @NonNull
  private String name;

  private String description;
  private Integer imageResource;

  public Category(String name, String description, Integer imageResource) {
    this.name = name;
    this.description = description;
    this.imageResource = imageResource;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public Integer getImageResource() {
    //return imageResource;
    return R.drawable.img_bowling;
  }
}
