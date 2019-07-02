package com.qlvb.vnpt.botttt.schedule.app;

import android.app.Application;

import com.qlvb.vnpt.botttt.schedule.app.di.AppModule;

import dagger.ObjectGraph;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by linhl on 8/24/2018.
 */

public class BoTTTTApplication extends Application{
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        // dagger
        objectGraph = ObjectGraph.create(new AppModule(this));
        objectGraph.inject(this);
        initRealm();
    }
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
