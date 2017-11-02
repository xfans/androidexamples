package com.examples.demo.ex18;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.examples.demo.R;

/**
 * Created by zooly on 17-11-2.
 */

public class CanvasTest extends View {
    public CanvasTest(Context context) {
        super(context);
    }

    public CanvasTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
//        canvas.drawColor(Color.RED);
        Paint paint = new Paint();
//        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
////        paint.setColor(Color.DKGRAY);

//        Shader shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

//        paint.setShader(shader);

//        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
//        paint.setColorFilter(lightingColorFilter);

        paint.setColor(Color.RED);
//        canvas.drawCircle(250, 250, 70, paint);

//        canvas.drawRect(150,150,350,350,paint);
//        paint.setColor(Color.GREEN);
//
//
//        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        paint.setXfermode(xfermode);
//        canvas.drawCircle(250,250,200,paint);
//
//        paint.setXfermode(null);
//        canvas.drawPaint(paint);


        paint.setShadowLayer(10, 0, 0, Color.RED);


        paint.setTextSize(100);
        paint.setAntiAlias(true);
//        canvas.drawText("test", 80, 300, paint);
        Path path = new Path();
        Paint paint1 = new Paint();
        paint.getTextPath("Test", 0, 4, 100, 100, path);

        PathEffect pathEffect = new PathDashPathEffect(path, 0, 0,
                PathDashPathEffect.Style.TRANSLATE);

        paint1.setTextSize(50);
        paint1.setPathEffect(pathEffect);
        canvas.drawPath(path, paint1);
        canvas.restoreToCount(saved);
    }
}
