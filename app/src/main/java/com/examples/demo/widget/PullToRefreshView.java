package com.examples.demo.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.examples.demo.R;

/**
 * Created by xfan on 2017/2/28.
 */

public class PullToRefreshView extends ViewGroup {
    private View mHeader;
    private View mContent;
    private int mHeaderHeight = 200;
    private int mTouchSlop;

    public PullToRefreshView(Context context) {
        super(context);
        init(context);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mHeader = LayoutInflater.from(context).inflate(R.layout.pull_header, this, false);
        addView(mHeader);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    float lastX;
    float lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int action = ev.getAction();
        float x = ev.getX();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Pull", "ACTION_DOWN:" );
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("Pull", "ACTION_MOVE:" );
                if (canChildScrollUp()) {
                    intercept = false;
                } else {
                    if (y - lastY > 0) {
                        intercept = true;
                    } else {
                        intercept = false;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.d("Pull", "ACTION_UP:" );
                intercept = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("Pull", "ACTION_CANCEL:" );
                intercept = false;
                break;
        }

        lastX = x;
        lastY = y;
        Log.d("Pull", "intercept:" + intercept);
        return intercept;
    }
    int pullY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float x = ev.getX();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onTouchEvent", "ACTION_MOVE");
                if (canChildScrollUp()) {
                    //intercept = false;
                } else {
                    if (y - lastY > 0) {
                        pullY = (int) (y - lastY) + pullY;
                        Log.d("onTouchEvent", "pullY:"+pullY);
                        requestLayout();
                        //intercept = true;
                    } else {
                        //intercept = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                pullY = 0;
                requestLayout();
                Log.d("onTouchEvent", "ACTION_UP");
                //intercept = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                pullY = 0;
                requestLayout();
                Log.d("onTouchEvent", "ACTION_CANCEL");
                //intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        return super.onTouchEvent(ev);
    }

    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mContent instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mContent;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mContent, -1) || mContent.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mContent, -1);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getTarget();
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightMeasureSpec - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
        mContent.measure(widthMeasureSpec, heightMeasureSpec);
        mHeader.measure(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(mHeaderHeight, MeasureSpec.EXACTLY));
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getTarget();
        mHeader.layout(l, t - mHeaderHeight + pullY, r, b);
        mContent.layout(l, t + pullY, r, b);
    }

    public void getTarget() {
        if (mContent != null) return;
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v != mHeader) {
                mContent = v;
                break;
            }
        }
    }
}
