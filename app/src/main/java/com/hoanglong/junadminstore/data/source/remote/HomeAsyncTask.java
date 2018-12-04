package com.hoanglong.junadminstore.data.source.remote;

import android.support.annotation.NonNull;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.data.source.CallBack;
import com.hoanglong.junadminstore.service.BaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeAsyncTask {
    private CallBack<PhoneProduct> mCallBack;

    public HomeAsyncTask(CallBack<PhoneProduct> callBack) {
        mCallBack = callBack;
    }


    public void getHomeApi() {
        Call<PhoneProduct> call = BaseService.getService().getAllPhone();
        call.enqueue(new Callback<PhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<PhoneProduct> call, @NonNull Response<PhoneProduct> response) {
                if (response.body() != null) {
                    mCallBack.getDataSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneProduct> call, @NonNull Throwable t) {
                mCallBack.getDataError(t.getMessage());
            }
        });
    }
}
