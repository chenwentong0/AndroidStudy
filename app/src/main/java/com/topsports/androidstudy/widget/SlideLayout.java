package com.topsports.androidstudy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.topsports.androidstudy.R;
import com.topsports.androidstudy.adapter.SimpleTextAdapter;
import com.topsports.androidstudy.bean.SlideLayoutData;
import com.topsports.androidstudy.behavior.SlideLayoutBehavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date 2018/10/17
 * Time 10:13
 *
 * @author wentong.chen
 */
public class SlideLayout extends FrameLayout implements CoordinatorLayout.AttachedBehavior {
    private static final String TAG = SlideLayout.class.getSimpleName();
    private TextView mTvTitle;
    private RecyclerView mRecyclerView;
    private int mTitleBg;
    private String mTitleStr;
    private int mTitleHeight;
    private SimpleTextAdapter mTextAdapter;
    private String[] mStrings = new String[]{"苍老师", "苍老师","苍老师","苍老师",
            "苍老师","苍老师","苍老师"};

    public SlideLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_slide_layout, this, true);
        mTvTitle = view.findViewById(R.id.tv_title);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlideLayout);
        mTitleBg = ta.getColor(R.styleable.SlideLayout_sl_title_bg, 0);
        mTitleStr = ta.getString(R.styleable.SlideLayout_sl_title_str);
        mTvTitle.setText(mTitleStr);
        mTvTitle.setBackgroundColor(mTitleBg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mTextAdapter = new SimpleTextAdapter();
        mRecyclerView.setAdapter(mTextAdapter);
        ta.recycle();
        SlideLayoutData slideLayoutData = new SlideLayoutData();
        slideLayoutData.title = "标题";
        slideLayoutData.stringList = Arrays.asList(mStrings);
        setList(slideLayoutData);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            mTitleHeight = mTvTitle.getMeasuredHeight();
        }
    }

    public void setList(SlideLayoutData slideLayoutData) {
        mTextAdapter.setNewData(slideLayoutData.stringList);
        mTvTitle.setText(slideLayoutData.title);
    }

    public int getTitleHeight() {
        int measuredHeight = findViewById(R.id.tv_title).getMeasuredHeight();
        Log.d(TAG, " measuredHeight = " + measuredHeight);
        return measuredHeight;
    }

    @NonNull
    @Override
    public CoordinatorLayout.Behavior getBehavior() {
        return new SlideLayoutBehavior();
    }
}
