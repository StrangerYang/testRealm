package com.example.testrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
 * @author Admin
 */
public class MyApplication extends Application {

    public static RealmConfiguration config;

    @Override
    public void onCreate() {
        super.onCreate();


        Realm.init(this);

        config = new RealmConfiguration.Builder()
                .name("myRealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);


        //配置默认的数据库
        //setDefaultConfiguration(new RealmConfiguration.Builder().build());
//        RealmConfiguration configuration = new RealmConfiguration.Builder().build();


    }
}
