package com.mobiledev.realmdatabasesample.utils;

import android.app.Application;

import com.mobiledev.realmdatabasesample.db.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Manu on 11/6/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}