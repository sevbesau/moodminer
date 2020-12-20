package com.sevbesau.moodminer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ActivityListAdapter
  extends RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder> {

  class ActivityViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {
      public final TextView activityView;
      final ActivityListAdapter mAdapter;

      public ActivityViewHolder(View itemView, ActivityListAdapter adapter) {
          super(itemView);
          activityView = itemView.findViewById(R.id.activity_title);
          this.mAdapter = adapter;
          itemView.setOnClickListener(this);
      }
      @Override
      public void onClick(View v) {
          int mPosition = getLayoutPosition();
          Activity element = mActivityList.get(mPosition);
          mActivityList.set(mPosition, element.click());
          mAdapter.notifyDataSetChanged();
      }
  }

  private final LinkedList<Activity> mActivityList;
  private LayoutInflater mInflater;

  public ActivityListAdapter(Context context, LinkedList<Activity> activityList) {
    mInflater = LayoutInflater.from(context);
    this.mActivityList = activityList;
  }

  @NonNull
  @Override
  public ActivityListAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View mItemView = mInflater.inflate(R.layout.activity_item, parent, false);
    return new ActivityViewHolder(mItemView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull ActivityListAdapter.ActivityViewHolder holder, int position) {
    Activity mCurrent = mActivityList.get(position);
    holder.activityView.setText(mCurrent.getTitle());
  }

  @Override
  public int getItemCount() {
    return mActivityList.size();
  }
}
