package com.armandl.nasimesahary.di.dependencies;

import com.armandl.nasimesahary.di.scopes.PerFragment;
import com.armandl.nasimesahary.ui.MainActivity;
import com.armandl.nasimesahary.ui.calendar.CalendarFragment;
import com.armandl.nasimesahary.ui.calendar.month.DaysPaintResources;

import javax.inject.Inject;

@PerFragment
public final class CalendarFragmentDependency {
    private final DaysPaintResources daysPaintResources;

    @Inject
    CalendarFragment calendarFragment;

    @Inject
    public CalendarFragmentDependency(MainActivity activity) {
        daysPaintResources = new DaysPaintResources(activity);
    }

    public CalendarFragment getCalendarFragment() {
        return calendarFragment;
    }

    public DaysPaintResources getDaysPaintResources() {
        return daysPaintResources;
    }
}
