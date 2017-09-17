package com.examples.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

/**
 * Created by xfans on 17-9-18.
 */

public class SVGTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        //imageView.setImageResource(R.drawable.ic_launcher);
        setContentView(imageView);
        try {
            SVG svg = SVG.getFromResource(this, R.raw.svg_yun);
            Drawable drawable = new PictureDrawable(svg.renderToPicture());
            imageView.setImageDrawable(drawable);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }
}
