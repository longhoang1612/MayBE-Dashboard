package com.hoanglong.junadminstore.data.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhoneCategory {

    @SerializedName("Category")
    @Expose
    private
    List<ItemPhoneCategory> mPhoneCategoryList;

    public PhoneCategory(List<ItemPhoneCategory> phoneCategoryList) {
        mPhoneCategoryList = phoneCategoryList;
    }

    public List<ItemPhoneCategory> getPhoneCategoryList() {
        return mPhoneCategoryList;
    }

    public void setPhoneCategoryList(List<ItemPhoneCategory> phoneCategoryList) {
        mPhoneCategoryList = phoneCategoryList;
    }
}
