package com.hoanglong.junadminstore.data.model;

import android.support.annotation.StringDef;

import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_CONFIRMED;
import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_DELIVERY;
import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_WAITING_CONFIRM;

@StringDef({ORDER_DELIVERY, ORDER_WAITING_CONFIRM, ORDER_CONFIRMED})
public @interface TypeConfirmOrder {
    String ORDER_DELIVERY = "Đang giao hàng";
    String ORDER_WAITING_CONFIRM = "Đang xác nhận";
    String ORDER_CONFIRMED = "Đã xác nhận đơn hàng";
}
