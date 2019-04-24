package com.topsports.androidstudy.widget.picker;

import java.text.SimpleDateFormat;

/**
 * Date 2018/12/5
 * Time 17:54
 *
 * @author wentong.chen
 */
public class NumberFormatUtils {


    public static String formatDate(long timeInMillis, String s) {
        SimpleDateFormat sdf = new SimpleDateFormat(s);
        return sdf.format(timeInMillis);
    }
}
