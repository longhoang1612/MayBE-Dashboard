package com.hoanglong.junadminstore.data.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemPhoneCategory {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("imageCategory")
    @Expose
    private String mImageCategory;
    @SerializedName("type")
    @Expose
    private String mType;

    public ItemPhoneCategory(String _id, String imageCategory, String type) {
        this._id = _id;
        mImageCategory = imageCategory;
        mType = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImageCategory() {
        return mImageCategory;
    }

    public void setImageCategory(String imageCategory) {
        mImageCategory = imageCategory;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
