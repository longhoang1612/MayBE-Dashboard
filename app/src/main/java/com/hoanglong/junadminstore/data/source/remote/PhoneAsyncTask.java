package com.hoanglong.junadminstore.data.source.remote;

import android.support.annotation.NonNull;

import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.service.BaseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhoneAsyncTask {

    private CallBack<List<ItemPhoneProduct>> mCallBack;

    PhoneAsyncTask(CallBack<List<ItemPhoneProduct>> mCallBack) {
        this.mCallBack = mCallBack;
    }

    void getDataPhone(String typeCategory){
        Call<PhoneProduct> call = BaseService.getService().getItemWithCategory(typeCategory);
        call.enqueue(new Callback<PhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<PhoneProduct> call,
                                   @NonNull Response<PhoneProduct> response) {
                if (response.body() != null) {
                    mCallBack.getDataSuccess(response.body().getPhoneProduct());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneProduct> call, @NonNull Throwable t) {
               mCallBack.getDataError(t.getMessage());
            }
        });
    }
}
