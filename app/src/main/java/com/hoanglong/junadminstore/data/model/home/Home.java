package com.hoanglong.junadminstore.data.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home {
    @SerializedName("NewsFeed")
    @Expose
    private List<NewsFeed> newsFeed = null;

    public List<NewsFeed> getNewsFeed() {
        return newsFeed;
    }

    public void setNewsFeed(List<NewsFeed> newsFeed) {
        this.newsFeed = newsFeed;
    }
}
