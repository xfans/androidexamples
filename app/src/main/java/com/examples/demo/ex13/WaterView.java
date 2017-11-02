package com.examples.demo.ex13;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.examples.demo.R;

/**
 * Created by zhu on 16/8/23.
 */
public class WaterView extends View {
    Paint mWavePaint;
    int a;
    int b;
    int mRotate;
    int mColor;
    ValueAnimator mValueAnimator;
    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaterView);
        mColor = a.getColor(R.styleable.WaterView_water_color, Color.BLUE);
        a.recycle();
        mWavePaint = new Paint();
        mWavePaint.setStrokeWidth(1.0F);
        mWavePaint.setColor(mColor);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(resolveSize(100,widthMeasureSpec),resolveSize(100,widthMeasureSpec));
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
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
