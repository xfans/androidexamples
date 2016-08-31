package com.examples.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.examples.demo.utils.LogText;

public class LView extends View {
    private boolean onTouchEvent;

    public boolean isOnTouchEvent() {
        return onTouchEvent;
    }

    public void setOnTouchEvent(boolean onTouchEvent) {
        this.onTouchEvent = onTouchEvent;
    }

    public LView(Context context) {
        super(context);
    }

    public LView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = 100;
        int h = 100;
        int mw = View.resolveSize(w,widthMeasureSpec);
        int mh = View.resolveSize(h,heightMeasureSpec);
        setMeasuredDimension(mw,mh);
    }

    // TextView <-- View
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogText.i("LView:onTouchEvent ",event);

        return onTouchEvent;
    }

}