package com.armandl.nasimesahary.entities;

import com.armandl.nasimesahary.calendar.IslamicDate;

public class IslamicCalendarEvent extends AbstractEvent<IslamicDate> {
    public IslamicCalendarEvent(IslamicDate date, String title, boolean holiday) {
        this.date = date;
        this.title = title;
        this.holiday = holiday;
    }
}
