package com.topsports.androidstudy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.topsports.androidstudy.R;


/**
 * Created by wentong.chen on 17/5/31.
 */

public abstract class BottomDialog extends Dialog {
    protected boolean touchOutside;

    public BottomDialog(Context context) {
        super(context);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(touchOutside);
        setContentView(getLayoutRes());
        setDialogGravity();
    }

    protected void setDialogGravity() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        window.getAttributes().windowAnimations = R.style.dialogAnim;
    }

    @Override
    protected void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics( dm );
        getWindow().setLayout( dm.widthPixels, getWindow().getAttributes().height );
    }

    public BottomDialog setTouchOutside(boolean touchOutside) {
        this.touchOutside = touchOutside;
        return this;
    }

    protected abstract @LayoutRes int getLayoutRes();
}
