package com.examples.demo.ex9;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by xfans on 16/7/6.
 */
public class RotateImage extends ImageView {
    ValueAnimator mValueAnimator;
    int mRotate;

    public RotateImage(Context context) {
        super(context);
        init();
    }

    public RotateImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mValueAnimator = ValueAnimator.ofInt(0,360);
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
        mValueAnimator.setStartDelay(500);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.start();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Bitmap bm = ((BitmapDrawable) drawable).getBitmap();
        Rect rect =  new Rect(0,0,getWidth(),getHeight());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        //paint.setARGB(0,0,0,0);
        Bitmap bitmap = Bitmap.createScaledBitmap(bm,rect.width(),rect.height(),true);

        Bitmap b = Bitmap.createBitmap(rect.width(),rect.height(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawCircle(rect.width()/2,rect.height()/2,rect.width()/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.rotate(mRotate,rect.exactCenterX(),rect.exactCenterY());
        c.drawBitmap(bitmap,rect,rect,paint);

//        if(!bitmap.isRecycled()){
//            bitmap.recycle();
//        }
        paint.reset();
//        canvas.concat(getImageMatrix());
        canvas.drawBitmap(b,rect,rect,paint);

    }
}
