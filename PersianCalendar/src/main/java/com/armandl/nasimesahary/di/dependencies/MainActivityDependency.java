package com.armandl.nasimesahary.di.dependencies;

import com.armandl.nasimesahary.di.scopes.PerActivity;
import com.armandl.nasimesahary.ui.MainActivity;

import javax.inject.Inject;

@PerActivity
public final class MainActivityDependency {
    @Inject
    MainActivity activity;

    @Inject
    public MainActivityDependency() {
    }

    public MainActivity getMainActivity() {
        return activity;
    }
}
