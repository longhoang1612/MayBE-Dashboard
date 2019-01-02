package com.hoanglong.junadminstore.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeySearch {
    @SerializedName("keySearch")
    @Expose
    private String keySearch;

    public KeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }
}
