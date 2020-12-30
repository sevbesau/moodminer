package com.sevbesau.moodminer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.Model;
import com.sevbesau.moodminer.model.database.Activity;
import com.sevbesau.moodminer.model.database.Category;

import java.util.List;

public class ActivityAdd extends AppCompatActivity {
  public static final String EXTRA_REPLY_TITLE = "com.sevbesau.moodminer.TITLE_REPLY";
  public static final String EXTRA_REPLY_DESCRIPTION = "com.sevbesau.moodminer.DESCRIPTION_REPLY";

  private EditText mTitleEditText;
  private EditText mDescriptionEditText;
  private Spinner mCategorySpinner;

  private Model mModel;

  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    setContentView(R.layout.activity_add);
    mTitleEditText = findViewById(R.id.TitleEditTextText);
    mDescriptionEditText = findViewById(R.id.DescriptionEditText);
    mCategorySpinner = findViewById(R.id.categorySpinner);

    final String[] categories = {};
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mCategorySpinner.setAdapter(adapter);

    //mModel = new Model(ActivityAdd.this.getApplication());
    mModel = Model.getInstance(ActivityAdd.this.getApplication());

    /*
    mModel.getCategories().observe(this, new Observer<List<Category>>() {
      @Override
      public void onChanged(@Nullable final List<Category> categories) {
        // Update the cached copy of the words in the adapter.
        adapter.clear();
        for (Category category : categories) {
          adapter.add(category.getName());
        }
      }
    });
     */
  }

  public void saveActivity(View view) {
    Intent replyIntent = new Intent();
    if (TextUtils.isEmpty(mTitleEditText.getText())) {
      setResult(RESULT_CANCELED, replyIntent);
    } else {
      String title = mTitleEditText.getText().toString();
      String description = mDescriptionEditText.getText().toString();
      replyIntent.putExtra(EXTRA_REPLY_TITLE, title);
      replyIntent.putExtra(EXTRA_REPLY_DESCRIPTION, description);
      setResult(RESULT_OK, replyIntent);
    }
    finish();
  }

}
