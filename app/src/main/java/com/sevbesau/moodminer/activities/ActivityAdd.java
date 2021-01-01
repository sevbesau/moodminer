package com.sevbesau.moodminer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.sevbesau.moodminer.CategorySpinnerAdapter;
import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.Model;
import com.sevbesau.moodminer.model.database.categories.Category;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
  public static final String EXTRA_REPLY_TITLE = "com.sevbesau.moodminer.TITLE_REPLY";
  public static final String EXTRA_REPLY_DESCRIPTION = "com.sevbesau.moodminer.DESCRIPTION_REPLY";
  public static final String EXTRA_REPLY_CATEGORY = "com.sevbesau.moodminer.CATEGORY";

  private EditText mTitleEditText;
  private EditText mDescriptionEditText;
  private Spinner mCategorySpinner;

  private ArrayList<Category> mCategories;
  private CategorySpinnerAdapter mAdapter;
  private Category mSelectedCategory;

  private Model mModel;

  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    setContentView(R.layout.activity_add);
    mTitleEditText = findViewById(R.id.TitleEditTextText);
    mDescriptionEditText = findViewById(R.id.DescriptionEditText);

    mCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
    mCategorySpinner.setOnItemSelectedListener(this);

    mCategories = new ArrayList<>();
    mAdapter = new CategorySpinnerAdapter(this, mCategories);
    mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesSpinnerModel);


    mModel = Model.getInstance(ActivityAdd.this.getApplication());

    mModel.getCategories().observe(this, new Observer<List<Category>>() {
      @Override
      public void onChanged(@Nullable final List<Category> categories) {
        // Update the cached copy of the words in the adapter.
        mCategories.clear();
        for (Category category : categories) {
          System.out.println("spinner add "+category.toString());
          mCategories.add(category);
        }
        mCategorySpinner.setAdapter(mAdapter);
      }
    });
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    mSelectedCategory = (Category) parent.getItemAtPosition(position);
    System.out.println("spinner "+mSelectedCategory.id+mSelectedCategory.toString());
  }

  @Override
  public void onNothingSelected(AdapterView<?> arg0) {}

  public void saveActivity(View view) {
    Intent replyIntent = new Intent();
    if (TextUtils.isEmpty(mTitleEditText.getText())) {
      setResult(RESULT_CANCELED, replyIntent);
    } else {
      String title = mTitleEditText.getText().toString();
      String description = mDescriptionEditText.getText().toString();
      replyIntent.putExtra(EXTRA_REPLY_TITLE, title);
      replyIntent.putExtra(EXTRA_REPLY_DESCRIPTION, description);
      replyIntent.putExtra(EXTRA_REPLY_CATEGORY, mSelectedCategory.name);
      setResult(RESULT_OK, replyIntent);
    }
    finish();
  }

}
