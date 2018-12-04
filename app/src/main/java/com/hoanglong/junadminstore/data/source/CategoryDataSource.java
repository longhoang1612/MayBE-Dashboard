package com.hoanglong.junadminstore.data.source;

import com.hoanglong.junadminstore.data.model.category.Category;

import java.util.List;


public interface CategoryDataSource {
    interface remoteDataSource {
    }

    interface localDataSource {
        void getCategory(CallBack<List<Category>> callBack);
    }
}
