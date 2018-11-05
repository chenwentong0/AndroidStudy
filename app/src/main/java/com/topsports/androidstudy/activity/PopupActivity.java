package com.topsports.androidstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.topsports.androidstudy.R;
import com.topsports.androidstudy.dialog.BaseConfirmDialog;
import com.topsports.androidstudy.dialog.SetAgeDialog;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Date 2018/11/1
 * Time 21:21
 *
 * @author wentong.chen
 */
public class PopupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        LinearLayout llPopup = findViewById(R.id.ll_popup);
        llPopup.setOnClickListener(this);
        mEditText = findViewById(R.id.edit);
    }

    @Override
    public void onClick(View v) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
////
////        PopupWindowCompact popupWindow = new PopupWindowCompact(this);
////        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_simple, null);
////        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
////        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
////        popupWindow.setContentView(contentView);
////        popupWindow.setOutsideTouchable(true);
////        popupWindow.setFocusable(true);
////        popupWindow.setBackgroundDrawable(new BitmapDrawable());
////        popupWindow.showAsDropDown(v);
        SetAgeDialog setAgeDialog = new SetAgeDialog(this, 2006, 6, 22);
        setAgeDialog.setOnConfirmListener(new SetAgeDialog.OnConfirmListener() {
            @Override
            public void confirmDate(int year, int month, int day, int age, Date date) {
                //TODO 设置生日成功
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(date);
            }
        })
                .show();
//        BaseConfirmDialog baseConfirmDialog = new BaseConfirmDialog(this);
//        baseConfirmDialog.show();

    }
}
