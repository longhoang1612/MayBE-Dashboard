package com.hoanglong.junadminstore.data.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderUpload {
    @SerializedName("Orders")
    @Expose
    private List<Order> orders = null;

    public OrderUpload(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderUpload{" +
                "orders=" + orders +
                '}';
    }
}
