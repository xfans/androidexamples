package com.examples.demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by xfans on 16/7/6.
 */
public class RotateImage1 extends ImageView {
    ValueAnimator mValueAnimator;
    int mRotate;
    BitmapShader mBitmapShader;
    Matrix mMatrix;

    public RotateImage1(Context context) {
        super(context);
        init();
    }

    public RotateImage1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateImage1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mMatrix = new Matrix();
        mValueAnimator = ValueAnimator.ofInt(360, 0);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotate = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(10);
        mValueAnimator.setDuration(3000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        float scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
        paint.setAntiAlias(true);
        mMatrix.setScale(scale, scale);
        mMatrix.postRotate(mRotate, getWidth() / 2, getWidth() / 2);
        mBitmapShader.setLocalMatrix(mMatrix);

        paint.setShader(mBitmapShader);
//        canvas.rotate(mRotate, getWidth() / 2, getWidth() / 2);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);

    }
}
