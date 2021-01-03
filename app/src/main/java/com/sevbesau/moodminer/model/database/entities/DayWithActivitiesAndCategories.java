package com.sevbesau.moodminer.model.database.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class DayWithActivitiesAndCategories {
  @Embedded public Day day;
  @Relation(
    parentColumn = "dayId",
    entityColumn = "activityId",
    associateBy = @Junction(DayActivityCrossRef.class)
  )
  public List<ActivityWithCategories> activities;
}
