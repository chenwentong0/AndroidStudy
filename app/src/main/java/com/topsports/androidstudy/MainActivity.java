package com.topsports.androidstudy;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.topsports.androidstudy.activity.BottomSheetBvActivity;
import com.topsports.androidstudy.activity.MyBehaviorActivity;
import com.topsports.androidstudy.activity.PopupActivity;
import com.topsports.androidstudy.base.BaseActivity;

/**
 * Date 2018/10/17
 * Time 09:56
 *
 * @author wentong.chen
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

    }

    public void goBehavior(View view) {
        startActivity(new Intent(this, MyBehaviorActivity.class));
    }

    public void goBottomSheetBehavior(View view) {
        startActivity(new Intent(this, BottomSheetBvActivity.class));
    }

    public void goPopupWindow(View view) {
        startActivity(new Intent(this, PopupActivity.class));
    }
}
