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
//    private OnConfirmListener confirmListener;
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


    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }


    @Override
    public void onClick(View v) {

    }
}
