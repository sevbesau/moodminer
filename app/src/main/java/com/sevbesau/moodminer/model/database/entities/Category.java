package com.sevbesau.moodminer.model.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.sevbesau.moodminer.R;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Categories")
public class Category {

  @NonNull
  @PrimaryKey
  public long categoryId;
  public String categoryName;
  public String description;
  @NonNull
  public Integer imageResource;

  public Category(long categoryId, String categoryName, String description, Integer imageResource) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.description = description;
    this.imageResource = imageResource;
  }

  public static Category getFromJson(JSONObject categoryJson) throws JSONException {
    Integer imageResource = categoryJson.getString("image").equals("") ?
      R.drawable.img_golf : Integer.parseInt(categoryJson.getString("image"));
    return new Category(
      categoryJson.getInt("categoryId"),
      categoryJson.getString("name"),
      categoryJson.getString("description"),
      imageResource
    );
  }

  @Override
  public String toString() {
    return "Category{" +
      "name='" + categoryName + '\'' +
      ", description='" + description + '}';
  }
}
