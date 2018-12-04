package com.hoanglong.junadminstore.data.model.phone_product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhoneProduct {
    @SerializedName("PhoneProduct")
    @Expose
    private List<ItemPhoneProduct> phoneProduct = null;

    public List<ItemPhoneProduct> getPhoneProduct() {
        return phoneProduct;
    }

    public void setPhoneProduct(List<ItemPhoneProduct> phoneProduct) {
        this.phoneProduct = phoneProduct;
    }
}
