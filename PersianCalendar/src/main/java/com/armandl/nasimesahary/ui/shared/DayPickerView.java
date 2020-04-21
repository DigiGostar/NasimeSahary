package com.armandl.nasimesahary.ui.shared;

import com.armandl.nasimesahary.utils.CalendarType;

public interface DayPickerView {
    void setDayJdnOnView(long jdn);

    long getDayJdnFromView();

    CalendarType getSelectedCalendarType();

    void setOnSelectedDayChangedListener(OnSelectedDayChangedListener listener);

    interface OnSelectedDayChangedListener {
        void onSelectedDayChanged(long jdn);
    }
}
