package com.sevbesau.moodminer.activities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sevbesau.moodminer.CategoryListAdapter;
import com.sevbesau.moodminer.model.Model;
import com.sevbesau.moodminer.model.database.categories.Category;
import com.sevbesau.moodminer.R;

import java.util.List;

public class Categories extends AppCompatActivity
  implements View.OnClickListener {

  private RecyclerView mRecyclerView;
  private FloatingActionButton mFloatingActionButton;
  private Model mModel;

  //private ArrayList<Category> mCategoryData;
  //private CategoryAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.categories);

    String[] categoriesList = getResources().getStringArray(R.array.category_titles);
    TypedArray categoryImageResources = getResources().obtainTypedArray(R.array.category_images);
    for(int i=0;i<categoriesList.length;i++){
      System.out.println("image "+categoryImageResources.getResourceId(i, 0));
    }

    mFloatingActionButton = findViewById(R.id.addActivityFAB);
    mFloatingActionButton.setOnClickListener(this);

    final CategoryListAdapter adapter = new CategoryListAdapter(this);
    //adapter = new CategoryAdapter(this, mCategoryData);
    mRecyclerView = findViewById(R.id.recyclerView);
    mRecyclerView.setAdapter(adapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    //mModel = new Model(Categories.this.getApplication());
    mModel = Model.getInstance(Categories.this.getApplication());

    mModel.getCategories().observe(this, new Observer<List<Category>>() {
      @Override
      public void onChanged(@Nullable final List<Category> categories) {
        System.out.println(categories.get(0));
        adapter.setCategories(categories);
      }
    });
  }

  /*
  private void initializeData() {
    // Get the resources from the XML file.
    String[] categoriesList = getResources().getStringArray(R.array.category_titles);
    String[] descriptionsList = getResources().getStringArray(R.array.category_descriptions);
    TypedArray categoryImageResources = getResources().obtainTypedArray(R.array.category_images);

    mCategoryData.clear();
    for(int i=0;i<categoriesList.length;i++){
      mCategoryData.add(new Category(categoriesList[i],descriptionsList[i], categoryImageResources.getResourceId(i, 0)));
    }

    categoryImageResources.recycle();
    mAdapter.notifyDataSetChanged();
  }
   */

  @Override
  public void onClick(View v) {
    // TODO show suggest activity page
  }

}
