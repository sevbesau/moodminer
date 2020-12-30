package com.sevbesau.moodminer;

/*
public class ActivityListAdapter_old
  extends RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder> {

  class ActivityViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {
      public final TextView activityView;
      final ActivityListAdapter mAdapter;

    public ActivityViewHolder_old(View itemView, ActivityListAdapter adapter) {
          super(itemView);
          activityView = itemView.findViewById(R.id.activity_title);
          this.mAdapter = adapter;
          itemView.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
          int position = getLayoutPosition();
          Activity element = mActivityList.get(position);
          mActivityList.set(position, element.setTitle("clicked"));
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
    View mItemView = mInflater.inflate(R.layout.m, parent, false);
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

*/
