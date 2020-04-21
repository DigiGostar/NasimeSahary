package com.armandl.nasimesahary.ui.preferences.interfacecalendar

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.armandl.nasimesahary.R
import com.armandl.nasimesahary.di.dependencies.MainActivityDependency
import com.armandl.nasimesahary.ui.preferences.interfacecalendar.calendarsorder.CalendarPreferenceDialog
import com.armandl.nasimesahary.utils.Utils
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FragmentInterfaceCalendar : PreferenceFragmentCompat() {

    @Inject
    lateinit var mainActivityDependency: MainActivityDependency

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        AndroidSupportInjection.inject(this)

        addPreferencesFromResource(R.xml.preferences_interface_calendar)

        val switchPreference = findPreference("showDeviceCalendarEvents") as SwitchPreferenceCompat

        switchPreference.setOnPreferenceChangeListener { _, _ ->
            if (ActivityCompat.checkSelfPermission(mainActivityDependency.mainActivity, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                Utils.askForCalendarPermission(mainActivityDependency.mainActivity)
                switchPreference.isChecked = false
            } else {
                switchPreference.isChecked = !switchPreference.isChecked
            }
            false
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        if (preference?.key == "calendars_priority") {
            val fragmentManager = fragmentManager
            if (fragmentManager != null) {
                CalendarPreferenceDialog().show(fragmentManager, "CalendarPreferenceDialog")
            }
            return true
        }
        return super.onPreferenceTreeClick(preference)
    }
}
