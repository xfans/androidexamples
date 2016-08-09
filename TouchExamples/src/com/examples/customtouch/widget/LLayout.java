package com.examples.customtouch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class LLayout extends ViewGroup {
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

        Log.i("LTAG", "LLayout dispatchTouchEvent :" + getAction(ev));
        Log.i("LTAG", "LLayout dispatchTouchEvent default return " + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    private String getAction(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";

            case MotionEvent.ACTION_UP:
                return "ACTION_UP";

            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";

            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            default:
                return "error";
        }

    }

    // ViewGroup
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("LTAG", "LLayout onInterceptTouchEvent :" + getAction(ev));
        Log.i("LTAG", "LLayout onInterceptTouchEvent default return " + super.onInterceptTouchEvent(ev));
        return false;
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
        Log.i("LTAG", "LLayout onTouchEvent " + getAction(event));
        Log.i("LTAG", "LLayout onTouchEvent default return " + super.onTouchEvent(event));
        return false;
    }

}

