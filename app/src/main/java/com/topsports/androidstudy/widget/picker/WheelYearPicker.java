package com.topsports.androidstudy.widget.picker;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wentong.chen on 2018/11/29.
 */

public class WheelYearPicker extends WheelPicker {
    private int mYearStart = 1900, mYearEnd = 3000;
    private int mYear;
    public WheelYearPicker(Context context) {
        super(context);
    }

    public WheelYearPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
    }

    public void initDefault() {
        mYear = WheelDatePicker.mDefaultYear;
        setMinMax(WheelDatePicker.MIN_YEAR, WheelDatePicker.MAX_YEAR);
    }


    public void setMinValue(int minYear) {
        this.mYearStart = minYear;
        updateYear();
        setSelectYear(mYear);
    }

    public void setMaxValue(int maxYear) {
        this.mYearEnd = maxYear;
        updateYear();
        setSelectYear(mYear);
    }

    public void setMinMax(int min, int max) {
        this.mYearStart = min;
        this.mYearEnd = max;
        updateYear();
        setSelectYear(mYear);
    }

    public void setSelectYear(int year) {
        mYear = year;
        setSelectedItemPosition(year - mYearStart > getData().size() - 1 ?
                getData().size() -1 : year - mYearStart, false);
    }

    public int getSelectYear() {
        return getSelectYear(getSelectedItemPosition());
    }

    public int getSelectYear(int position) {
        if (position < getData().size()) {
            mYear = Integer.valueOf(getData().get(getSelectedItemPosition()).toString());
        }
        return mYear;
    }

    @Override
    public void setSelectedItemPosition(int position, boolean anim) {
        mYear = getSelectYear(position);
        super.setSelectedItemPosition(position, anim);
    }

    private void updateYear() {
        List<Integer> list = new ArrayList<>();
        for (int i = mYearStart; i <= mYearEnd; i++) {
            list.add(i);
        }
        updateData(list);
    }

    public void updateData(List<Integer> years) {
        super.setData(years);
    }
}
