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
public class ScrollerLayout2 extends ViewGroup {
    private static final String TAG = "ScrollerLayout";
    private Scroller mScroller;
    private int mLastX,mLastY;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    public ScrollerLayout2(Context context) {
        this(context, null);
    }

    public ScrollerLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (deltaY > deltaX && deltaY > mTouchSlop){
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        mLastX = x;
        mLastY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mVelocityTracker.addMovement(ev);
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action){
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastY;
                scrollBy(0,-deltaY);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.computeCurrentVelocity(1000);
                float vel = mVelocityTracker.getYVelocity();

                mScroller.fling(0,getScrollY(),0,(int)-vel,0,0,0,getChildAt(0).getMeasuredHeight()*2);

                invalidate();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
           scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int resolveW = resolveSize(100,widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int h = 0;
        for (int i = 0;i<getChildCount();i++){
            h += getChildAt(0).getMeasuredHeight();
        }
        setMeasuredDimension(resolveW,h);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int offset = 0;
        for (int i =0 ;i<count;i++){
            View v = getChildAt(i);
            v.layout(l,offset + t,r,offset + v.getMeasuredHeight());
            offset += v.getMeasuredHeight();
        }
    }
}
