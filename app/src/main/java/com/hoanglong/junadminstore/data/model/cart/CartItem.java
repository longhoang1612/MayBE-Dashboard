package com.hoanglong.junadminstore.data.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {
    @SerializedName("titleItem")
    @Expose
    private String mName;
    @SerializedName("priceItem")
    @Expose
    private String mPrice;
    @SerializedName("quantityItem")
    @Expose
    private int mQuantity;
    @SerializedName("imageItem")
    @Expose
    private String mImage;
    private String mId;

    public CartItem(String name, String price, int quantity, String image) {
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mImage = image;
    }

    public CartItem(String name, String price, int quantity, String image, String id) {
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mImage = image;
        mId = id;
    }

    public CartItem() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
