package com.examples.customtouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class LView extends View {
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
        Log.i("LTAG", "Button:onTouchEvent "+getAction(event));
        Log.i("LTAG", "Button:onTouchEvent default return " + super.onTouchEvent(event));

        return true;
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
}