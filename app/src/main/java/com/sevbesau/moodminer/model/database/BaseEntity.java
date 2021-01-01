package com.sevbesau.moodminer.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BaseEntity {
  @NonNull
  @PrimaryKey()
  public Integer id;
}
