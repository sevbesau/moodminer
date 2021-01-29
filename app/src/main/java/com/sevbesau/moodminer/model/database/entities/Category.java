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
  public long cId;
  public String categoryName;
  public String categoryDescription;
  @NonNull
  public Integer imageResource;

  public Category(long cId, String categoryName, String categoryDescription, Integer imageResource) {
    this.cId = cId;
    this.categoryName = categoryName;
    this.categoryDescription = categoryDescription;
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
      ", description='" + categoryDescription + '}';
  }
}
