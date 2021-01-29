package com.sevbesau.moodminer.model.database.populators;

import com.sevbesau.moodminer.model.database.AppDAO;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.ActivityCategoryCrossRef;
import com.sevbesau.moodminer.model.database.entities.Category;

import java.util.List;

public class ActivityPopulator extends Populator {

  public ActivityPopulator(AppDAO dao) {
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
        activityDescriptions[i]
      );
      activity.aId = i;
      activity.ownerId = 0;
      mDao.insertActivity(activity);
      List<Category> categories = mDao.getSyncAllCategories();
      System.out.println("populated activity: "+activity);
      mDao.insertActivityCategoryCrossRef(new ActivityCategoryCrossRef(
        activity.aId, categories.get(0).cId
      ));
    }
  }
}
