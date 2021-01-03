package com.sevbesau.moodminer.model.database.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithDays {
  @Embedded public User user;
  @Relation(
    parentColumn = "userId",
    entityColumn = "dayOwnerId"
  )
  public List<Day> days;
}
