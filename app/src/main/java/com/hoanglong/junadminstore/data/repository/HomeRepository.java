package com.hoanglong.junadminstore.data.repository;


import com.hoanglong.junadminstore.data.model.home.NewsFeed;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.data.source.HomeDataSourceImp;
import com.hoanglong.junadminstore.data.source.remote.HomeDataSource;

public class HomeRepository implements HomeDataSourceImp.remoteDataSource
        , HomeDataSourceImp.localDataSource {

    private static HomeRepository sInstance;
    private HomeDataSource mHomeDataSource;

    private HomeRepository(HomeDataSource homeDataSource) {
        mHomeDataSource = homeDataSource;
    }

    public static HomeRepository getInstance(HomeDataSource homeDataSource) {
        if (sInstance == null)
            sInstance = new HomeRepository(homeDataSource);
        return sInstance;
    }

    @Override
    public void getAllItems(CallBack<PhoneProduct> callBack) {
        mHomeDataSource.getAllItems(callBack);
    }

}
