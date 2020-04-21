package com.armandl.nasimesahary.entities;

import androidx.annotation.NonNull;

import com.armandl.nasimesahary.utils.CalendarType;

public class CalendarTypeItem {
    private final CalendarType type;
    private final String title;

    public CalendarTypeItem(CalendarType type, String title) {
        this.type = type;
        this.title = title;
    }

    public CalendarType getType() {
        return type;
    }

    @NonNull
    public String toString() {
        return title;
    }
}
