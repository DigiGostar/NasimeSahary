package com.armandl.nasimesahary.entities;

import com.armandl.nasimesahary.calendar.PersianDate;

public class PersianCalendarEvent extends AbstractEvent<PersianDate> {
    public PersianCalendarEvent(PersianDate date, String title, boolean holiday) {
        this.date = date;
        this.title = title;
        this.holiday = holiday;
    }
}
