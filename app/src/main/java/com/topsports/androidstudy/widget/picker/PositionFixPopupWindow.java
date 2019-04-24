/*
 * *
 *  * 通联数据机密
 *  * --------------------------------------------------------------------
 *  * 通联数据股份公司版权所有 © 2013-2017
 *  *
 *  * 注意：本文所载所有信息均属于通联数据股份公司资产。本文所包含的知识和技术概念均属于
 *  * 通联数据产权，并可能由中国、美国和其他国家专利或申请中的专利所覆盖，并受商业秘密或
 *  * 版权法保护。
 *  * 除非事先获得通联数据股份公司书面许可，严禁传播文中信息或复制本材料。
 *  *
 *  * DataYes CONFIDENTIAL
 *  * --------------------------------------------------------------------
 *  * Copyright © 2013-2017 DataYes, All Rights Reserved.
 *  *
 *  * NOTICE: All information contained herein is the property of DataYes
 *  * Incorporated. The intellectual and technical concepts contained herein are
 *  * proprietary to DataYes Incorporated, and may be covered by China, U.S. and
 *  * Other Countries Patents, patents in process, and are protected by trade
 *  * secret or copyright law.
 *  * Dissemination of this information or reproduction of this material is
 *  * strictly forbidden unless prior written permission is obtained from DataYes.
 *
 */

package com.topsports.androidstudy.widget.picker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


/**
 * 修复7.0系统位置不对的BUG
 * Created by shenen.gao on 2017/6/30.
 */
public class PositionFixPopupWindow extends PopupWindow {

    public PositionFixPopupWindow() {
        super();
    }

    public PositionFixPopupWindow(Context context) {
        super(context);
    }

    public PositionFixPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
    }

    public PositionFixPopupWindow(View contentView) {
        super(contentView);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        showAsDropDown(anchor, xoff, yoff, Gravity.TOP | Gravity.START);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        // 7.0 以下或者高度为WRAP_CONTENT, 默认显示
        if (Build.VERSION.SDK_INT < 24 || getHeight() == ViewGroup.LayoutParams.WRAP_CONTENT) {
            super.showAsDropDown(anchor, xoff, yoff, gravity);
        } else {
            if (getContentView().getContext() instanceof Activity) {
                Activity activity = (Activity) getContentView().getContext();
                int screenHeight;
                // 获取屏幕真实高度, 减掉虚拟按键的高度
                screenHeight = OsUtils.getContentHeight(activity);
                int[] location = new int[2];
                // 获取控件在屏幕的位置
                anchor.getLocationOnScreen(location);
                // 算出popwindow最大高度
                int maxHeight = screenHeight - location[1] - anchor.getHeight();
                // popupwindow  有具体的高度值，但是小于anchor下边缘与屏幕底部的距离， 正常显示
                if(getHeight() > 0 && getHeight() < maxHeight){
                    super.showAsDropDown(anchor, xoff, yoff, gravity);
                }else {
                    // match_parent 或者 popwinddow的具体高度值大于anchor下边缘与屏幕底部的距离， 都设置为最大可用高度
                    setHeight(maxHeight);
                    super.showAtLocation(anchor, gravity, xoff, location[1] + anchor.getHeight());
                }

            } else {
                super.showAsDropDown(anchor, xoff, yoff, gravity);
            }
        }

    }

}
