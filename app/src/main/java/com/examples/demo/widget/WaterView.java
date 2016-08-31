package com.examples.demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * Created by zhu on 16/8/23.
 */
public class WaterView extends View {
    Paint mWavePaint;
    int a;
    int b;
    int mRotate;
    ValueAnimator mValueAnimator;
    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWavePaint = new Paint();
        mWavePaint.setStrokeWidth(1.0F);
        mWavePaint.setColor(Color.RED);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(View.resolveSize(100,widthMeasureSpec),View.resolveSize(100,widthMeasureSpec));
        mValueAnimator = ValueAnimator.ofInt(0,getMeasuredHeight());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotate = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(Animation.INFINITE);
        mValueAnimator.setDuration(1500);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 波浪效果
        int startX =  0;
        a = getMeasuredHeight() / 25;
        b = getMeasuredHeight() / 2;
        //y=Asin(2πx/B)
        //canvas.drawLine(startX, 0, startX, mAmplitude, mWavePaint);
        while (startX < getRight()) {
            int startY = (int)(a * Math.sin((2*Math.PI*(startX + mRotate + getMeasuredHeight()/2))/b));
            canvas.drawLine(startX, getMeasuredHeight(), startX, startY + getMeasuredHeight()/2, mWavePaint);
            startX++;
        }
    }
}
