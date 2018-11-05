package com.topsports.androidstudy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.topsports.androidstudy.R;


/**
 * Created by wentong.chen on 2018/11/2.
 */

public class BaseConfirmDialog extends Dialog {

    private View.OnClickListener onConfirmListener;
    private View.OnClickListener onCancelListener;
    private String mTitle;
    private String mContent;

    public BaseConfirmDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BaseConfirmDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_base);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.black_transparent)));
        setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.CENTER);
        window.getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        window.getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;
//        TextView tvConfirm = (TextView) findViewById(R.id.tv_confirm);
//        TextView tvCancel = (TextView) findViewById(R.id.tv_cancel);
//        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
//        TextView tvContent = (TextView) findViewById(R.id.tv_content);
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//                if (onCancelListener != null) {
//                    onCancelListener.onClick(v);
//                }
//            }
//        });
//        tvConfirm.setOnClickListener(onConfirmListener);
//        tvTitle.setText(mTitle);
//        tvContent.setText(mContent);

    }

    public BaseConfirmDialog setOnConfirmListener(View.OnClickListener listener) {
        onConfirmListener = listener;
        return this;
    }

    public BaseConfirmDialog setOnCancelListener(View.OnClickListener listener) {
        onCancelListener = listener;
        return this;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }
}