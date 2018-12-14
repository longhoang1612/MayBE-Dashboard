package com.hoanglong.junadminstore.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hoanglong.junadminstore.data.model.order.Order;
import com.hoanglong.junadminstore.data.model.order.StatusUpload;
import com.hoanglong.junadminstore.service.BaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    public static void uploadStatus(String idOrder,String status) {

        StatusUpload statusUpload = new StatusUpload(status);

        Call<Order> call = BaseService.getService().updateStatusOrder(idOrder,statusUpload);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                if (response.body() != null) {
                    Log.d("DONE", "onResponse: "+ response.body().getStatusOrder());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                Log.d("DONE", "onFailure: " + t.getMessage());
            }
        });
    }
}
