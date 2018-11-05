package com.topsports.androidstudy.popup;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.topsports.androidstudy.R;

/**
 * Date 2018/11/1
 * Time 21:25
 *
 * @author wentong.chen
 */
public class SimplePopupWindow  {

    private final PopupWindow mPopupWindow;

    public SimplePopupWindow(Context context) {
//        View view = LayoutInflater.from(context).inflate(R.layout.popup_simple, null);
//        view.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth(context),
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        setContentView(view);
//        setAnimationStyle(R.style.animTranslate);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_simple, null, false);
        mPopupWindow = new PopupWindow();
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setContentView(view);
        mPopupWindow.setOutsideTouchable(true);

    }

    public void showAtView(View view) {
        mPopupWindow.showAsDropDown(view);
//        mPopupWindow.showAtLocation(view, Gravity.TOP, 0, 100);
    }

    private int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

}
