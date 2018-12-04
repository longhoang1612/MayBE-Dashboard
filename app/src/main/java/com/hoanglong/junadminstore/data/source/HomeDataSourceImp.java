package com.hoanglong.junadminstore.data.source;


import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;

public interface HomeDataSourceImp {
    interface remoteDataSource {
        void getAllItems(CallBack<PhoneProduct> callBack);
    }

    interface localDataSource {
    }
}
