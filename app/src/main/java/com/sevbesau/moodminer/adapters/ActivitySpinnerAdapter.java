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
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class ActivitySpinnerAdapter extends ArrayAdapter<Activity> {

  public ActivitySpinnerAdapter(Context context, List<Activity> activities) {
    super(context, 0, activities);
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
        R.layout.activity_spinner_row, parent, false
      );
    }
    TextView activityTitle = convertView.findViewById(R.id.activity_title);
    Activity currentItem = getItem(position);
    if (currentItem != null) {
      activityTitle.setText(currentItem.title);
    }
    return convertView;
  }


}
