package com.examples.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.examples.demo.widget.CustomCanvasLayerView;

/**
 * Created by xfans on 17-9-18.
 */

public class CanvasLayerActivity extends Activity {
    CustomCanvasLayerView customCanvasLayerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvas_layer_view);
        customCanvasLayerView = (CustomCanvasLayerView) findViewById(R.id.view);

        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CanvasLayerActivity.this,"change",Toast.LENGTH_LONG).show();
                customCanvasLayerView.setColor();
            }
        });
    }
}
