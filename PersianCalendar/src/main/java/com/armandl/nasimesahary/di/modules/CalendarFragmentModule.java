package com.armandl.nasimesahary.di.modules;

import com.armandl.nasimesahary.di.scopes.PerChildFragment;
import com.armandl.nasimesahary.ui.calendar.dialogs.SelectDayDialog;
import com.armandl.nasimesahary.ui.calendar.dialogs.ShiftWorkDialog;
import com.armandl.nasimesahary.ui.calendar.month.MonthFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CalendarFragmentModule {
    @PerChildFragment
    @ContributesAndroidInjector(modules = MainChildFragmentModule.class)
    abstract MonthFragment monthFragmentInjector();

    @PerChildFragment
    @ContributesAndroidInjector
    abstract SelectDayDialog selectDayDialogInjector();

    @PerChildFragment
    @ContributesAndroidInjector
    abstract ShiftWorkDialog shiftWorkDialogInjector();
}
