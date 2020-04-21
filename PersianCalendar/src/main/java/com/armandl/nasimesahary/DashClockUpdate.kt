package com.armandl.nasimesahary

import android.content.Intent
import com.armandl.nasimesahary.ui.MainActivity
import com.armandl.nasimesahary.utils.Utils
import com.google.android.apps.dashclock.api.DashClockExtension
import com.google.android.apps.dashclock.api.ExtensionData

class DashClockUpdate : DashClockExtension() {

    override fun onUpdateData(reason: Int) {
        setUpdateWhenScreenOn(true)
        val mainCalendar = Utils.getMainCalendar()
        val jdn = Utils.getTodayJdn()
        val date = Utils.getDateFromJdnOfCalendar(mainCalendar, jdn)
        publishUpdate(ExtensionData().visible(true)
                .icon(Utils.getDayIconResource(date.dayOfMonth))
                .status(Utils.getMonthName(date))
                .expandedTitle(Utils.dayTitleSummary(date))
                .expandedBody(Utils.dateStringOfOtherCalendars(jdn, Utils.getSpacedComma()))
                .clickIntent(Intent(applicationContext, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)))
    }
}
