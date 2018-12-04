package com.hoanglong.junadminstore.data.source;


import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;

import java.util.List;

public interface PhoneDataSourceImp {
    interface remoteDataSource {
        void getPhoneCategory(CallBack<List<ItemPhoneCategory>> callBack, String typeCategory);

        void getPhoneItem(CallBack<List<ItemPhoneProduct>> callBack, String typeCategory);
    }

    interface localDataSource {
    }
}
