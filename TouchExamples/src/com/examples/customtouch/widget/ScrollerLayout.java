package com.examples.customtouch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zhu on 2016/8/7.
 */
public class ScrollerLayout extends ViewGroup {
    private static final String TAG = "ScrollerLayout";
    private Scroller mScroller;
    private int mLastX, mLastY;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    boolean mIntercept = false;
    int index = 0;

    public ScrollerLayout(Context context) {
        this(context, null);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIntercept = false;
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaY) > Math.abs(deltaX) && Math.abs(deltaY) > mTouchSlop) {
                    mIntercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        mLastX = x;
        mLastY = y;
        Log.d(TAG, "mIntercept：" + mIntercept + " ev:" + ev.getAction());
        return mIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mVelocityTracker.addMovement(ev);
        Log.d(TAG, "onTouchEvent：" + ev.getAction());
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_MOVE:

                if (!mIntercept) {
                    int deltaX = x - mLastX;
                    int deltaY = y - mLastY;
                    if (Math.abs(deltaY) > Math.abs(deltaX) && Math.abs(deltaY) > mTouchSlop) {
                        mIntercept = true;
                    }
                }
                if (mIntercept) {
                    int deltaY = y - mLastY;
                    scrollBy(0, -deltaY);
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mIntercept) {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float vel = mVelocityTracker.getYVelocity();

                    if (Math.abs(vel) > 500) {
                        index = vel > 0 ? index - 1 : index + 1;
                        Log.d(TAG, "vel：" + vel);
                    } else {
                        index = (getScrollY() + getChildAt(0).getMeasuredHeight() / 2) / getChildAt(0).getMeasuredHeight();
                    }
                    if (index < 0) index = 0;
                    if (index > getChildCount() - 1) index = getChildCount() - 1;
                    int dy = index * getChildAt(0).getMeasuredHeight() - getScrollY();
                    mScroller.startScroll(0, getScrollY(), 0, dy);
                    invalidate();
                }
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int resolveW = resolveSize(100, widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int h = 0;
        for (int i = 0; i < getChildCount(); i++) {
            h += getChildAt(0).getMeasuredHeight();
        }
        setMeasuredDimension(resolveW, h);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int offset = 0;
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            v.layout(l, offset + t, r, offset + v.getMeasuredHeight());
            offset += v.getMeasuredHeight();
        }
    }
}
