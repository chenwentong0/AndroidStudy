package com.topsports.androidstudy.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.topsports.androidstudy.R;
import com.topsports.androidstudy.widget.picker.WheelDatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wentong.chen on 17/6/2.
 */

public class SetAgeDialog extends BottomDialog implements View.OnClickListener {
    private OnClickListener cancelListener;
    private OnConfirmListener confirmListener;
    private WheelDatePicker date_picker;
    private TextView tv_age;
    private int mYear, mMonth, mDay, mAge;
    private Calendar mCalendar;

    public SetAgeDialog(Context context) {
        super(context);
        mCalendar = Calendar.getInstance();
    }

    public SetAgeDialog(Context context, int year, int month, int day) {
        super(context);
        mCalendar = Calendar.getInstance();
        mYear = year;
        mMonth = month;
        mDay = day;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setDimAmount(0);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        date_picker = (WheelDatePicker) findViewById(R.id.date_picker);
        tv_age = (TextView) findViewById(R.id.tv_age);
        if (mYear == 0 || mMonth == 0 || mDay == 0) {
            tv_age.setText(String.format("%s岁", 0));
            date_picker.setSelectedYear(mCalendar.get(Calendar.YEAR));
            date_picker.setSelectedMonth(mCalendar.get(Calendar.MONTH)); //设置月份的时候记得+1
            date_picker.setSelectedDay(mCalendar.get(Calendar.DAY_OF_MONTH));
        } else {
            mAge = caculateAge(mYear, mMonth, mDay);
            tv_age.setText(String.format("%s岁", mAge));
            date_picker.setSelectedYear(mYear);
            date_picker.setSelectedMonth(mMonth - 1); //设置月份的时候记得+1
            date_picker.setSelectedDay(mDay);
        }
        date_picker.setDateItemUnit(":月:");
        date_picker.setCurved(false);
        //        date_picker.setCurtain(true);  //设置帘子
        date_picker.setIndicator(true);//设置分割线
        date_picker.setIndicatorColor(Color.parseColor("#eeeeee"));
        date_picker.setAtmospheric(true);   //设置模糊效果
        //        date_picker.setCyclic(true);//设置是否循环
        date_picker.setIndicatorSize(2);
        date_picker.setItemSpace(20);
        date_picker.setItemTextColor(Color.GRAY);
        date_picker.setOnDateSelectedListener(new WheelDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(WheelDatePicker picker, int year, int month, int day) {
                mAge = caculateAge(year, month, day);
                tv_age.setText(String.format("%s岁", mAge));
            }
        });

    }

    private int caculateAge(int aYear, int aMonth, int aDay) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int age;
        if (month < aYear || (month == aMonth && day <= aDay)) {
            age = year - aYear + 1;
        } else {
            age = year - aYear;
        }
        return age;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_set_age;
    }

    public SetAgeDialog setCancelListener(DialogInterface.OnClickListener listener) {
        this.cancelListener = listener;
        return this;
    }

    public SetAgeDialog setOnConfirmListener(OnConfirmListener listener) {
        this.confirmListener = listener;
        return this;
    }

    public interface OnConfirmListener {
        void confirmDate(int year, int month, int day, int age, Date date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                if (cancelListener != null) {
                    cancelListener.onClick(this, 0);
                }
                dismiss();
                break;
            case R.id.tv_confirm:
                if (confirmListener != null && date_picker != null) {
                    confirmListener.confirmDate(date_picker.getCurrentYear(), date_picker.getCurrentMonth(),
                            date_picker.getCurrentDay(), caculateAge(date_picker.getCurrentYear(), date_picker.getCurrentMonth(),
                                    date_picker.getCurrentDay()), date_picker.getCurrentDate());
                }
                dismiss();
                break;
        }
    }

}
