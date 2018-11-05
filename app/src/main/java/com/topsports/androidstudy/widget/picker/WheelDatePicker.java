package com.topsports.androidstudy.widget.picker;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.topsports.androidstudy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WheelDatePicker extends LinearLayout implements WheelPicker.OnItemSelectedListener,
        IDebug, IWheelPicker, IWheelDatePicker, IWheelYearPicker, IWheelMonthPicker,
        IWheelDayPicker {
    private static final SimpleDateFormat SDF =
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
        mPickerYear.setOnItemSelectedListener(this);
        mPickerMonth.setOnItemSelectedListener(this);
        mPickerDay.setOnItemSelectedListener(this);

        setMaximumWidthTextYear();
        mPickerMonth.setMaximumWidthText("00");
        mPickerDay.setMaximumWidthText("00");

//        mTVYear = (TextView) findViewById(R.id.wheel_date_picker_year_tv);
//        mTVMonth = (TextView) findViewById(R.id.wheel_date_picker_month_tv);
//        mTVDay = (TextView) findViewById(R.id.wheel_date_picker_day_tv);

        mYear = mPickerYear.getCurrentYear();
        mMonth = mPickerMonth.getCurrentMonth();
        mDay = mPickerDay.getCurrentDay();
    }

    private void setMaximumWidthTextYear() {
        List years = mPickerYear.getData();
        String lastYear = String.valueOf(years.get(years.size() - 1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastYear.length(); i++)
            sb.append("0");
        mPickerYear.setMaximumWidthText(sb.toString());
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        if (picker.getId() == R.id.wheel_date_picker_year) {
            mYear = (int) data;
            mPickerMonth.setYear(mYear);
            mPickerDay.setYearAndMonth(mYear, mMonth);
        } else if (picker.getId() == R.id.wheel_date_picker_month) {
            mMonth = (int) data;
            mPickerMonth.setYear(mYear);
            mPickerDay.setYearAndMonth(mYear, mMonth);
        }
        mDay = mPickerDay.getCurrentDay();
        if (null != mListener) {
            mListener.onDateSelected(this, mYear, mMonth, mDay);
        }
    }

    @Override
    public void setDebug(boolean isDebug) {
        mPickerYear.setDebug(isDebug);
        mPickerMonth.setDebug(isDebug);
        mPickerDay.setDebug(isDebug);
    }

    @Override
    public int getVisibleItemCount() {
        if (mPickerYear.getVisibleItemCount() == mPickerMonth.getVisibleItemCount() &&
                mPickerMonth.getVisibleItemCount() == mPickerDay.getVisibleItemCount())
            return mPickerYear.getVisibleItemCount();
        throw new ArithmeticException("Can not get visible item count correctly from" +
                "WheelDatePicker!");
    }

    @Override
    public void setVisibleItemCount(int count) {
        mPickerYear.setVisibleItemCount(count);
        mPickerMonth.setVisibleItemCount(count);
        mPickerDay.setVisibleItemCount(count);
    }

    @Override
    public boolean isCyclic() {
        return mPickerYear.isCyclic() && mPickerMonth.isCyclic() && mPickerDay.isCyclic();
    }
    //设置是否循环
    @Override
    public void setCyclic(boolean isCyclic) {
        mPickerYear.setCyclic(isCyclic);
        mPickerMonth.setCyclic(isCyclic);
        mPickerDay.setCyclic(isCyclic);
    }

    @Deprecated
    @Override
    public void setOnItemSelectedListener(WheelPicker.OnItemSelectedListener listener) {
        throw new UnsupportedOperationException("You can not setLoginUser OnItemSelectedListener for" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public int getSelectedItemPosition() {
        throw new UnsupportedOperationException("You can not get position of selected item from" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public void setSelectedItemPosition(int position) {
        throw new UnsupportedOperationException("You can not setLoginUser position of selected item for" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public int getCurrentItemPosition() {
        throw new UnsupportedOperationException("You can not get position of current item from" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public List getData() {
        throw new UnsupportedOperationException("You can not get data source from WheelDatePicker");
    }

    @Deprecated
    @Override
    public void setData(List data) {
        throw new UnsupportedOperationException("You don't need to setLoginUser data source for" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public void setSameWidth(boolean hasSameSize) {
        throw new UnsupportedOperationException("You don't need to setLoginUser same width for" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public boolean hasSameWidth() {
        throw new UnsupportedOperationException("You don't need to setLoginUser same width for" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public void setOnWheelChangeListener(WheelPicker.OnWheelChangeListener listener) {
        throw new UnsupportedOperationException("WheelDatePicker unsupport setLoginUser" +
                "OnWheelChangeListener");
    }

    @Deprecated
    @Override
    public String getMaximumWidthText() {
        throw new UnsupportedOperationException("You can not get maximum width text from" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public void setMaximumWidthText(String text) {
        throw new UnsupportedOperationException("You don't need to setLoginUser maximum width text for" +
                "WheelDatePicker");
    }

    @Deprecated
    @Override
    public int getMaximumWidthTextPosition() {
        throw new UnsupportedOperationException("You can not get maximum width text position" +
                "from WheelDatePicker");
    }

    @Deprecated
    @Override
    public void setMaximumWidthTextPosition(int position) {
        throw new UnsupportedOperationException("You don't need to setLoginUser maximum width text" +
                "position for WheelDatePicker");
    }

    @Override
    public int getSelectedItemTextColor() {
        if (mPickerYear.getSelectedItemTextColor() == mPickerMonth.getSelectedItemTextColor() &&
                mPickerMonth.getSelectedItemTextColor() == mPickerDay.getSelectedItemTextColor())
            return mPickerYear.getSelectedItemTextColor();
        throw new RuntimeException("Can not get color of selected item text correctly from" +
                "WheelDatePicker!");
    }

    @Override
    public void setSelectedItemTextColor(int color) {
        mPickerYear.setSelectedItemTextColor(color);
        mPickerMonth.setSelectedItemTextColor(color);
        mPickerDay.setSelectedItemTextColor(color);
    }

    @Override
    public int getItemTextColor() {
        if (mPickerYear.getItemTextColor() == mPickerMonth.getItemTextColor() &&
                mPickerMonth.getItemTextColor() == mPickerDay.getItemTextColor())
            return mPickerYear.getItemTextColor();
        throw new RuntimeException("Can not get color of item text correctly from" +
                "WheelDatePicker!");
    }

    /**
     *
     * @param color 数据项文本颜色，16位颜色值
     */
    @Override
    public void setItemTextColor(int color) {
        mPickerYear.setItemTextColor(color);
        mPickerMonth.setItemTextColor(color);
        mPickerDay.setItemTextColor(color);
    }

    @Override
    public int getItemTextSize() {
        if (mPickerYear.getItemTextSize() == mPickerMonth.getItemTextSize() &&
                mPickerMonth.getItemTextSize() == mPickerDay.getItemTextSize())
            return mPickerYear.getItemTextSize();
        throw new RuntimeException("Can not get size of item text correctly from" +
                "WheelDatePicker!");
    }

    @Override
    public void setItemTextSize(int size) {
        mPickerYear.setItemTextSize(size);
        mPickerMonth.setItemTextSize(size);
        mPickerDay.setItemTextSize(size);
    }

    @Override
    public int getItemSpace() {
        if (mPickerYear.getItemSpace() == mPickerMonth.getItemSpace() &&
                mPickerMonth.getItemSpace() == mPickerDay.getItemSpace())
            return mPickerYear.getItemSpace();
        throw new RuntimeException("Can not get item space correctly from WheelDatePicker!");
    }

    /**
     *
     * @param space 滚轮选择器数据项之间间距，单位：px
     */
    @Override
    public void setItemSpace(int space) {
        mPickerYear.setItemSpace(space);
        mPickerMonth.setItemSpace(space);
        mPickerDay.setItemSpace(space);
    }

    /**
     *
     * @param hasIndicator 是否有指示器
     */
    @Override
    public void setIndicator(boolean hasIndicator) {
        mPickerYear.setIndicator(hasIndicator);
        mPickerMonth.setIndicator(hasIndicator);
        mPickerDay.setIndicator(hasIndicator);
    }

    @Override
    public boolean hasIndicator() {
        return mPickerYear.hasIndicator() && mPickerMonth.hasIndicator() &&
                mPickerDay.hasIndicator();
    }

    @Override
    public int getIndicatorSize() {
        if (mPickerYear.getIndicatorSize() == mPickerMonth.getIndicatorSize() &&
                mPickerMonth.getIndicatorSize() == mPickerDay.getIndicatorSize())
            return mPickerYear.getIndicatorSize();
        throw new RuntimeException("Can not get indicator size correctly from WheelDatePicker!");
    }

    @Override
    public void setIndicatorSize(int size) {
        mPickerYear.setIndicatorSize(size);
        mPickerMonth.setIndicatorSize(size);
        mPickerDay.setIndicatorSize(size);
    }

    @Override
    public int getIndicatorColor() {
        if (mPickerYear.getCurtainColor() == mPickerMonth.getCurtainColor() &&
                mPickerMonth.getCurtainColor() == mPickerDay.getCurtainColor())
            return mPickerYear.getCurtainColor();
        throw new RuntimeException("Can not get indicator color correctly from WheelDatePicker!");
    }

    @Override
    public void setIndicatorColor(int color) {
        mPickerYear.setIndicatorColor(color);
        mPickerMonth.setIndicatorColor(color);
        mPickerDay.setIndicatorColor(color);
    }

    /**
     *
     * @param hasCurtain 滚轮选择器是否显示幕布
     */
    @Override
    public void setCurtain(boolean hasCurtain) {
        mPickerYear.setCurtain(hasCurtain);
        mPickerMonth.setCurtain(hasCurtain);
        mPickerDay.setCurtain(hasCurtain);
    }

    @Override
    public boolean hasCurtain() {
        return mPickerYear.hasCurtain() && mPickerMonth.hasCurtain() &&
                mPickerDay.hasCurtain();
    }

    @Override
    public int getCurtainColor() {
        if (mPickerYear.getCurtainColor() == mPickerMonth.getCurtainColor() &&
                mPickerMonth.getCurtainColor() == mPickerDay.getCurtainColor())
            return mPickerYear.getCurtainColor();
        throw new RuntimeException("Can not get curtain color correctly from WheelDatePicker!");
    }

    @Override
    public void setCurtainColor(int color) {
        mPickerYear.setCurtainColor(color);
        mPickerMonth.setCurtainColor(color);
        mPickerDay.setCurtainColor(color);
    }

    /**
     *
     * @param hasAtmospheric 滚轮选择器是否有空气感
     */
    @Override
    public void setAtmospheric(boolean hasAtmospheric) {
        mPickerYear.setAtmospheric(hasAtmospheric);
        mPickerMonth.setAtmospheric(hasAtmospheric);
        mPickerDay.setAtmospheric(hasAtmospheric);
    }

    @Override
    public boolean hasAtmospheric() {
        return mPickerYear.hasAtmospheric() && mPickerMonth.hasAtmospheric() &&
                mPickerDay.hasAtmospheric();
    }

    /**
     * 选择器是否设置成曲线样式
     * @return
     */
    @Override
    public boolean isCurved() {
        return mPickerYear.isCurved() && mPickerMonth.isCurved() && mPickerDay.isCurved();
    }

    @Override
    public void setCurved(boolean isCurved) {
        mPickerYear.setCurved(isCurved);
        mPickerMonth.setCurved(isCurved);
        mPickerDay.setCurved(isCurved);
    }

    @Deprecated
    @Override
    public int getItemAlign() {
        throw new UnsupportedOperationException("You can not get item align from WheelDatePicker");
    }

    /**
     *
     * @param align 对齐方式标识值
     *              该值仅能是下列值之一：
     *              {@link WheelPicker#ALIGN_CENTER}
     *              {@link WheelPicker#ALIGN_LEFT}
     */
    @Deprecated
    @Override
    public void setItemAlign(int align) {
        throw new UnsupportedOperationException("You don't need to setLoginUser item align for" +
                "WheelDatePicker");
    }

    @Override
    public Typeface getTypeface() {
        if (mPickerYear.getTypeface().equals(mPickerMonth.getTypeface()) &&
                mPickerMonth.getTypeface().equals(mPickerDay.getTypeface()))
            return mPickerYear.getTypeface();
        throw new RuntimeException("Can not get typeface correctly from WheelDatePicker!");
    }

    /**
     *
     * @param tf 字体对象
     */
    @Override
    public void setTypeface(Typeface tf) {
        mPickerYear.setTypeface(tf);
        mPickerMonth.setTypeface(tf);
        mPickerDay.setTypeface(tf);
    }

    @Override
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


    @Override
    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public Date getCurrentDate() {
        String date = mYear + "-" + mMonth + "-" + mDay;
        try {
            return SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemAlignYear() {
        return mPickerYear.getItemAlign();
    }

    @Override
    public void setItemAlignYear(int align) {
        mPickerYear.setItemAlign(align);
    }

    @Override
    public int getItemAlignMonth() {
        return mPickerMonth.getItemAlign();
    }

    @Override
    public void setItemAlignMonth(int align) {
        mPickerMonth.setItemAlign(align);
    }

    @Override
    public int getItemAlignDay() {
        return mPickerDay.getItemAlign();
    }

    @Override
    public void setItemAlignDay(int align) {
        mPickerDay.setItemAlign(align);
    }

    @Override
    public WheelYearPicker getWheelYearPicker() {
        return mPickerYear;
    }

    @Override
    public WheelMonthPicker getWheelMonthPicker() {
        return mPickerMonth;
    }

    @Override
    public WheelDayPicker getWheelDayPicker() {
        return mPickerDay;
    }

    @Override
    public TextView getTextViewYear() {
        return null;
    }

    @Override
    public TextView getTextViewMonth() {
        return null;
    }

    @Override
    public TextView getTextViewDay() {
        return null;
    }

    /**
     *
     * @param start 开始的年份
     * @param end   结束的年份
     */
    @Override
    public void setYearFrame(int start, int end) {
        mPickerYear.setYearFrame(start, end);
    }

    @Override
    public int getYearStart() {
        return mPickerYear.getYearStart();
    }

    /**
     *
     * @param start 开始的年份
     */
    @Override
    public void setYearStart(int start) {
        mPickerYear.setYearStart(start);
    }

    @Override
    public int getYearEnd() {
        return mPickerYear.getYearEnd();
    }

    @Override
    public void setYearEnd(int end) {
        mPickerYear.setYearEnd(end);
    }

    @Override
    public int getSelectedYear() {
        return mPickerYear.getSelectedYear();
    }

    /**
     *
     * @param year 年份选择器初始化时选中的年份
     */
    @Override
    public void setSelectedYear(int year) {
        mYear = year;
        mPickerYear.setSelectedYear(year);
        mPickerMonth.setYear(mYear);
        mPickerDay.setYearAndMonth(mYear, mMonth);
    }

    @Override
    public int getCurrentYear() {
        return mPickerYear.getCurrentYear();
    }

    @Override
    public int getSelectedMonth() {
        return mPickerMonth.getSelectedMonth();
    }

    @Override
    public void setSelectedMonth(int month) {
        mMonth = month;
        mPickerMonth.setSelectedMonth(month);
        mPickerDay.setYearAndMonth(mYear, month);
//        mPickerDay.setMonth(month);
    }

    @Override
    public int getCurrentMonth() {
        return mPickerMonth.getCurrentMonth();
    }

    @Override
    public int getSelectedDay() {
        return mPickerDay.getSelectedDay();
    }

    @Override
    public void setSelectedDay(int day) {
        mDay = day;
        mPickerDay.setSelectedDay(day);
    }

    @Override
    public int getCurrentDay() {
        return mPickerDay.getCurrentDay();
    }

    /**
     *
     * @param year  年份
     * @param month 月份
     */
    @Override
    public void setYearAndMonth(int year, int month) {
        mYear = year;
        mMonth = month;
        mPickerYear.setSelectedYear(year);
        mPickerMonth.setSelectedMonth(month);
        mPickerDay.setYearAndMonth(year, month);
    }

    @Override
    public int getYear() {
        return getSelectedYear();
    }

    @Override
    public void setYear(int year) {
        mYear = year;
        mPickerYear.setSelectedYear(year);
        mPickerMonth.setYear(year);
        mPickerDay.setYearAndMonth(year, mMonth);
    }

    @Override
    public int getMonth() {
        return getSelectedMonth();
    }

    @Override
    public void setMonth(int month) {
        mMonth = month;
        mPickerMonth.setSelectedMonth(month);
        mPickerDay.setYearAndMonth(mYear, month);
    }

    public interface OnDateSelectedListener {
        void onDateSelected(WheelDatePicker picker, int year, int month, int day);
    }
}