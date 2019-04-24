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

public class WheelDayPicker extends WheelPicker {
    private Calendar mCalendar = Calendar.getInstance();
    private int mStartDay = 1, mEndDay = 31;
    private int mYear;
    private int mMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private HashMap<String, List<String>> DAYS = new HashMap<>();
    private int mMinYear = 1900;
    private int mMinMonth;
    private int mMaxYear;
    private int mMaxMonth;
    private int mMinDay;
    private int mMaxDay;
    private int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    public WheelDayPicker(Context context) {
        super(context);
    }

    public WheelDayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
    }

    public void initDefault() {
        mYear = WheelDatePicker.mDefaultYear;
        mMonth = WheelDatePicker.mDefaultMonth;
        mDay = WheelDatePicker.mDefaultDay;
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.MONTH, mMonth - 1);
        initMinMaxValue(WheelDatePicker.MIN_YEAR, WheelDatePicker.MIN_MONTH, WheelDatePicker.MIN_DAY,
                WheelDatePicker.MAX_YEAR, WheelDatePicker.MAX_MONTH, WheelDatePicker.MAX_DAY);
    }

    private void initMinMaxValue(int minYear, int minMonth, int minDay, int maxYear, int maxMonth, int maxDay) {
        this.mMinYear = minYear;
        this.mMinMonth = minMonth;
        this.mMinDay = minDay;
        this.mMaxYear = maxYear;
        this.mMaxMonth = maxMonth;
        this.mMaxDay = maxDay;
        updateDay();
        setYearMonthDay(mYear, mMonth, mDay);
    }

    public void setMinValue(int minYear, int minMonth, int minDay) {
        this.mMinYear = minYear;
        this.mMinMonth = minMonth;
        this.mMinDay = minDay;
        mYear = mYear < minYear ? minYear : mYear;
        mMaxYear = mMaxYear < minYear ? minYear : mMaxYear;
        updateDay();
        setYearMonthDay(mYear, mMonth, mDay);
    }

    public void setMaxValue(int maxYear, int maxMonth, int maxDay) {
        this.mMaxYear = maxYear;
        this.mMaxMonth = maxMonth;
        this.mMaxDay = maxDay;
        mMinYear = mMinYear > maxYear ? maxYear : mMinYear;
        mYear = mYear > maxYear ? maxYear : mYear;
        updateDay();
        setYearMonthDay(mYear, mMonth, mDay);
    }

    public void setYear(int year) {
        setYearMonth(year, mMonth);
    }

    public void setYearMonth(int year, int month) {
        setYearMonthDay(year, month, mDay);
    }

    public void setYearMonthDay(int year, int month, int day) {
        this.mYear = year;
        this.mMonth = month;
        mDay = day;
        updateDay();
        setSelectedItemPosition(day - mStartDay >= getData().size() ?
                getData().size() -1 : day - mStartDay, false);
    }

    public int getSelectDay() {
        return getSelectDay(getSelectedItemPosition());
    }

    public int getSelectDay(int position) {
        if (position < getData().size()) {
            mDay = Integer.valueOf(getData().get(getSelectedItemPosition()).toString());
        }
        return mDay;
    }

    @Override
    public void setSelectedItemPosition(int position, boolean anim) {
        mDay = getSelectDay(position);
        super.setSelectedItemPosition(position,anim);
    }

    public void updateDay() {
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.MONTH, mMonth - 1);
        mStartDay = 1;
//        if (mYear == Calendar.getInstance().get(Calendar.YEAR) && mMonth == Calendar.getInstance().get(Calendar.MONTH) + 1) {
//            mEndDay = mCalendar.get(Calendar.DAY_OF_MONTH);
//        } else {
//
//        }
        //获取指定日期的当月总天数
        mEndDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (mMonth == 2 && mYear % 4 == 0) {
            mEndDay = 29;
        } else if (mMonth == 2){
            mEndDay = 28;
        }
        if (mMinYear == mYear && mMinMonth == mMonth && mMinDay > 0) {
            mStartDay = mMinDay;
        }
        if (mMaxYear == mYear && mMaxMonth == mMonth && mMaxDay > 0 && mMaxDay < mEndDay) {
            mEndDay = mMaxDay;
        }
        String dayStr = (mEndDay - mStartDay) + "-" + mStartDay + "-" + mEndDay;
        List<String> data = DAYS.get(dayStr);
        if (null == data) {
            data = new ArrayList<>();
            for (int i = mStartDay; i <= mEndDay; i++) {
                data.add(i < 10 ? "0" + i : "" + i);
            }
            DAYS.put(dayStr, data);
        }
        System.out.println("mYear = "  + mYear + "mMonth = " + mMonth + " 天数 ；" + data.size());
        super.setData(data);
    }
}
