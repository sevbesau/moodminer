package com.sevbesau.moodminer.model.database.categories;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.BaseEntity;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Categories")
public class Category extends BaseEntity {

  @NonNull
  public String name;

  public String description;
  public Integer imageResource;

  public Category(String name, String description, Integer imageResource) {
    this.name = name;
    this.description = description;
    this.imageResource = imageResource;
  }

  public static Category getFromJson(JSONObject categoryJson) throws JSONException {
    Integer imageResource = categoryJson.getString("image").equals("") ?
      R.drawable.img_golf : Integer.parseInt(categoryJson.getString("image"));
    return new Category(
      categoryJson.getString("name"),
      categoryJson.getString("description"),
      imageResource
    );
  }

  @Override
  public String toString() {
    return "Category{" +
      "name='" + name + '\'' +
      ", description='" + description + '}';
  }
}
