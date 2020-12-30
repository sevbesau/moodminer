package com.sevbesau.moodminer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sevbesau.moodminer.model.database.categories.Category;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

  private LayoutInflater mInflater;
  private Context mContext;
  private List<Category> mCategories;

  public CategoryListAdapter(Context context) {
    mContext = context;
    mInflater = LayoutInflater.from(context);
  }

  @Override
  public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.category_item, parent, false);
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(CategoryListAdapter.ViewHolder holder, int position) {
    if (mCategories != null) {
      Category current = mCategories.get(position);
      holder.bindTo(current);
    } else {
      holder.bindTo(new Category("no name", "no title", R.drawable.img_golf));
    }
  }

  public void setCategories(List<Category> categories) {
    mCategories = categories;
    notifyDataSetChanged();
  }


  @Override
  public int getItemCount()  {
    if (mCategories != null)
      return mCategories.size();
    else return 0;
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
      mTitleText.setText(currentCategory.name);
      mDescriptionText.setText(currentCategory.description);
      Glide.with(mContext).load(currentCategory.imageResource).into(mCategoryImage);
    }
  }
}
