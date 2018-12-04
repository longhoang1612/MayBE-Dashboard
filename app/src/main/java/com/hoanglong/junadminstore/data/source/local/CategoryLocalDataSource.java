package com.hoanglong.junadminstore.data.source.local;


import com.hoanglong.junadminstore.data.model.category.Category;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.data.source.CategoryDataSource;

import java.util.List;

public class CategoryLocalDataSource implements CategoryDataSource.localDataSource {

    private static CategoryLocalDataSource mInstance;

    public static CategoryLocalDataSource getInstance() {
        if (mInstance == null)
            mInstance = new CategoryLocalDataSource();
        return mInstance;
    }

    private void getDataHomeFormApi(CallBack<List<Category>> mCallBack) {
        new CategoryLocalAsyncTask(mCallBack).execute();
    }

    @Override
    public void getCategory(CallBack<List<Category>> callBack) {
        getDataHomeFormApi(callBack);
    }
}
