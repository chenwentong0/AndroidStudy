package com.topsports.androidstudy.widget.picker;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wentong.chen on 2018/11/29.
 */

public class WheelMonthPicker extends WheelPicker {
    private Calendar mCalendar = Calendar.getInstance();
    private int mStartMonth = 1, mEndMonth = 12;
    private int mYear;
    private HashMap<String, List<String>> MONTHS = new HashMap<>();
    private int mMinYear = 1900;
    private int mMinMonth;
    private int mMaxYear;
    private int mMaxMonth;
    private int mMonth;

    public WheelMonthPicker(Context context) {
        super(context);
    }

    public WheelMonthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
    }

    public void initDefault() {

        mYear = WheelDatePicker.mDefaultYear;
        mMonth = WheelDatePicker.mDefaultMonth;
        mCalendar.set(Calendar.YEAR, mYear);
        initMinMaxValue(WheelDatePicker.MIN_YEAR, WheelDatePicker.MIN_MONTH,
                WheelDatePicker.MAX_YEAR, WheelDatePicker.MAX_MONTH);
    }

    private void initMinMaxValue(int minYear, int minMonth, int maxYear, int maxMonth ) {
        this.mMinYear = minYear;
        this.mMinMonth = minMonth;
        this.mMaxYear = maxYear;
        this.mMaxMonth = maxMonth;
        updateMonth();
        setYearAndMonth(mYear, mMonth);
    }

    public void setMinValue(int minYear, int minMonth) {
        this.mMinYear = minYear;
        this.mMinMonth = minMonth;
        mMaxYear = mMaxYear < minYear ? minYear : mMaxYear;
        updateMonth();
        setYearAndMonth(mYear, mMonth);
    }

    public void setMaxValue(int maxYear, int maxMonth) {
        this.mMaxYear = maxYear;
        this.mMaxMonth = maxMonth;
        mMinYear = mMinYear > maxYear ? maxYear : mMinYear;
        updateMonth();
        setYearAndMonth(mYear, mMonth);
    }

    public void setYear(int year) {
        setYearAndMonth(year, mMonth);
    }

    public void setYearAndMonth(int year, int month) {
        this.mYear = year;
        this.mMonth = month;
        updateMonth();
        setSelectedItemPosition(month - mStartMonth >= getData().size() ?
                getData().size() - 1 : month - mStartMonth, false);
    }

    public int getSelectedMonth() {
        return getSelectedMonth(getSelectedItemPosition());
    }

    public int getSelectedMonth(int position) {
        if (position < getData().size()) {
            mMonth = Integer.valueOf(getData().get(getSelectedItemPosition()).toString());
        }
        return mMonth;
    }

    @Override
    public void setSelectedItemPosition(int position, boolean anim) {
        mMonth = getSelectedMonth(position);
        super.setSelectedItemPosition(position, anim);
    }

    public void updateMonth() {
        mCalendar.set(Calendar.YEAR, mYear);
        mStartMonth = 1;
        if (mMinYear == mYear) {
            mStartMonth = mMinMonth;
        }
        if (mMaxYear == mYear && mYear != Calendar.getInstance().get(Calendar.YEAR) && mMaxMonth < mEndMonth) {
            mEndMonth = mMaxMonth;
        }
        String monthStr = mYear + "-" + mStartMonth + "-" + mEndMonth;
        List<String> data = MONTHS.get(monthStr);
        if (null == data) {
            data = new ArrayList<>();
            for (int i = mStartMonth; i <= mEndMonth; i++) {
                data.add(i < 10 ? "0" + i : "" + i);
            }
            MONTHS.put(monthStr, data);
        }
        super.setData(data);
    }
}
