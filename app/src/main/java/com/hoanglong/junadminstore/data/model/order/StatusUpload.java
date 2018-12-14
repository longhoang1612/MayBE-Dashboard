package com.hoanglong.junadminstore.data.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusUpload {

    @SerializedName("statusOrder")
    @Expose
    private String mStatus;

    public StatusUpload(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
