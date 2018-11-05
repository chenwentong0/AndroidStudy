package com.topsports.androidstudy.widget.picker;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 月份选择器
 * <p>
 * Picker for Months
 *
 * @author AigeStudio 2016-07-12
 * @version 1
 */
public class WheelMonthPicker extends WheelPicker implements IWheelMonthPicker {
    private static final Map<Integer, List<Integer>> MONTHS = new HashMap<>();
    private final Calendar mCalendar;
    private final Calendar curCalendar;
    private int mYear;
    private int mSelectedMonth;
    public WheelMonthPicker(Context context) {
        this(context, null);
    }

    public WheelMonthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCalendar = Calendar.getInstance();
        curCalendar = Calendar.getInstance();
        updateMonths();
        mSelectedMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        updateSelectedMonth();
    }

    private void updateMonths() {
        mCalendar.set(Calendar.YEAR, mYear);
        int months;
        if (mYear == curCalendar.get(Calendar.YEAR)) {
            months = curCalendar.get(Calendar.MONTH) + 1;
        } else {
            months = 12;
        }
        List<Integer> data = MONTHS.get(months);
        if (null == data) {
            data = new ArrayList<>();
            for (int i = 1; i <= months; i++)
                data.add(i);
            MONTHS.put(months, data);
        }
        super.setData(data);
    }

    private void updateSelectedMonth() {
        setSelectedItemPosition(mSelectedMonth);
    }

    @Override
    public void setData(List data) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelMonthPicker");
    }

    @Override
    public int getSelectedMonth() {
        return getSelectedItemPosition();
    }

    @Override
    public void setSelectedMonth(int month) {
        mSelectedMonth = month;
        updateSelectedMonth();
    }

    @Override
    public int getCurrentMonth() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition())));
    }

    @Override
    public void setYear(int year) {
        this.mYear = year;
        updateMonths();
    }

    @Override
    public int getYear() {
        return mYear;
    }
}