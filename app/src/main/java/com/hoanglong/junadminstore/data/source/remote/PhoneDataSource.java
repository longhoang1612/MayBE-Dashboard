package com.hoanglong.junadminstore.data.source.remote;


import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.data.source.PhoneDataSourceImp;

import java.util.List;

public class PhoneDataSource implements PhoneDataSourceImp.remoteDataSource
        , PhoneDataSourceImp.localDataSource {

    private static PhoneDataSource mInstance;

    public static PhoneDataSource getInstance() {
        if (mInstance == null)
            mInstance = new PhoneDataSource();
        return mInstance;
    }

    private void getDataCategoryFromApi(CallBack<List<ItemPhoneCategory>> callBack, String typeCategory) {
        new PhoneCategoryAsyncTask(callBack).getCategoryPhone(typeCategory);
    }

    private void getPhoneFromApi(CallBack<List<ItemPhoneProduct>> callBack, String typeCategory) {
        new PhoneAsyncTask(callBack).getDataPhone(typeCategory);
    }

    @Override
    public void getPhoneCategory(CallBack<List<ItemPhoneCategory>> callBack,String typeCategory) {
        getDataCategoryFromApi(callBack,typeCategory);
    }

    @Override
    public void getPhoneItem(CallBack<List<ItemPhoneProduct>> callBack,String typeCategory) {
        getPhoneFromApi(callBack,typeCategory);
    }
}
