package com.sevbesau.moodminer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyActivities extends AppCompatActivity
  implements View.OnClickListener {

    private ActivityListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinkedList<Activity> mActivities;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activities);

        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(this);

        mActivities = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            mActivities.add(new Activity("activity "+i,50, new Category("none")));
        }

        mAdapter = new ActivityListAdapter(this, mActivities);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        initializeData();
    }

    private void initializeData() {

    }

  @Override
  public void onClick(View v) {
      int wordListSize = mActivities.size();
      mActivities.addLast(new Activity("New activity!", 12, new Category("cat1")));
      mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
      mRecyclerView.smoothScrollToPosition(wordListSize);
  }
}
