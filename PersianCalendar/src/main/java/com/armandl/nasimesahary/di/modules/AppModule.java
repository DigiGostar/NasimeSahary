package com.armandl.nasimesahary.di.modules;

import com.armandl.nasimesahary.di.scopes.PerActivity;
import com.armandl.nasimesahary.ui.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class AppModule {
    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();
}
