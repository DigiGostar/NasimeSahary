package com.armandl.nasimesahary.ui.calendar.month;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.armandl.nasimesahary.R;
import com.armandl.nasimesahary.di.dependencies.CalendarFragmentDependency;
import com.armandl.nasimesahary.entities.AbstractEvent;
import com.armandl.nasimesahary.entities.DayItem;
import com.armandl.nasimesahary.entities.DeviceCalendarEvent;
import com.armandl.nasimesahary.ui.calendar.CalendarFragmentModel;
import com.armandl.nasimesahary.utils.Utils;

import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> {
    private final int startingDayOfWeek;
    private final int totalDays;
    private final ViewGroup.LayoutParams layoutParams;
    private final DaysPaintResources daysPaintResources;
    private CalendarFragmentDependency calendarFragmentDependency;
    private SparseArray<List<DeviceCalendarEvent>> monthEvents = new SparseArray<>();
    private List<DayItem> days;
    private boolean isArabicDigit;
    private int weekOfYearStart;
    private int weeksCount;
    private Context context;
    private int selectedDay = -1;

    MonthAdapter(CalendarFragmentDependency calendarFragmentDependency, List<DayItem> days,
                 int startingDayOfWeek, int weekOfYearStart, int weeksCount) {
        this.calendarFragmentDependency = calendarFragmentDependency;
        this.startingDayOfWeek = Utils.fixDayOfWeekReverse(startingDayOfWeek);
        totalDays = days.size();
        this.days = days;
        this.weekOfYearStart = weekOfYearStart;
        this.weeksCount = weeksCount;
        this.context = calendarFragmentDependency.getCalendarFragment().getContext();
        initializeMonthEvents(context);
        isArabicDigit = Utils.isArabicDigitSelected();

        layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                context.getResources().getDimensionPixelSize(R.dimen.day_item_size));
        this.daysPaintResources = calendarFragmentDependency.getDaysPaintResources();
    }

    void initializeMonthEvents(Context context) {
        if (Utils.isShowDeviceCalendarEvents()) {
            monthEvents = Utils.readMonthDeviceEvents(context, days.get(0).getJdn());
        }
    }

    void selectDay(int dayOfMonth) {
        int prevDay = selectedDay;
        selectedDay = -1;
        notifyItemChanged(prevDay);

        if (dayOfMonth == -1) return;

        selectedDay = dayOfMonth + 6 + startingDayOfWeek;
        if (Utils.isWeekOfYearEnabled()) {
            selectedDay = selectedDay + selectedDay / 7 + 1;
        }

        notifyItemChanged(selectedDay);
    }

    @NonNull
    @Override
    public MonthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDayView itemDayView = new ItemDayView(parent.getContext(), daysPaintResources);
        itemDayView.setLayoutParams(layoutParams);
        return new ViewHolder(itemDayView);
    }

    private boolean hasDeviceEvents(List<AbstractEvent> dayEvents) {
        for (AbstractEvent event : dayEvents) {
            if (event instanceof DeviceCalendarEvent) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 7 * (Utils.isWeekOfYearEnabled() ? 8 : 7); // days of week * month view rows
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ViewHolder(ItemDayView itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ItemDayView itemDayView = (ItemDayView) v;
            long jdn = itemDayView.getJdn();
            if (jdn == -1) return;

            ViewModelProviders.of(calendarFragmentDependency.getCalendarFragment())
                    .get(CalendarFragmentModel.class).selectDay(jdn);
            MonthAdapter.this.selectDay(itemDayView.getDayOfMonth());
        }

        @Override
        public boolean onLongClick(View v) {
            onClick(v);

            ItemDayView itemDayView = (ItemDayView) v;
            long jdn = itemDayView.getJdn();
            if (jdn == -1) return false;

            calendarFragmentDependency.getCalendarFragment().addEventOnCalendar(jdn);
            return false;
        }

        void bind(int position) {
            int originalPosition = position;
            ItemDayView itemDayView = (ItemDayView) itemView;
            if (Utils.isWeekOfYearEnabled()) {
                if (position % 8 == 0) {
                    int row = position / 8;
                    if (row > 0 && row <= weeksCount) {
                        String weekNumber = Utils.formatNumber(weekOfYearStart + row - 1);
                        itemDayView.setNonDayOfMonthItem(weekNumber,
                                daysPaintResources.weekNumberTextSize);
                        if (Utils.isTalkBackEnabled()) {
                            itemDayView.setContentDescription(
                                    String.format(context.getString(R.string.nth_week_of_year), weekNumber));
                        }

                        itemDayView.setVisibility(View.VISIBLE);
                    } else setEmpty();
                    return;
                }

                position = position - position / 8 - 1;
            }

            if (totalDays < position - 6 - startingDayOfWeek) {
                setEmpty();
            } else if (position < 7) {
                itemDayView.setNonDayOfMonthItem(
                        Utils.getInitialOfWeekDay(Utils.fixDayOfWeek(position)),
                        daysPaintResources.weekDaysInitialTextSize);
                if (Utils.isTalkBackEnabled()) {
                    itemDayView.setContentDescription(String.format(
                            context.getString(R.string.week_days_name_column),
                            Utils.getWeekDayName(Utils.fixDayOfWeek(position))));
                }

                itemDayView.setVisibility(View.VISIBLE);
            } else {
                if (position - 7 - startingDayOfWeek >= 0) {
                    DayItem day = days.get(position - 7 - startingDayOfWeek);
                    List<AbstractEvent> events = Utils.getEvents(day.getJdn(), monthEvents);
                    boolean isHoliday = Utils.isWeekEnd(day.getDayOfWeek()) || Utils.hasAnyHolidays(events);

                    itemDayView.setDayOfMonthItem(day.isToday(), originalPosition == selectedDay,
                            events.size() > 0, hasDeviceEvents(events), isHoliday,
                            isArabicDigit
                                    ? daysPaintResources.arabicDigitsTextSize
                                    : daysPaintResources.persianDigitsTextSize,
                            day.getJdn(), position - 6 - startingDayOfWeek,
                            Utils.getShiftWorkTitle(day.getJdn(), true));

                    itemDayView.setContentDescription(Utils.getA11yDaySummary(context,
                            day.getJdn(), day.isToday(), monthEvents,
                            day.isToday(), false, true));

                    itemDayView.setVisibility(View.VISIBLE);
                } else {
                    setEmpty();
                }

            }
        }

        private void setEmpty() {
            itemView.setVisibility(View.GONE);
        }
    }
}
