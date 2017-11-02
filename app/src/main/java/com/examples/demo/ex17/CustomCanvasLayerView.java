package com.examples.demo.ex17;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by riven_chris on 15/4/29.
 */
public class CustomCanvasLayerView extends View {

    private Paint paint = null;
    private Paint paint2 = null;
    private Paint paint3 = null;
    private Paint paint4 = null;
    private float x, y;

    private GestureDetector mDetector;

    public CustomCanvasLayerView(Context context) {
        this(context, null);
    }

    public CustomCanvasLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.GREEN);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.BLUE);

        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setColor(Color.YELLOW);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetector(context, new TapDetector());
        // Set the gesture detector as the double tap
        // listener.
//        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        x = w / 2;
        y = h / 2;
    }

    public void setColor() {
        paint3.setColor(Color.DKGRAY);

        invalidate();
    }
    float[] values = new float[9];
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(200, 200, x, y, paint);
        Matrix matrix = new Matrix();
        matrix.postTranslate(0,800);
        canvas.setMatrix(matrix);
        matrix.getValues(values);
//        int i1 = canvas.save();
//        canvas.translate(40f,0f);
//        canvas.clipRect(x - 150, y - 150, x + 150, y + 150);
//        canvas.drawColor(Color.GREEN);
//        canvas.restoreToCount(i1);
//
//        int i2 = canvas.save();
//        canvas.rotate(5);
        canvas.drawRect(200, 200, 300, 300, paint3);
//        canvas.restoreToCount(i2);
        //canvas.restoreToCount(i1);

        //canvas.drawRect(x - 300, y - 300, x + 300, y + 300, paint4);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }


    /** 获取matrix变化后原来的位置
     * https://stackoverflow.com/questions/8002298/android-imageview-get-coordinates-of-tap-click-regardless-of-scroll-location
     * // Get the values of the matrix
     * float[] values = new float[9];
     * matrix.getValues(values);

     * // values[2] and values[5] are the x,y coordinates of the top left corner of the drawable image, regardless of the zoom factor.
     * // values[0] and values[4] are the zoom factors for the image's width and height respectively. If you zoom at the same factor, these should both be the same value.

     * // event is the touch event for MotionEvent.ACTION_UP
     * float relativeX = (event.getX() - values[2]) / values[0];
     * float relativeY = (event.getY() - values[5]) / values[4];
     **/
    private class TapDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            //Log.e("TouchEvent", "onDown at CustomView.java");
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            float x = (e.getX() - values[2]) / values[0];
            float y = (e.getY() - values[5]) / values[4];

//            float x = e.getX();
//            float y = e.getY();
            if (200 <= x && x <= 300 && 200 <= y && y <= 300) {
                Log.e("TouchEvent", "onSingleTapUp:" + x + ":" + y);

            }
            return super.onSingleTapUp(e);
        }
    }
}