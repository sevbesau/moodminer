package com.sevbesau.moodminer.model.database.populators;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.AppDAO;
import com.sevbesau.moodminer.model.database.entities.Category;

public class CategoryPopulator extends Populator {

  public CategoryPopulator(AppDAO dao) {
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
        i,
        categoryTitles[i],
        categoryDescriptions[i],
        R.drawable.img_running
      );
      mDao.insertCategory(category);
      System.out.println("populated category: "+category);
    }
  }
}
