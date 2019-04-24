package com.topsports.androidstudy.widget.picker;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.topsports.androidstudy.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * wentong.chen
 * 2018.11.5
 */
public class WheelDatePicker extends LinearLayout implements
        IDebug, WheelPicker.OnWheelChangeListener {
    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = 3000;
    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;
    public static final int MIN_DAY = 1;
    public static final int MAX_DAY = 31;
    public static final int mDefaultYear = Calendar.getInstance().get(Calendar.YEAR);
    public static final int mDefaultMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    public static final int mDefaultDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    public static final SimpleDateFormat SDF =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private WheelYearPicker mPickerYear;
    private WheelMonthPicker mPickerMonth;
    private WheelDayPicker mPickerDay;

    private OnDateSelectedListener mListener;

//    private TextView mTVYear, mTVMonth, mTVDay;

    private int mYear, mMonth, mDay;

    public WheelDatePicker(Context context) {
        this(context, null);
    }

    public WheelDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_wheel_date_picker, this);

        mPickerYear = (WheelYearPicker) findViewById(R.id.wheel_date_picker_year);
        mPickerMonth = (WheelMonthPicker) findViewById(R.id.wheel_date_picker_month);
        mPickerDay = (WheelDayPicker) findViewById(R.id.wheel_date_picker_day);
        mPickerYear.setOnWheelChangeListener(this);
        mPickerMonth.setOnWheelChangeListener(this);
        mPickerDay.setOnWheelChangeListener(this);
//        setDebug(true);
        setMaximumWidthTextYear();
        mPickerMonth.setMaximumWidthText("00");
        mPickerDay.setMaximumWidthText("00");

        Logger.d(getClass().getSimpleName() + " mYear = " + mYear + " mMonth = " + mMonth + " mDay = " + mDay);

    }

    public void setMinDate(String date) {
        boolean isEmpty = TextUtils.isEmpty(date);
        if (isEmpty) {
            initDafault();
            return;
        }
        String[] split = date.split("-");
        if (split.length < 3) {
            return;
        }
        int year = Integer.valueOf(split[0]);
        int month = Integer.valueOf(split[1]);
        int day = Integer.valueOf(split[2]);
        mPickerYear.setMinValue(year);
        mPickerMonth.setMinValue(year, month);
        mPickerDay.setMinValue(year, month, day);

        mYear = mPickerYear.getSelectYear();
        mMonth = mPickerMonth.getSelectedMonth();
        mDay = mPickerDay.getSelectDay();

    }

    public void initDafault() {
        mPickerYear.initDefault();
        mPickerMonth.initDefault();
        mPickerDay.initDefault();
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        mMonth  = Calendar.getInstance().get(Calendar.MONTH) + 1;
        mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public void setMaxDate(String date) {
        boolean isEmpty = TextUtils.isEmpty(date);
        if (isEmpty) {
            initDafault();
            return;
        }
        String[] split = date.split("-");
        if (split.length < 3) {
            return;
        }
        int year = Integer.valueOf(split[0]);
        int month = Integer.valueOf(split[1]);
        int day = Integer.valueOf(split[2]);

        mPickerYear.setMaxValue(year);
        mPickerMonth.setMaxValue(year, month);
        mPickerDay.setMaxValue(year, month, day);
        mYear = mPickerYear.getSelectYear();
        mMonth = mPickerMonth.getSelectedMonth();
        mDay = mPickerDay.getSelectDay();
    }

    private void setMaximumWidthTextYear() {
        List years = mPickerYear.getData();
        String lastYear = String.valueOf(years.get(years.size() - 1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastYear.length(); i++) {
            sb.append("0");
        }
        mPickerYear.setMaximumWidthText(sb.toString());
    }


    @Override
    public void setDebug(boolean isDebug) {
        mPickerYear.setDebug(isDebug);
        mPickerMonth.setDebug(isDebug);
        mPickerDay.setDebug(isDebug);
    }


    public String getCurDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(mPickerYear.getSelectYear(),mPickerMonth.getSelectedMonth() - 1,mPickerDay.getSelectDay());
        return NumberFormatUtils.formatDate(instance.getTimeInMillis(), "yyyy-MM-dd");
    }

    @Override
    public void onWheelScrolled(WheelPicker wheelPicker, int offset) {

    }

    @Override
    public void onWheelSelected(WheelPicker picker, int position) {
        Object data = picker.getData().get(position);
        mYear = mPickerYear.getSelectYear();
        mDay = mPickerDay.getSelectDay();
        mMonth = mPickerMonth.getSelectedMonth();
        if (picker.getId() == R.id.wheel_date_picker_year) {
            mYear = Integer.valueOf(data.toString());
            mPickerMonth.setYear(mYear);
            mPickerDay.setYearMonth(mYear, mMonth);
        } else if (picker.getId() == R.id.wheel_date_picker_month) {
            mMonth = Integer.valueOf(data.toString());
            mPickerDay.setYearMonth(mYear, mMonth);
        } else if (picker.getId() == R.id.wheel_date_picker_day) {
            mDay = Integer.valueOf(data.toString());
        }
        System.out.println(picker.toString() + " data " + data.toString() + " position " + position);
        System.out.println("mYear" + mYear + " month " + mMonth + " mDay " + mDay);
        if (null != mListener) {
            mListener.onDateSelected(this, mYear, mMonth, mDay);
        }
    }

    @Override
    public void onWheelScrollStateChanged(WheelPicker wheelPicker, int state) {

    }

    public int getCurrentYear() {
        return mPickerYear.getSelectYear();
    }

    public int getCurrentMonth() {
        return mPickerMonth.getSelectedMonth();
    }

    public int getCurrentDay() {
        return mPickerDay.getSelectDay();
    }

    public void setDateItemUnit(String dateItemUnit) {
        if (TextUtils.isEmpty(dateItemUnit)) {
            return;
        }
        //设置日期单位
        String[] split = dateItemUnit.split(":");
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                mPickerYear.setDateItemUnit(split[i]);
            } else if (i == 1) {
                mPickerMonth.setDateItemUnit(split[i]);
            } else if (i == 2) {
                mPickerDay.setDateItemUnit(split[i]);
            }
        }
    }


    public interface OnDateSelectedListener {
        void onDateSelected(WheelDatePicker picker, int year, int month, int day);
    }
}