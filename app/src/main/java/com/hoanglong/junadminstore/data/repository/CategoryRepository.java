package com.hoanglong.junadminstore.data.repository;


import com.hoanglong.junadminstore.data.model.category.Category;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.data.source.CategoryDataSource;
import com.hoanglong.junadminstore.data.source.local.CategoryLocalDataSource;

import java.util.List;

public class CategoryRepository implements CategoryDataSource.localDataSource, CategoryDataSource.remoteDataSource {
    private static CategoryRepository sInstance;
    private CategoryLocalDataSource mCategoryLocalDataSource;

    private CategoryRepository(CategoryLocalDataSource categoryLocalDataSource) {
        mCategoryLocalDataSource = categoryLocalDataSource;
    }

    public static CategoryRepository getInstance(CategoryLocalDataSource categoryLocalDataSource) {
        if (sInstance == null)
            sInstance = new CategoryRepository(categoryLocalDataSource);
        return sInstance;
    }

    @Override
    public void getCategory(CallBack<List<Category>> callBack) {
        mCategoryLocalDataSource.getCategory(callBack);
    }
}
