package com.topsports.androidstudy.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.topsports.androidstudy.widget.SlideLayout;

/**
 * Date 2018/10/17
 * Time 11:26
 *
 * @author wentong.chen
 */

public class SlideLayoutBehavior extends CoordinatorLayout.Behavior<SlideLayout> {
    private final String TAG = getClass().getSimpleName();

    private int mInitiateTop;

    public SlideLayoutBehavior() {

    }

    public SlideLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, SlideLayout child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        //测量子控件的宽和高 （总高度-当前控件除外的所有头部高度）
        int parentHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        int offset = 0;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if (view != child && view instanceof SlideLayout) {
                offset += ((SlideLayout) view).getTitleHeight();
            }
        }
        child.measure(parentWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(parentHeight - offset, View.MeasureSpec.EXACTLY));
        Logger.d("child " + parent.indexOfChild(child) + " height = " + child.getMeasuredHeight());
        return true;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, SlideLayout child, int layoutDirection) {
        //摆放里面的所有子控件
        parent.onLayoutChild(child, layoutDirection);
        //布局控件
        SlideLayout previorView = getPreviorView(parent, child);
        if (previorView != null) {
            child.offsetTopAndBottom(previorView.getTop() + previorView.getTitleHeight());
        } else {
            child.offsetTopAndBottom(0);
        }
        mInitiateTop = child.getTop();
        Logger.d("child " + parent.indexOfChild(child) + " top = " + mInitiateTop);
        return true;
    }

    private SlideLayout getPreviorView(CoordinatorLayout parent, SlideLayout view) {
        int index = parent.indexOfChild(view);
        for (int i = index - 1; i >= 0; i--) {
            View child = parent.getChildAt(i);
            if (child != view && child instanceof SlideLayout) {
                return (SlideLayout) child;
            }
        }
        return null;
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull SlideLayout child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        //只处理竖直方向的滑动
        boolean scrollVertial = (ViewCompat.SCROLL_AXIS_VERTICAL & axes) != 0;
        if (scrollVertial && child == directTargetChild) {
            Logger.d("可以嵌套滑动");
            return true;
        }
        Logger.d("不可以嵌套滑动");
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout parent, @NonNull SlideLayout child,
                                  @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Logger.d("开始滑动前准备");
        consumed[1] = scroll(mInitiateTop + child.getHeight(), mInitiateTop, dy, child);
        shift(consumed[1], child, parent);
        super.onNestedPreScroll(parent, child, target, dx, dy, consumed, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout parent, @NonNull SlideLayout child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        //开始滑动--
        //控制自己的滑动（设置滑动边界）
        int shift = scroll(mInitiateTop + child.getHeight() - child.getTitleHeight(), mInitiateTop, dyUnconsumed, child);
        shift(shift, child, parent);
        super.onNestedScroll(parent, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    private void shift(int shift, SlideLayout child, CoordinatorLayout parent) {
        if (shift == 0) {
            Logger.d("没有进行联动推动");
            return;
        }
        if (shift > 0) {
            Logger.d("外层判断进行联动网上推动");
            SlideLayout current = child;
            SlideLayout next = getPreviorView(parent, child);
            while (next != null) {
                int shiftOffset = getUpShiftOffset(current, next);
                Logger.d("shiftOffset" + shiftOffset);
                if (shiftOffset < 0) {
                    next.offsetTopAndBottom(shiftOffset);
                    Logger.d("进行联动网上推动");

                }
                current = next;
                next = getPreviorView(parent, current);
            }
        } else {        //往下推
            Logger.d("外层判断进行联动往下推动");
            SlideLayout current = child;
            SlideLayout next = getNextView(parent, child);
            while (next != null) {
                int shiftOffset = getShiftOffset(current, next);
                Logger.d("下推shiftOffset" + shiftOffset);
                if (shiftOffset > 0) {
                    next.offsetTopAndBottom(shiftOffset);
                    Logger.d("进行联动往下推动");

                }
                current = next;
                next = getNextView(parent, current);
            }
        }
    }

    private int getShiftOffset(SlideLayout current, SlideLayout next) {
        Logger.d("current.getTop()" + current.getTop() + "next.getTop()" + next.getTop());
        return current.getTop() + current.getTitleHeight() - next.getTop();
    }

    private int getUpShiftOffset(SlideLayout current, SlideLayout next) {
        Logger.d("current.getTop()" + current.getTop() + "next.getTop()" + next.getTop());
        return current.getTop() - current.getTitleHeight() - next.getTop();
    }

    private SlideLayout getNextView(ViewGroup parent, View child) {
        int index = parent.indexOfChild(child);
        for (int i = index+1; i < parent.getChildCount(); i++) {
            View child1 = parent.getChildAt(i);
            if (child != child1 && child1 instanceof SlideLayout) {
                return (SlideLayout) child1;
            }
        }
        return null;
    }

    private int scroll(int maxValue, int minValue, int dy, SlideLayout child) {
        int offset = getMidValue(maxValue, minValue, child.getTop() - dy) - child.getTop();
        child.offsetTopAndBottom(offset);
        return offset;
    }

    private int getMidValue(int maxValue, int minValue, int value) {
        if (value > maxValue) {
            return maxValue;
        } else if (value < minValue){
            return minValue;
        }
        return value;
    }
}
