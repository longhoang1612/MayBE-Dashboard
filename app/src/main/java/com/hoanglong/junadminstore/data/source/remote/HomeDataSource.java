package com.hoanglong.junadminstore.data.source.remote;


import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.data.source.HomeDataSourceImp;

public class HomeDataSource implements HomeDataSourceImp.localDataSource
        ,HomeDataSourceImp.remoteDataSource {

    private static HomeDataSource mInstance;

    public static HomeDataSource getInstance() {
        if (mInstance == null)
            mInstance = new HomeDataSource();
        return mInstance;
    }

    private void getNewsFeedFromApi(CallBack<PhoneProduct> callBack) {
        new HomeAsyncTask(callBack).getHomeApi();
    }

    @Override
    public void getAllItems(CallBack<PhoneProduct> callBack) {
        getNewsFeedFromApi(callBack);
    }
}
