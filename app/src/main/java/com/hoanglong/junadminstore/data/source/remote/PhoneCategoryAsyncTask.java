package com.hoanglong.junadminstore.data.source.remote;

import android.support.annotation.NonNull;

import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.category.PhoneCategory;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.service.BaseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhoneCategoryAsyncTask {
    private CallBack<List<ItemPhoneCategory>> mCallBack;

    PhoneCategoryAsyncTask(CallBack<List<ItemPhoneCategory>> mCallBack) {
        this.mCallBack = mCallBack;
    }

    public void getCategoryPhone(String typeCategory) {
        Call<PhoneCategory> call = BaseService.getService().getTypeCategory(typeCategory);
        call.enqueue(new Callback<PhoneCategory>() {
            @Override
            public void onResponse(@NonNull Call<PhoneCategory> call,
                                   @NonNull Response<PhoneCategory> response) {
                if (response.body() != null) {
                    mCallBack.getDataSuccess(response.body().getPhoneCategoryList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneCategory> call, @NonNull Throwable t) {
                mCallBack.getDataError(t.getMessage());
            }
        });
    }
}
