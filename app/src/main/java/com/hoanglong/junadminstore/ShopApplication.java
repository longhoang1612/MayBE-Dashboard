package com.hoanglong.junadminstore;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ShopApplication extends Application {

    private static ShopApplication sSelf;

    public static ShopApplication self() {
        return sSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSelf = this;
        //Init realm
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build());
    }
}
