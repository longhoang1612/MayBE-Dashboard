package com.hoanglong.junadminstore.data.source.local.Realm;


import com.hoanglong.junadminstore.data.model.search.HistorySearch;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmHistorySearch {
    private static Realm realm = Realm.getDefaultInstance();
    private static final String PRIMARY_KEY = "mHistorySearch";

    public static void addHistorySearch(HistorySearch historySearch) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(historySearch);
        realm.commitTransaction();
    }

    public static HistorySearch getRecentSearch(HistorySearch historySearch) {
        RealmObject realmObject = realm.where(HistorySearch.class).equalTo(PRIMARY_KEY, historySearch.getHistorySearch()).findFirst();
        return (HistorySearch) realmObject;
    }

    public static void deleteRecentSearch(final HistorySearch historySearch) {
        RealmResults<HistorySearch> movies = realm.where(HistorySearch.class).equalTo(PRIMARY_KEY, historySearch.getHistorySearch()).findAll();
        realm.beginTransaction();
        movies.deleteFromRealm(0);
        realm.commitTransaction();
    }

    public static List<HistorySearch> getListHistorySearch() {
        return realm.where(HistorySearch.class).findAll();
    }
}
