package com.sevbesau.moodminer.model.database.categories;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.DAO;
import com.sevbesau.moodminer.model.database.Populator;

public class CategoryPopulator extends Populator {

  public CategoryPopulator(DAO dao) {
    super(dao);
  }

  String[] categoryTitles = {
    "lichaamsbeweging",
    "ontspanning",
    "creativiteit",
    "uitdaging",
    "sociaal",
    "rust"
  };
  String[] categoryDescriptions = {
    "je lichaam moe maken",
    "dingen doen die je weer opladen",
    "out of the box",
    "daag jezelf uit",
    "spreek eens af",
    "'niks' doen"
  };

  public void populate() {
    for (int i = 0; i <= categoryTitles.length - 1; i++) {
      Category category = new Category(
        categoryTitles[i],
        categoryDescriptions[i],
        R.drawable.img_running,
        i
      );
      mDao.insert(category);
    }
  }
}
