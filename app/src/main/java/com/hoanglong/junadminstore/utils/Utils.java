package com.hoanglong.junadminstore.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.hoanglong.junadminstore.data.model.comment.Comment;
import com.hoanglong.junadminstore.data.model.order.Order;
import com.hoanglong.junadminstore.data.model.order.StatusUpload;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.service.BaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    public static void uploadStatus(String idOrder, String status) {

        StatusUpload statusUpload = new StatusUpload(status);

        Call<Order> call = BaseService.getService().updateStatusOrder(idOrder, statusUpload);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                if (response.body() != null) {
                    Log.d("DONE", "onResponse: " + response.body().getStatusOrder());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                Log.d("DONE", "onFailure: " + t.getMessage());
            }
        });
    }

    public static void deleteProduct(final Context context, final ItemPhoneProduct itemPhoneProduct) {
        Call<ItemPhoneProduct> call = BaseService.getService().deleteProduct(itemPhoneProduct.getId());
        call.enqueue(new Callback<ItemPhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<ItemPhoneProduct> call, @NonNull Response<ItemPhoneProduct> response) {
                Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                Log.d("ABCD", "onResponse: "+response.toString()+":"+itemPhoneProduct.getId());
            }

            @Override
            public void onFailure(@NonNull Call<ItemPhoneProduct> call, @NonNull Throwable t) {
                Toast.makeText(context, "Có lỗi xảy ra" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void deleteComment(final Context context, Comment comment){
        Call<Comment> call = BaseService.getService().deleteComment(comment.getId());
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(@NonNull Call<Comment> call, @NonNull Response<Comment> response) {
                Toast.makeText(context, "Xóa comment của khách hàng", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Comment> call, @NonNull Throwable t) {

            }
        });
    }


}
