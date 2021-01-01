package com.sevbesau.moodminer.model.database.activities;

import com.sevbesau.moodminer.model.database.DAO;
import com.sevbesau.moodminer.model.database.Populator;

public class ActivityPopulator extends Populator {

  public ActivityPopulator(DAO dao) {
    super(dao);
  }

  String[] activityTitles = {"koken", "lopen", "huiswerk"};
  String[] activityDescriptions = {
    "eten maken voor jezelf of anderen",
    "aan je conditie werken",
    "werken aan je toekomst"
  };

  public void populate() {
    for (int i = 0; i <= activityTitles.length - 1; i++) {
      Activity activity = new Activity(
        activityTitles[i],
        activityDescriptions[i],
        "sociaal"
      );
      mDao.insert(activity);
    }
  }
}
