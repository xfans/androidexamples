package com.examples.customtouch.widget;

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
import android.widget.ImageView;

/**
 * Created by mac01 on 16/7/6.
 */
public class RotateImage extends ImageView {
    public RotateImage(Context context) {
        super(context);
    }

    public RotateImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Rect rect =  drawable.getBounds();
//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //paint.setARGB(0,0,0,0);
        Bitmap b = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawCircle(rect.exactCenterX(),rect.exactCenterY(),rect.width()/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        c.rotate(30,rect.exactCenterX(),rect.exactCenterY());
        c.drawBitmap(bitmap,rect,rect,paint);
        paint.reset();
        canvas.drawBitmap(b,rect,rect,paint);
//        canvas.drawCircle(rect.exactCenterX(),rect.exactCenterY(),rect.width()/2,paint);
        //super.onDraw(canvas);
    }
}
