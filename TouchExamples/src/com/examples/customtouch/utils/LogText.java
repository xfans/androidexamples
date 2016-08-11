package com.examples.customtouch.utils;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by zhu on 16/8/11.
 */
public class LogText {
    private static TextView mtv;

    public static void setMtv(TextView mtv) {
        LogText.mtv = mtv;
    }

    public static void i(String text, MotionEvent event) {
        Log.i("LTAG", text + getAction(event));
        mtv.append(text +"-"+ getAction(event)+"\n");
    }

    private static String getAction(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "DOWN";

            case MotionEvent.ACTION_UP:
                return "UP";

            case MotionEvent.ACTION_MOVE:
                return "MOVE";

            case MotionEvent.ACTION_CANCEL:
                return "CANCEL";
            default:
                return "error";
        }

    }
}
