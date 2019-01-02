package com.hoanglong.junadminstore.data.model.search;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HistorySearch extends RealmObject {
    @PrimaryKey
    private String mHistorySearch;

    public HistorySearch() {
    }

    public HistorySearch(String historySearch) {
        mHistorySearch = historySearch;
    }

    public String getHistorySearch() {
        return mHistorySearch;
    }

    public void setHistorySearch(String historySearch) {
        mHistorySearch = historySearch;
    }

    @Override
    public String toString() {
        return "HistorySearch{" +
                "mHistorySearch='" + mHistorySearch + '\'' +
                '}';
    }
}
