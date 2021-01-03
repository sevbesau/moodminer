package com.sevbesau.moodminer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.entities.Category;

import java.util.ArrayList;

public class CategorySpinnerAdapter extends ArrayAdapter<Category> {

  public CategorySpinnerAdapter(Context context, ArrayList<Category> categories) {
    super(context, 0, categories);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return initView(position, convertView, parent);
  }

  @Override
  public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return initView(position, convertView, parent);
  }

  private View initView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(
        R.layout.category_spinner_row, parent, false
      );
    }
    ImageView categoryImage = convertView.findViewById(R.id.categoryImage);
    TextView categoryTitle = convertView.findViewById(R.id.categoryTitle);
    Category currentItem = getItem(position);
    if (currentItem != null) {
      Glide.with(super.getContext()).load(currentItem.imageResource).into(categoryImage);
      categoryTitle.setText(currentItem.categoryName);
    }
    return convertView;
  }


}
