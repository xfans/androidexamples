package com.examples.demo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by zhu on 2016/8/9.
 */
public class ScrollViewPager extends ViewPager {
    private static final String TAG = "ScrollViewPager";
    public ScrollViewPager(Context context) {
        super(context);
    }

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    boolean is = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if(getCurrentItem() == 2 && ev.getAction() == MotionEvent.ACTION_DOWN){
//            ViewGroup vi = (ViewGroup) getParent();
//            MotionEvent e = MotionEvent.obtain(ev);
//            e.setAction(MotionEvent.ACTION_UP);
//            dispatchTouchEvent(e);
//            is = true;
////            if(is){
////                e.setAction(MotionEvent.ACTION_DOWN);
////                vi.dispatchTouchEvent(e);
////                is = false;
////            }
//
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onTouchEvent ev:" + ev.getAction() +"position:"+getCurrentItem());

        return super.onTouchEvent(ev);
    }
}
