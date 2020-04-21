package com.armandl.nasimesahary.di.dependencies;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.armandl.nasimesahary.MainApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AppDependency {
    private final SharedPreferences sharedPreferences;

    @Inject
    public AppDependency(MainApplication app) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
