package com.sevbesau.moodminer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sevbesau.moodminer.model.database.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

  private ArrayList<Category> mCategoryData;
  private Context mContext;

  public CategoryAdapter(Context context, ArrayList<Category> categoryData) {
    this.mCategoryData = categoryData;
    this.mContext = context;
  }

  @Override
  public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false));
  }

  @Override
  public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
    Category currentCategory = mCategoryData.get(position);
    holder.bindTo(currentCategory);
  }

  @Override
  public int getItemCount()  {
    return mCategoryData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView mCategoryImage;
    private TextView mTitleText;
    private TextView mDescriptionText;

    ViewHolder(View itemView) {
      super(itemView);
      mCategoryImage = itemView.findViewById(R.id.categoryImage);
      mTitleText = itemView.findViewById(R.id.categoryTitle);
      mDescriptionText = itemView.findViewById(R.id.categoryDescription);
    }

    void bindTo(Category currentCategory) {
      mTitleText.setText(currentCategory.getName());
      mDescriptionText.setText(currentCategory.getDescription());
      Glide.with(mContext).load(currentCategory.getImageResource()).into(mCategoryImage);
    }
  }
}
