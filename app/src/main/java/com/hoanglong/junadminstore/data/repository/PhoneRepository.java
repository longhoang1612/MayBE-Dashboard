package com.hoanglong.junadminstore.data.repository;


import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.data.source.PhoneDataSourceImp;
import com.hoanglong.junadminstore.data.source.remote.PhoneDataSource;

import java.util.List;

public class PhoneRepository implements PhoneDataSourceImp.localDataSource
        , PhoneDataSourceImp.remoteDataSource {
    private static PhoneRepository sInstance;
    private PhoneDataSource mPhoneDataSource;

    private PhoneRepository(PhoneDataSource phoneDataSource) {
        mPhoneDataSource = phoneDataSource;
    }

    public static PhoneRepository getInstance(PhoneDataSource phoneDataSource) {
        if (sInstance == null)
            sInstance = new PhoneRepository(phoneDataSource);
        return sInstance;
    }

    @Override
    public void getPhoneCategory(CallBack<List<ItemPhoneCategory>> callBack, String typeCategory) {
        mPhoneDataSource.getPhoneCategory(callBack,typeCategory);
    }

    @Override
    public void getPhoneItem(CallBack<List<ItemPhoneProduct>> callBack, String typeCategory) {
        mPhoneDataSource.getPhoneItem(callBack,typeCategory);
    }
}
