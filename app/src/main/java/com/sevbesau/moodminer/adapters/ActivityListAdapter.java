package com.sevbesau.moodminer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.database.entities.Activity;
import com.sevbesau.moodminer.model.database.entities.ActivityWithCategories;

import java.util.List;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder> {

  private final LayoutInflater mInflater;
  private List<ActivityWithCategories> mActivities; // Cached copy of activities

  public ActivityListAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
  }

  @Override
  public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.activity_item, parent, false);
    return new ActivityViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(ActivityViewHolder holder, int position) {
    if (mActivities != null) {
      ActivityWithCategories current = mActivities.get(position);
      System.out.println("activity: "+current);
      holder.titleTextView.setText(current.activity.title);
      // TODO allow multiple activities
      if (current.categories.size() > 0) holder.categoryTextView.setText(current.categories.get(0).categoryName);
      holder.descriptionTextView.setText(current.activity.activityDescription);
    } else {
      // Covers the case of data not being ready yet.
      holder.titleTextView.setText("no title");
      holder.categoryTextView.setText("no category");
      holder.descriptionTextView.setText("no description");
    }
  }

  public void setActivities(List<ActivityWithCategories> activities) {
    mActivities = activities;
    notifyDataSetChanged();
  }

  public ActivityWithCategories getActivity(int index) {
    return mActivities.get(index);
  }

  // getItemCount() is called many times, and when it is first called,
  // mWords has not been updated (means initially, it's null, and we can't return null).
  @Override
  public int getItemCount() {
    if (mActivities != null)
      return mActivities.size();
    else return 0;
  }

  class ActivityViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView descriptionTextView;
    private final TextView categoryTextView;

    private ActivityViewHolder(View itemView) {
      super(itemView);
      titleTextView = itemView.findViewById(R.id.titleTextView);
      descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
      categoryTextView = itemView.findViewById(R.id.categoryTextView);
    }
  }
}
