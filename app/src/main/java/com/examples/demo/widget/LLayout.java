package com.examples.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.examples.demo.utils.LogText;

public class LLayout extends ViewGroup {
    private boolean onTouchEvent;
    private boolean onInterceptTouchEvent;

    public boolean isOnTouchEvent() {
        return onTouchEvent;
    }

    public void setOnTouchEvent(boolean onTouchEvent) {
        this.onTouchEvent = onTouchEvent;
    }

    public boolean isOnInterceptTouchEvent() {
        return onInterceptTouchEvent;
    }

    public void setOnInterceptTouchEvent(boolean onInterceptTouchEvent) {
        this.onInterceptTouchEvent = onInterceptTouchEvent;
    }

    public LLayout(Context context) {
        super(context);
    }

    public LLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        LogText.i("LLayout:dispatch",ev);
        return super.dispatchTouchEvent(ev);
    }

    // ViewGroup
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogText.i("LLayout:onIntercept",ev);
        return onInterceptTouchEvent;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.d("changed "," [" + changed + "], l = [" + l + "], t = [" + t + "], r = [" + r + "], b = [" + b + "]");
        // 遍历所有子视图
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = getChildAt(i);
//
//            // 获取在onMeasure中计算的视图尺寸
//            int measureHeight = childView.getMeasuredHeight();
//            int measuredWidth = childView.getMeasuredWidth();
//
//            childView.layout(l, 0, measuredWidth, measureHeight);
//
//        }

        View c = getChildAt(0);
        c.layout(l,0,c.getMeasuredWidth(),c.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = 200;
        int h = 200;
        int mw = View.resolveSize(w,widthMeasureSpec);
        int mh = View.resolveSize(h,heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(mw,mh);
    }

    // View
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogText.i("LLayout:onTouchEvent ",event);
        return onTouchEvent;
    }

}

