package com.examples.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.examples.demo.utils.Marker;
import com.examples.demo.widget.MarkerGestureImageView;


/**
 * This example demonstrates how to show markers on the image pinned to particular image
 * points.<br/>
 * See also {@link MarkerGestureImageView}.
 */
public class SVGTestActivity extends Activity {

    private MarkerGestureImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = new MarkerGestureImageView(this);
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        try
        {
            SVG svg = SVG.getFromResource(this, R.raw.svg_1_g);
            Drawable drawable = new PictureDrawable(svg.renderToPicture());
            imageView.setImageDrawable(drawable);
        }
        catch(SVGParseException e)
        {}
        imageView.getController().getSettings().setMaxZoom(4f);

        setContentView(imageView);

        // Getting tinted icon drawable
        Drawable icon = ContextCompat.getDrawable(this, R.drawable.ic_pin_white_48dp);
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, ContextCompat.getColor(this, R.color.colorAccent));

        final float density = getResources().getDisplayMetrics().density;
        final int iconTipOffsetY = Math.round(4 * density); // Pin's tip vertical offset

        imageView.addMarker(new Marker()
                .setIcon(icon)
                .setLocation(917, 119) // Novosibirsk
                .setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
                .setOffset(0, iconTipOffsetY) // Icon's focal point (pin tip) offset
        );

        imageView.addMarker(new Marker()
                .setIcon(icon)
                .setLocation(141, 194) // San-Francisco
                .setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
                .setOffset(0, iconTipOffsetY) // Icon's focal point (pin tip) offset
                .setZoom(2f / density) // Initial zoom, taking into account icon's density
                .setMode(Marker.Mode.STICK) // Icon will be zoomed / rotated along with the image
        );

        // Applying general options
       // getSettingsListener().onSetupGestureView(imageView);
    }

    //@Override
    //protected void onSettingsChanged() {
        //getSettingsListener().onSetupGestureView(imageView);
    //}

}