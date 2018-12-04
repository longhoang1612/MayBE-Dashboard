package com.hoanglong.junadminstore.screen.dashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.category.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private LayoutInflater mInflater;
    private List<Category> mCategoryList;
    private OnClickCategoryItem mCategoryItem;

    public CategoriesAdapter(List<Category> categoryList, OnClickCategoryItem categoryItem) {
        mCategoryList = categoryList;
        mCategoryItem = categoryItem;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_category_home, viewGroup, false);
        return new CategoryViewHolder(view, viewGroup.getContext(), mCategoryItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        Category category = mCategoryList.get(i);
        categoryViewHolder.bindData(category);
    }

    @Override
    public int getItemCount() {
        return mCategoryList != null ? mCategoryList.size() : 0;
    }

    public interface OnClickCategoryItem {
        void onClickItem(Category category);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_category)
        ImageView mImageCategory;
        @BindView(R.id.text_title_category)
        TextView mTextCategory;
        private Context mContext;
        private OnClickCategoryItem mClickCategoryItem;
        private Category mCategory;

        CategoryViewHolder(@NonNull View itemView, Context context, OnClickCategoryItem onClickCategoryItem) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
            mClickCategoryItem = onClickCategoryItem;
            mImageCategory.setOnClickListener(this);
        }

        void bindData(Category category) {
            if (category == null) {
                return;
            }
            mCategory = category;
            Glide.with(mContext).load(category.getImage()).into(mImageCategory);
            mTextCategory.setText(category.getTitle());
        }

        @Override
        public void onClick(View v) {
            mClickCategoryItem.onClickItem(mCategory);
        }
    }
}
