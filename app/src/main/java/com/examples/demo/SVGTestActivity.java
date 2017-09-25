package com.examples.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGAndroidRenderer;
import com.caverock.androidsvg.SVGParseException;
import com.examples.demo.utils.GroupXY;
import com.examples.demo.utils.Marker;
import com.examples.demo.utils.SvgDecoder;
import com.examples.demo.utils.SvgDrawableTranscoder;
import com.examples.demo.utils.SvgSoftwareLayerSetter;
import com.examples.demo.widget.MarkerGestureImageView;

import java.io.InputStream;
import java.util.List;


/**
 * This example demonstrates how to show markers on the image pinned to particular image
 * points.<br/>
 * See also {@link MarkerGestureImageView}.
 */
public class SVGTestActivity extends Activity {

    private MarkerGestureImageView imageView;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    Drawable icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = new MarkerGestureImageView(this);
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        requestBuilder = Glide.with(this)
                .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .skipMemoryCache(true)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>(new SVGListener() {
                    @Override
                    public void success() {

                        List<GroupXY> groupXYList = SVGAndroidRenderer.getGroupXYList();
                        for (GroupXY g:groupXYList){
                            imageView.addMarker(new Marker()
                                    .setIcon(icon)
                                    .setLocation(g.getX(), g.getY()) // Novosibirsk
                                    .setGravity(Gravity.CENTER)
                            );
                        }
                    }
                }));


//        try
//        {
//            SVG svg = SVG.getFromResource(this, R.raw.svg_1_g);
//            Drawable drawable = new PictureDrawable(svg.renderToPicture());
//            imageView.setImageDrawable(drawable);
//        }
//        catch(SVGParseException e)
//        {}
        imageView.getController().getSettings().setMaxZoom(4f);
//
        setContentView(imageView);
        loadNet();

//        // Getting tinted icon drawable
        icon = ContextCompat.getDrawable(this, R.drawable.ic_circle_hollow);
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, ContextCompat.getColor(this, R.color.colorAccent));
//
//        final float density = getResources().getDisplayMetrics().density;
//        final int iconTipOffsetY = Math.round(4 * density); // Pin's tip vertical offset

//        imageView.addMarker(new Marker()
//                .setIcon(icon)
//                .setLocation(917, 119) // Novosibirsk
//                .setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
//                .setOffset(0, iconTipOffsetY) // Icon's focal point (pin tip) offset
//        );
//
//        imageView.addMarker(new Marker()
//                .setIcon(icon)
//                .setLocation(141, 194) // San-Francisco
//                .setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
//                .setOffset(0, iconTipOffsetY) // Icon's focal point (pin tip) offset
//                .setZoom(2f / density) // Initial zoom, taking into account icon's density
//                .setMode(Marker.Mode.STICK) // Icon will be zoomed / rotated along with the image
//        );

        // Applying general options
       // getSettingsListener().onSetupGestureView(imageView);
    }

    //@Override
    //protected void onSettingsChanged() {
        //getSettingsListener().onSetupGestureView(imageView);
    //}
    private void loadNet() {
        Uri uri = Uri.parse("https://raw.githubusercontent.com/xfans/androidexamples/master/app/src/main/res/raw/svg_1_g.svg");
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(imageView);
    }

    public interface SVGListener{
        void success();
    }
}