package com.armandl.nasimesahary.di.modules;

import com.armandl.nasimesahary.di.scopes.PerFragment;
import com.armandl.nasimesahary.ui.about.AboutFragment;
import com.armandl.nasimesahary.ui.about.DeviceInformationFragment;
import com.armandl.nasimesahary.ui.calendar.CalendarFragment;
import com.armandl.nasimesahary.ui.compass.CompassFragment;
import com.armandl.nasimesahary.ui.converter.ConverterFragment;
import com.armandl.nasimesahary.ui.preferences.PreferencesFragment;
import com.armandl.nasimesahary.ui.preferences.interfacecalendar.FragmentInterfaceCalendar;
import com.armandl.nasimesahary.ui.preferences.interfacecalendar.calendarsorder.CalendarPreferenceDialog;
import com.armandl.nasimesahary.ui.preferences.locationathan.FragmentLocationAthan;
import com.armandl.nasimesahary.ui.preferences.locationathan.location.GPSLocationDialog;

import net.androgames.level.LevelFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//import com.armandl.nasimesahary.ui.reminder.EditReminderDialog;
//import com.armandl.nasimesahary.ui.reminder.ReminderFragment;

@Module
public abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = CalendarFragmentModule.class)
    abstract CalendarFragment calendarFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract PreferencesFragment settingsFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract CompassFragment compassFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract LevelFragment levelFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract AboutFragment aboutFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract DeviceInformationFragment deviceInfoFragmentInjector();

//    @PerFragment
//    @ContributesAndroidInjector
//    abstract ReminderFragment reminderFragmentInjector();
//
//    @PerFragment
//    @ContributesAndroidInjector
//    abstract EditReminderDialog editReminderFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract ConverterFragment converterFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract FragmentLocationAthan fragmentLocationAthanInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract FragmentInterfaceCalendar fragmentInterfaceCalendarInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract CalendarPreferenceDialog calendarPreferenceDialogInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract GPSLocationDialog gpsLocationDialogInjector();
}
